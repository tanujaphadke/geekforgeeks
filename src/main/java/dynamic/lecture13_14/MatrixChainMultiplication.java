package dynamic.lecture13_14;

/*
https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/

Find the paranthesisation of a chain of matrices such that the cost is minimum
 */

class OptimalValueAndPartition {
    int partitionAt;
    int value;

    public OptimalValueAndPartition(int partitionAt, int value) {
        this.partitionAt = partitionAt;
        this.value = value;
    }

    public int getPartitionAt() {
        return partitionAt;
    }

    public void setPartitionAt(int partitionAt) {
        this.partitionAt = partitionAt;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class MatrixChainMultiplication {
    //EIther store two sepearate matrices o
    static int partitionAt[][];
    static Integer optimizedValue[][];

    static int[] dimensions = {1, 2, 3, 4}; //{4, 10, 3, 12, 20, 7};


    public static void initMatrices(int totalNoOfMatrices) {
        partitionAt = new int[totalNoOfMatrices][totalNoOfMatrices];
        optimizedValue = new Integer[totalNoOfMatrices][totalNoOfMatrices];
    }

    //               A1     A2      A3      A4      A5
    //               A0     A1      A2      A3      A4
    //partiton at     0      1      2       3       4
    //              4X10,   10X3,   3X12,   12X20,   20X7
    //                  4,  10,     3,      12,       20,     7
    //This method should be overridden
    public static OptimalValueAndPartition calculateOptimizedValue(int fromMatrixAtIdx, int tillMatrixAtIdx) {
        //Do some calculation here .
        if (fromMatrixAtIdx == tillMatrixAtIdx || fromMatrixAtIdx > tillMatrixAtIdx) {
            optimizedValue[fromMatrixAtIdx][tillMatrixAtIdx] = 0; //cost is 0
            partitionAt[fromMatrixAtIdx][tillMatrixAtIdx] = fromMatrixAtIdx; //no partition
            return new OptimalValueAndPartition(fromMatrixAtIdx, 0);
        } else {
            //Find minimum of K = row to col-1
            //optimizedValue[row][row] + optimizedValue[row + 1 ] [col] + dimensions[row -1][..
            //If we are splitting at row
            int minCost = Integer.MAX_VALUE;
            int minIdx = Integer.MAX_VALUE;
            //We are partitioning at K so following the recurrence
            //we must first calculate teh min cost of muttiplication from startIdxofMatrix till k and then k+1 to the endIndex
            for (int k = fromMatrixAtIdx; k < tillMatrixAtIdx; k++) {
                int cost = optimizedValue[fromMatrixAtIdx][k] + optimizedValue[k + 1][tillMatrixAtIdx];
                //If  you are starting at index 0 meaning that P0 is the no of rows in the 0th matrix
                int noOfMultiplicationsForfinal2matrices = dimensions[fromMatrixAtIdx] * dimensions[k + 1 ] * dimensions[tillMatrixAtIdx + 1];
                cost = cost + noOfMultiplicationsForfinal2matrices;

                if (cost < minCost) {
                    minCost = cost;
                    minIdx = k;
                }
            }
            optimizedValue[fromMatrixAtIdx][tillMatrixAtIdx]= minCost;
            partitionAt[fromMatrixAtIdx][tillMatrixAtIdx] = minIdx;

            return new OptimalValueAndPartition(minIdx, minCost);
        }
    }

    // send an array to this
    public static void fillWithOptimalValues(int startIdx, int totalNoOfMatrices) {
//        if (startIdx == endIdx) {
//            //will be 0;
//        } else if (endIdx > startIdx) {
        for (int k = 0; k < totalNoOfMatrices; k++) {
            for (int  i = 0; i < totalNoOfMatrices - k ; i++) {
                int tillMatrixAtIdx = i + k ;
                optimizedValue[i][ tillMatrixAtIdx ] = calculateOptimizedValue(i, tillMatrixAtIdx).getValue();
            }
        }
    }
    //At the end pf this optimizedValue (1, n ) will contain the minumum of something


    public static int optimalSolution(int totalNoOfMatrices) {
        fillWithOptimalValues(0, totalNoOfMatrices);
        return optimizedValue[0][totalNoOfMatrices-1];
    }

    public static void main(String args[]) {
        //THIS IS the total no of matrices dimensions.length -1
        // The dimensions array is represented as follows
        //                  4,  10,     3,      12,       20,     7
        //which translates to
        //               4X10,   10X3,   3X12,   12X20,   20X7
        //meaning matrix A0 has dimensions P0 X P1
        //meaning matrix A1 has dimensions P1 X P2
        //meaning matrix Ai has dimensions Pi X Pi+1

        int totalNoOfMatrices = dimensions.length -1;//n
        initMatrices(totalNoOfMatrices);
        System.out.println("This minimum cost o matrix multiplication is " + optimalSolution(totalNoOfMatrices));
    }


}
