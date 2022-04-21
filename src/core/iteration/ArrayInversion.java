package core.iteration;

import java.util.Arrays;

public class ArrayInversion {
    public static void main(String[] args) {
        int[] intArray = {1, 5, 7, 10, 17};

        for (int i = 0; i < intArray.length /2; i++) {
            int temp = intArray[i];
            intArray[i] = intArray[intArray.length -i -1];
            intArray[intArray.length -i -1] = temp;
        }

        System.out.println(Arrays.toString(intArray));
    }
}
