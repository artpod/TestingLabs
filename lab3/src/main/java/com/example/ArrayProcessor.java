package com.example;

public class ArrayProcessor {

    private BubbleSort bubbleSort;

    public ArrayProcessor(BubbleSort bubbleSort) {
        this.bubbleSort = bubbleSort;
    }

    public int[] processArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        int[] result = arr.clone();
        bubbleSort.sort(result);
        return result;
    }
}
