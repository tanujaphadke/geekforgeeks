package zsjsu.lecture1;

import java.util.Arrays;

//Merges two sorted arrays
//Two arrays [3,4,5,8 ] and [1,2,6,7] are given
public class Merge {

    public static int[] merge (int[] A, int B[]){
        int[] result = new int[A.length + B.length];
        int i = 0,j=0,k=0;
        while ( true){
            if (j >= B.length || i >= A.length ) break;
            if (A[i] > B[j] ){
                result[k++] = B[j++];
            }else if (A[i] < B[j] ){
                result[k++] = A[i++];
            }else { //means both are equal
                result[k++] =A[i++];
                result[k++] =B[j++];
            }
        }
        if ( i >= A.length ){
            //copy remaining B
            for (int m = j ; m < B.length; m++){
                result[k++] = B[m];
            }
        } else { //Copy remaining A
            for (int m = i ; m < A.length; m++){
                result[k++] = A[m];
            }

        }
        return result;
    }
    public static void main( String args[]){
        //int [] A = {3,4,5,8 } ; int[] B= {1,2,6,7};
        //int [] A = {3 } ; int[] B= {1,2,6,7};
        int [] A = {3,4,5,8 } ; int[] B= {1,2};

        int [] result = merge(A,B);
        Arrays.stream(result).forEach((x) -> System.out.print( x +", "));
    }


}
