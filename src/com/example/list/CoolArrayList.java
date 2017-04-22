package com.example.list;

import java.util.AbstractList;
import java.util.Objects;

public class CoolArrayList<T> extends AbstractList<T> {
    private static final int MAX_SIZE = 30000;

    // fixed array - it's OK
    @SuppressWarnings("unchecked")
    private T[] content = (T[]) new Object[MAX_SIZE];
    private int size = 0;

    @Override
    public boolean add(T t) {
        if (size < MAX_SIZE) {
            content[size++] = t;
            return true;
        }
        throw new RuntimeException("Max size reached: " + MAX_SIZE);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Wrong index: " + index);
        }
        return content[index];
    }

    @Override
    public T remove(int index) {
        T result = get(index);
        System.arraycopy(content, index + 1, content, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }
}
