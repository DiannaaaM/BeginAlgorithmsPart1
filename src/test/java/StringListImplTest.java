import org.example.ElementNotFoundException;
import org.example.NullElementException;
import org.example.StringList;
import org.example.StringListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {

    private StringList stringList;

    @BeforeEach
    public void setUp() {
        stringList = new StringListImpl(10);
    }

    @Test
    public void testAdd() {
        assertEquals("Item1", stringList.add("Item1"));
        assertEquals("Item2", stringList.add("Item2"));
        assertEquals(2, stringList.size());
    }

    @Test
    public void testAddAtIndex() {
        stringList.add("Item1");
        stringList.add("Item2");
        assertEquals("Item3", stringList.add(1, "Item3"));
        assertEquals("Item3", stringList.get(1));
        assertEquals(3, stringList.size());
    }

    @Test
    public void testSet() {
        stringList.add("Item1");
        stringList.add("Item2");
        assertEquals("Item1", stringList.set(0, "Item3"));
        assertEquals("Item3", stringList.get(0));
    }

    @Test
    public void testRemoveByItem() {
        stringList.add("Item1");
        stringList.add("Item2");
        assertEquals("Item1", stringList.remove("Item1"));
        assertEquals(1, stringList.size());
        assertFalse(stringList.contains("Item1"));
    }

    @Test
    public void testRemoveByIndex() {
        stringList.add("Item1");
        stringList.add("Item2");
        assertEquals("Item1", stringList.remove(0));
        assertEquals(1, stringList.size());
        assertFalse(stringList.contains("Item1"));
    }

    @Test
    public void testContains() {
        stringList.add("Item1");
        assertTrue(stringList.contains("Item1"));
        assertFalse(stringList.contains("Item2"));
    }

    @Test
    public void testIndexOf() {
        stringList.add("Item1");
        stringList.add("Item2");
        assertEquals(0, stringList.indexOf("Item1"));
        assertEquals(1, stringList.indexOf("Item2"));
        assertEquals(-1, stringList.indexOf("Item3"));
    }

    @Test
    public void testLastIndexOf() {
        stringList.add("Item1");
        stringList.add("Item2");
        stringList.add("Item1");
        assertEquals(2, stringList.lastIndexOf("Item1"));
        assertEquals(1, stringList.lastIndexOf("Item2"));
        assertEquals(-1, stringList.lastIndexOf("Item3"));
    }

    @Test
    public void testGet() {
        stringList.add("Item1");
        stringList.add("Item2");
        assertEquals("Item1", stringList.get(0));
        assertEquals("Item2", stringList.get(1));
    }

    @Test
    public void testEquals() {
        StringList anotherList = new StringListImpl(10);
        stringList.add("Item1");
        anotherList.add("Item1");
        assertTrue(stringList.equals(anotherList));
        anotherList.add("Item2");
        assertFalse(stringList.equals(anotherList));
    }

    @Test
    public void testSize() {
        assertEquals(0, stringList.size());
        stringList.add("Item1");
        assertEquals(1, stringList.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stringList.isEmpty());
        stringList.add("Item1");
        assertFalse(stringList.isEmpty());
    }

    @Test
    public void testClear() {
        stringList.add("Item1");
        stringList.add("Item2");
        stringList.clear();
        assertEquals(0, stringList.size());
        assertTrue(stringList.isEmpty());
    }

    @Test
    public void testToArray() {
        stringList.add("Item1");
        stringList.add("Item2");
        String[] array = stringList.toArray();
        assertEquals(2, array.length);
        assertEquals("Item1", array[0]);
        assertEquals("Item2", array[1]);
    }

    @Test
    public void testAddNullElement() {
        assertThrows( NullElementException.class, () -> stringList.add(null));
    }

    @Test
    public void testAddNullElementAtIndex() {
        assertThrows(NullElementException.class, () -> stringList.add(0, null));
    }

    @Test
    public void testSetNullElement() {
        stringList.add("Item1");
        assertThrows(NullElementException.class, () -> stringList.set(0, null));
    }

    @Test
    public void testEqualsNullList() {
        assertThrows(NullElementException.class, () -> stringList.equals(null));
    }



    @Test
    public void testRemoveNonExistentElement() {
        assertThrows( ElementNotFoundException.class, () -> stringList.remove("Item1"));
    }
}
