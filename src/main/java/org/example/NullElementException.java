package org.example;

public class NullElementException extends StringListException {
    public NullElementException() {
        super("Элемент не может быть null");
    }
}
