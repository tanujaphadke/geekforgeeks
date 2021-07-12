package dynamic.lecture13_14;

/*
https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/

Find the paranthesisation of a chain of matrices such that the cost is minimum
 */

public class MatrixChainMultiplication2 {
    //EIther store two sepearate matrices o
    static OptimalValueAndPartition optimizedValue[][];

    static int[] dimensions = {4, 10, 3, 12, 20, 7}; //{1, 2, 3, 4}; //{4, 10, 3, 12, 20, 7};

    // 1X2 2X3  = 6  1X3 3 X4  => 12 totoal multi = 6 + 12 = 18


    public static void initMatrices(int totalNoOfMatrices) {
        optimizedValue = new OptimalValueAndPartition[totalNoOfMatrices][totalNoOfMatrices];
        for (int i = 0; i < totalNoOfMatrices; i++) {
            for (int j = 0; j < totalNoOfMatrices; j++) {
                optimizedValue[i][j] = new OptimalValueAndPartition(0, -1);
            }

        }
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
            optimizedValue[fromMatrixAtIdx][tillMatrixAtIdx].setValue(0);  //cost is 0
            optimizedValue[fromMatrixAtIdx][tillMatrixAtIdx].setPartitionAt(fromMatrixAtIdx); //no partition
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
                int cost = optimizedValue[fromMatrixAtIdx][k].getValue() + optimizedValue[k + 1][tillMatrixAtIdx].getValue();
                //If  you are starting at index 0 meaning that P0 is the no of rows in the 0th matrix
                int noOfMultiplicationsForfinal2matrices =
                        dimensions[fromMatrixAtIdx] * dimensions[k + 1] * dimensions[tillMatrixAtIdx + 1];
                cost = cost + noOfMultiplicationsForfinal2matrices;

                if (cost < minCost) {
                    minCost = cost;
                    minIdx = k;
                }
            }
            optimizedValue[fromMatrixAtIdx][tillMatrixAtIdx].setValue(minCost);
            optimizedValue[fromMatrixAtIdx][tillMatrixAtIdx].setPartitionAt(minIdx);

            return new OptimalValueAndPartition(minIdx, minCost);
        }
    }

    // send an array to this
    public static void fillWithOptimalValues(int startIdx, int totalNoOfMatrices) {
        for (int k = 0; k < totalNoOfMatrices; k++) {
            for (int i = 0; i < totalNoOfMatrices - k; i++) {
                int tillMatrixAtIdx = i + k;
                optimizedValue[i][tillMatrixAtIdx].value = calculateOptimizedValue(i, tillMatrixAtIdx).getValue();
            }
        }
    }
    //At the end pf this optimizedValue (1, n ) will contain the minumum of something


    public static int optimalSolution(int totalNoOfMatrices) {
        fillWithOptimalValues(0, totalNoOfMatrices);
        return optimizedValue[0][totalNoOfMatrices - 1].value; //or use getValue
    }

    public static void main(String args[]) {
        //This is the total no of matrices dimensions.length -1
        // The dimensions array is represented as follows
        //                  4,  10,     3,      12,       20,     7
        //which translates to
        //               4X10,   10X3,   3X12,   12X20,   20X7
        //meaning matrix A0 has dimensions P0 X P1
        //meaning matrix A1 has dimensions P1 X P2
        //meaning matrix Ai has dimensions Pi X Pi+1

        int totalNoOfMatrices = dimensions.length - 1;//n
        initMatrices(totalNoOfMatrices);
        System.out.println("This minimum cost of matrix multiplication is " + optimalSolution(totalNoOfMatrices));


    }


}
