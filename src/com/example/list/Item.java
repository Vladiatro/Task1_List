package com.example.list;

public class Item {
    private static int idSequence;

    private String name;
    private int count;

    private int[] a = new int[1000];

    public Item(int count) {
        this.count = count;
        name = "Item #" + idSequence;
        idSequence++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return name;
    }
}
