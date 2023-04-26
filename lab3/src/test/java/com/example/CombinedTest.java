package com.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;

public class CombinedTest {

    @Mock
    private Swapper swapper; ///3

    @InjectMocks
    private BubbleSort bubbleSort; ///3

    @Mock
    private BubbleSort bubbleSortMock; ///3

    private ArrayProcessor arrayProcessor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        arrayProcessor = new ArrayProcessor(bubbleSortMock);
    }

    // Test 1: Scenario with normal swapping
    @Test
    public void testNormalSwapping() {
        int[] arr = {3, 2, 1};
        System.out.println("Before sorting:");
        printArr(arr);  
        bubbleSort.sort(arr);
        System.out.println("Sorted:");
        printArr(arr);
        verify(swapper, times(3)).swap(eq(arr), anyInt(), anyInt());        /// (2) times
    }

    // Test 2: Scenario with no swapping
    @Test
    public void testNoSwapping() {
        int[] arr = {1, 2, 3};
        System.out.println("Before sorting:");
        printArr(arr);  
        bubbleSort.sort(arr);
        System.out.println("Sorted:");
        printArr(arr);
        verify(swapper, never()).swap(eq(arr), anyInt(), anyInt());
    }

    // Test 3: Scenario with exception handling                             //// (5) один сценарій за якого мок об’єкт має 
    @Test(expected = IllegalArgumentException.class)                        //       згенерувати виключення.
    public void testNullArrayThrowsException() {
        bubbleSort.sort(null);
    }

    // Test 4: Scenario with partial mock object                            /// (4)
    @Test
    public void testPartialMockObject() {
        Swapper realSwapper = new ArraySwapper();
        Swapper spySwapper = spy(realSwapper);

        BubbleSort bubbleSortWithSpy = new BubbleSort(spySwapper);

        int[] arr = {3, 2, 1};
        System.out.println("Before sorting:");
        printArr(arr);  
        bubbleSortWithSpy.sort(arr);
        System.out.println("Sorted:");
        printArr(arr);
        

        verify(spySwapper, times(3)).swap(eq(arr), anyInt(), anyInt());
    }

    // Test 5: Scenario where mock object throws an exception
    @Test(expected = RuntimeException.class)
    public void testSwapperThrowsException() {
        int[] arr = {3, 2, 1};
        doThrow(RuntimeException.class).when(swapper).swap(eq(arr), anyInt(), anyInt());

        bubbleSort.sort(arr);  
    }

    // Test 6: Test ArrayProcessor with Mockito
    @Test
    public void testProcessArray() {
    int[] input = {4, 3, 2, 1};
    int[] sorted = {1, 2, 3, 4};
    int[] expectedInput = input.clone();

    doAnswer(invocation -> {
        int[] arr = invocation.getArgument(0);
        System.arraycopy(sorted, 0, arr, 0, arr.length);
        return null;
    }).when(bubbleSortMock).sort(any(int[].class));
    
    System.out.println("Before sorting:");
    printArr(input);    
    int[] result = arrayProcessor.processArray(input.clone());
    System.out.println("Sorted:");
    printArr(result);

    assertArrayEquals(sorted, result);

    ArgumentCaptor<int[]> arrayCaptor = ArgumentCaptor.forClass(int[].class);
    verify(bubbleSortMock, times(1)).sort(arrayCaptor.capture());
    int[] capturedArray = arrayCaptor.getValue();

    assertArrayEquals(sorted, capturedArray);
}



    // Additional class for test 4
    private static class ArraySwapper implements Swapper {
        @Override
        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
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
