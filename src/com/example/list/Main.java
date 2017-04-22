package com.example.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

public class Main {
    private static final int ROUNDS = 10;
    private static int stub;

    private static long testList(List<Item> items) {
        Random random = new Random();

        // items income
        for (int i = 0; i < 250; i++) {
            items.add(new Item(random.nextInt(100) + 10));
        }

        // compute total items count (just for mark objects as used)
        stub += items.stream().mapToInt(Item::getCount).sum();

        // our clients buy some items
        for (int i = 0; i < 249; i++) {
            items.remove(random.nextInt(items.size()));
//            items.remove(items.size() - 1);
        }

        System.gc();
        System.gc();
        System.gc();
        Runtime.getRuntime().gc();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        stub += items.stream().mapToInt(Item::getCount).sum();
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static void main(String[] args) {
        // try commenting line 46 and uncommenting 47. Compare the results.
//        Supplier<List<Item>> listSupplier = ArrayList::new;
        Supplier<List<Item>> listSupplier = CoolArrayList::new;
        System.out.println("Testing " + listSupplier.get().getClass().getName());
        long memory = 0;
        for (int i = 0; i < ROUNDS; i++) {
            memory += testList(listSupplier.get());
        }
        System.out.printf("Average memory consumption: %d (%.2f MB)\n", memory / ROUNDS, memory / ROUNDS / 1000000.0);
    }
}
