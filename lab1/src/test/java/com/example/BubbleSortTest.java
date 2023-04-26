package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class BubbleSortTest {

    private BubbleSorter bubbleSort;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests.");
    }

    @BeforeEach
    void setUp() {
        bubbleSort = new BubbleSorter();
        System.out.println("Before each test.");
    }

    @Test
    void testEmptyArray() {
        int[] array = new int[0];
        System.out.println("Before sorting:");
        printArr(array);
        bubbleSort.sort(array);
        System.out.println("Sorted:");
        printArr(array);
        assertThat(array).isEmpty();
    }

    @Test
    void testOneElementArray() {
        int[] array = new int[]{5};
        System.out.println("Before sorting:");
        printArr(array);
        bubbleSort.sort(array);
        System.out.println("Sorted:");
        printArr(array);
        assertThat(array).hasSize(1);
        assertThat(array).containsExactly(5);
    }

    @Test
    void testTwoElementArray() {
        int[] array = new int[]{5, 3};
        System.out.println("Before sorting:");
        printArr(array);
        bubbleSort.sort(array);
        System.out.println("Sorted:");
        printArr(array);
        assertThat(array).hasSize(2);
        assertThat(array).containsExactly(3, 5);
    }

    @Test
    void testMultipleElementsArray() {
        int[] array = new int[]{5, 1, 4, 2, 8};
        System.out.println("Before sorting:");
        printArr(array);
        bubbleSort.sort(array);
        System.out.println("Sorted:");
        printArr(array);
        assertThat(array).hasSize(5);
        assertThat(array).containsExactly(1, 2, 4, 5, 8);
    }

    @Test
    void testNullPointerException() {
        assertThatThrownBy(() -> bubbleSort.sort(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Cannot read the array length because \"array\" is null");
    }

    @ParameterizedTest
    @CsvSource({
            "'5,1,4,2,8', '1,2,4,5,8'",
            "'3,2,1', '1,2,3'",
            "'1,2,3', '1,2,3'"
    })
    void testParameterized(String input, String expected) {
        List<String> inputList = Arrays.asList(input.split(","));
        List<String> expectedList = Arrays.asList(expected.split(","));

        int[] array = inputList.stream().mapToInt(Integer::parseInt).toArray();
        int[] expectedArray = expectedList.stream().mapToInt(Integer::parseInt).toArray();

        System.out.println("Before sorting:");
        printArr(array);
        bubbleSort.sort(array);
        System.out.println("Sorted:");
        printArr(array);

        assertThat(array).hasSize(expectedArray.length);
        assertThat(array).containsExactly(expectedArray);
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