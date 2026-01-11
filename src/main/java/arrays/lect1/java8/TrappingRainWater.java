package arrays.lect1.java8;

import java.util.stream.IntStream;

import static arrays.lect1.MaximumOnLeftAndRightSide.maxLeft;
import static arrays.lect1.MaximumOnLeftAndRightSide.maxRight;

/**
 * https://www.educative.io/edpresso/the-trapping-rainwater-algorithm-in-cpp-python-and-java
 * https://www.educative.io/edpresso/the-trapping-rainwater-algorithm-in-cpp-python-and-java
 */
public class TrappingRainWater {

    private static int[] A = {1, 2, 1, 3, 1, 2, 1, 4, 1, 0, 0, 2, 1, 4};
    // int[] B = {0, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 0};

    public static void main(String args[]) {
        findTrappedWater();

    }


    public static int findTrappedWater() {
        int water = 0; //total water as we traverse the elevation map
        int[] elevationMap = {1, 2, 1, 3, 1, 2, 1, 4, 1, 0, 0, 2, 1, 4};

        int[] maxLeftHeight = maxLeft(elevationMap);
        int[] maxRightHeight = maxRight(elevationMap);

        //calculating the amount of water

        int n = elevationMap.length; //number of points on the map
        for (int i = 0; i < n; i++) {
            int waterHeightAti = Math.min(maxLeftHeight[i], maxRightHeight[i]) - elevationMap[i];
            waterHeightAti = waterHeightAti < 0 ? 0 : waterHeightAti;
            water += waterHeightAti;
        }
        return water;
    }

    public static int findMaxWaterBucket() {
        int water = 0; //total water as we traverse the elevation map
        int[] elevationMap = {1, 2, 1, 3, 1, 2, 1, 4, 1, 0, 0, 2, 1, 4};

        int[] maxLeftHeight = maxLeft(elevationMap);
        int[] maxRightHeight = maxRight(elevationMap);

        //calculating the amount of water

        int n = elevationMap.length; //number of points on the map
        int[] waterHeightMap = IntStream.range(0, elevationMap.length).map((i) ->
                {
                    int waterHeightAti = Math.min(maxLeftHeight[i], maxRightHeight[i]) - elevationMap[i];
                    return Math.max(0, waterHeightAti);
                }
        ).toArray();
        int maxBucketHeight = 0;
        int bucketHeight = 0;

        for (int i = 0; i < elevationMap.length; i++) {
            if (waterHeightMap[i] == 0) {
                maxBucketHeight = Math.max(maxBucketHeight, bucketHeight);
                bucketHeight = 0;
                continue;
            }
            bucketHeight = bucketHeight + waterHeightMap[i];
        }
        return bucketHeight;
    }


}
