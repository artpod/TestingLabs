package com.example;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.Arrays;

public class BubbleSortTestNG {
    private BubbleSort bubbleSort;

    @BeforeClass
    public void setupClass() {
        bubbleSort = new BubbleSort();
    }

    @BeforeMethod
    public void setupMethod() {
        System.out.println("Starting a new test method...");
    }

    @DataProvider(name = "sortingDataProvider")
    public Object[][] sortingDataProvider() {
        return new Object[][]{
                {new int[]{3, 2, 1}, new int[]{1, 2, 3}},
                {new int[]{}, new int[]{}},
                {new int[]{5}, new int[]{5}},
                {new int[]{4, 4, 4, 4}, new int[]{4, 4, 4, 4}}
        };
    }

    @Test(dataProvider = "sortingDataProvider", groups = {"sorting"})
    public void testBubbleSort(int[] input, int[] expected) {
        System.out.println("Before sorting:");
        printArr(input);
        bubbleSort.sort(input);
        System.out.println("Sorted:");
        printArr(input);
        Assert.assertEquals(input, expected);
    }

    @Test(groups = {"exceptions"})
    public void testNullArrayThrowsException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> bubbleSort.sort(null));
    }

    @Test(groups = {"sorting"})
    public void testAlreadySortedArray() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        bubbleSort.sort(arr);
        //printArr(arr);
        Assert.assertEquals(arr, expected);
    }

    @Test(groups = {"sorting"})
    public void testReverseSortedArray() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        bubbleSort.sort(arr);
        //printArr(arr);
        Assert.assertEquals(arr, expected);
    }

    private static void printArr(int[] arr)
    {
        if (arr.length != 0) {
        for (int i=0; i < arr.length; i++)
        System.out.print(arr[i]+" ");
        System.out.println("");
        }
        else {
        System.out.print("[ ]");
        System.out.println("");
        }
    }
}
