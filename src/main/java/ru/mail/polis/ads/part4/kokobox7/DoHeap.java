package ru.mail.polis.ads.part4.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class DoHeap {

    private static class Heap {
        private int[] array;
        //points to where new element should be added
        private int pointer;

        Heap(int size) {
            array = new int[size + 1];
            pointer = 1;
        }

        void insert(final int x) {
            array[pointer] = x;
            swim(pointer);
            pointer++;
        }

        int extract() {
            swap(--pointer, 1);
            int indx = sink();
            swim(indx);
            return array[pointer];
        }

        private void swim(int index) {
            while (index != 1 && array[index] > array[index / 2]) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private void swap(int index1, int index2) {
            final int tmp = array[index2];
            array[index2] = array[index1];
            array[index1] = tmp;
        }

        //sinks first element
        private int sink() {
            int index = 1;
            int where = index * 2;
            while (where < pointer - 1) {
                if (array[where] < array[where + 1]) {
                    where++;
                }
                swap(index, where);
                index = where;
                where *= 2;
            }
            if (where == pointer - 1) {
                swap(index, where);
                index = where;
            }
            return index;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {

            final int n = in.nextInt();
            Heap heap = new Heap(n);
            for (int i = 0; i < n; i++) {
                int input = in.nextInt();
                if (input == 1) {
                    out.println(heap.extract());
                } else {
                    heap.insert(in.nextInt());
                }
            }
        }
    }
}