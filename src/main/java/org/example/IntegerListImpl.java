package org.example;

import java.util.Arrays;
import java.util.Random;

public class IntegerListImpl implements IntegerList {
    private Integer[] items;
    private int size;
    public IntegerListImpl(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }
        this.items = new Integer[initialCapacity];
        this.size = 0;
    }
    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new NullElementException();
        }
        ensureCapacity();
        items[size] = item;
        size++;
        return item;
    }
    @Override
    public Integer add(int index, Integer item) {
        if (item == null) {
            throw new NullElementException();
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        ensureCapacity();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        return item;
    }
    @Override
    public Integer set(int index, Integer item) {
        if (item == null) {
            throw new NullElementException();
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Integer oldItem = items[index];
        items[index] = item;
        return oldItem;
    }
    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException("Element not found: " + item);
        }
        return remove(index);
    }
    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Integer removedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[--size] = null;
        return removedItem;
    }
    @Override
    public boolean contains(Integer item) {
        sort();
        return binarySearch(item) != -1;
    }
    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return items[index];
    }
    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullElementException();
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!items[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }
    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[size];
        System.arraycopy(items, 0, array, 0, size);
        return array;
    }
    private void ensureCapacity() {
        if (size == items.length) {
            int newCapacity = items.length * 2;
            Integer[] newItems = new Integer[newCapacity];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
    }
    private void sort() {
        quickSort(0, size - 1);
    }
    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }
    private int partition(int low, int high) {
        Integer pivot = items[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (items[j] < pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }
    private void swap(int i, int j) {
        Integer temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
    private int binarySearch(Integer item) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int result = items[mid].compareTo(item);
            if (result == 0) {
                return mid;
            }
            if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        // Сравнение сортировок
        int size = 100000;
        Integer[] arr1 = generateRandomArray(size);
        Integer[] arr2 = Arrays.copyOf(arr1, size);
        Integer[] arr3 = Arrays.copyOf(arr1, size);
        long start = System.currentTimeMillis();
        Arrays.sort(arr1);
        System.out.println("Arrays.sort: " + (System.currentTimeMillis() - start) + " ms");
        start = System.currentTimeMillis();
        quickSort(arr2, 0, size - 1);
        System.out.println("QuickSort: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        mergeSort(arr3, 0, size - 1);
        System.out.println("MergeSort: " + (System.currentTimeMillis() - start) + " ms");
    }
    private static Integer[] generateRandomArray(int size) {
        Integer[] arr = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }
    private static void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    private static int partition(Integer[] arr, int low, int high) {
        Integer pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    private static void swap(Integer[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static void mergeSort(Integer[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    private static void merge(Integer[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
