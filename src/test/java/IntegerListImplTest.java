import org.example.IntegerList;
import org.example.IntegerListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IntegerListImplTest {
    private IntegerList integerList;

    @BeforeEach
    public void setUp() {
        integerList = new IntegerListImpl(10);
    }

    @Test
    public void testAdd() {
        assertEquals(Integer.valueOf(1), integerList.add(1));
        assertEquals(Integer.valueOf(2), integerList.add(2));
        assertEquals(2, integerList.size());
    }

    @Test
    public void testAddAtIndex() {
        integerList.add(1);
        integerList.add(2);
        assertEquals(Integer.valueOf(3), integerList.add(1, 3));
        assertEquals(Integer.valueOf(3), integerList.get(1));
        assertEquals(3, integerList.size());
    }

    @Test
    public void testSet() {
        integerList.add(1);
        integerList.add(2);
        assertEquals(Integer.valueOf(1), integerList.set(0, 3));
        assertEquals(Integer.valueOf(3), integerList.get(0));
    }

    @Test
    public void testRemoveByIndex() {
        integerList.add(1);
        integerList.add(2);
        assertEquals(Integer.valueOf(1), integerList.remove(0));
        assertEquals(1, integerList.size());
        assertFalse(integerList.contains(1));
    }
}
