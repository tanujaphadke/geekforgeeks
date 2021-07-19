package dynamic.lecture15;

// Java program to solve fractional Knapsack Problem

import java.util.Arrays;
import java.util.Comparator;

// Greedy approach
public class FractionalKnapSack {
    // function to get maximum value
    private static double getMaxValue(int[] wt, int[] val, int capacity) {
        ItemValue[] itemValues = new ItemValue[wt.length];

        for (int i = 0; i < wt.length; i++) {
            itemValues[i] = new ItemValue(wt[i], val[i]);
        }

        // sorting items by value;
        Arrays.sort(itemValues, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2) {
                return o2.valuePerUnitWeight.compareTo(o1.valuePerUnitWeight);
            }
        });

        double totalValue = 0d;

        for (ItemValue item : itemValues) {

            int curWt = (int) item.wt;
            int curVal = (int) item.val;

            if (capacity - curWt >= 0) {
                // this weight can be picked while
                capacity = capacity - curWt;
                totalValue += curVal;
            } else {
                // item cant be picked whole
                double fraction
                        = ((double) capacity / (double) curWt);
                totalValue += (curVal * fraction);
                capacity
                        = (int) (capacity - (curWt * fraction));
                break;
            }
        }

        return totalValue;
    }

    // item value class
    static class ItemValue {
        Double valuePerUnitWeight;
        double wt, val;

        // item value function
        public ItemValue(int wt, int val) {
            this.wt = wt;
            this.val = val;
            // this.ind = ind;
            valuePerUnitWeight = new Double((double) val / (double) wt);
        }
    }

    // Driver code
    public static void main(String[] args) {
        int[] wt = {10, 40, 20, 30};
        int[] val = {60, 40, 100, 120};
        int capacity = 50;

        double maxValue = getMaxValue(wt, val, capacity);

        // Function call
        System.out.println("Maximum value we can obtain = "
                + maxValue);
    }
}
