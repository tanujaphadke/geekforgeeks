package recursion.lect3;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
//https://en.wikipedia.org/wiki/Connected-component_labeling
//http://www.ideserve.co.in/learn/number-of-clusters-of-1s
//http://www.ideserve.co.in/learn/number-of-clusters-of-1s#algorithmVisualization
//http://www.ideserve.co.in/learn/number-of-clusters-of-1s#algorithmVisualization
//http://javabypatel.blogspot.in/2016/08/find-number-of-islands-using-dfs.html
//https://www.youtube.com/watch?v=dA1FYFlvckY
//https://www.youtube.com/watch?v=W8VuDt0eb5c
//https://www.quora.com/What-is-an-efficient-algorithm-to-find-an-island-of-connected-1s-in-a-matrix-of-0s-and-1s
public class LargestRegion {

	//Given a matrix

	static int M[][] = {  { 0, 0, 1, 1, 0 },
		            		  { 1, 0, 1, 1, 0 },
		            		  { 0, 1, 0, 0, 0 },
		            		  { 0, 0, 0, 0, 1 }
            	 };



	static boolean visited[][] =  {  	{ false, false, false, false, false },
										{ false, false, false, false, false },
										{ false, false, false, false, false },
										{ false, false, false, false, false }
								  };
	static int rows =-1;
	static int columns  = -1;

	public static void main (String args [] ){
		rows = M.length;

		for (int row = 0 ; row < rows ; row ++ ){
			int columns = M[row].length;

			for (int col = 0; col < columns; col ++ ){
				if ( M[row][col] == 0 ) continue ; //skip that entry

			}
		}

	}

	public static int findMaxRegion (){

		for (int row = 0 ; row < rows ; row ++ ){
			int columns = M[row].length;

			for (int col = 0; col < columns; col ++ ){
				if ( M[row][col] == 0 ) continue ; //skip that entry

			}
		}
		return 0;
	}

	//The below code returns the counts of the 1's of the neighbours.
	public static int howManyNeighoursAreOnes(int i, int j, int rows, int columns){
		int count = 0; int indexes [][] = new int [8][2] ; // You can also return the actual indexes or coordinates of elements which are 1
		int left=0; int right = 0; int above = 0; int below = 0;
		int aboveLeft = 0; int aboveRight = 0; int  belowLeft = 0; int belowRight =0;
		int leftIdx = 0; int rightIdx = 0; int aboveIdx = 0;
		int belowIdx = 0; int aboveLeftIdx = 0; int aboveRightIdx = 0; int  belowLeftIdx = 0;int belowRightIdx =0;
		if ( i-1 > -1 && i + 1< rows && j -1 > -1 && j +1 < columns ){
			//Find the number of 1's
			left = M [i][j-1];  right = M[i][j+1];
			above = M [i-1][j]; below = M[i+1][j];
			aboveLeft = M[i-1][j-1]; aboveRight = M[i-1][j+1];
			belowLeft = M[i+1][j-1]; belowRight = M[i+1][j+1];

		}
		else if (i==0 && j == 0 ){
			right = M[i][j+1];
			below = M[i+1][j];
			belowRight = M[i+1][j+1];
		}
		else if (i== 0 && j == columns-1){
			left = M [i][j-1];
			below = M[i+1][j];
			belowLeft = M[i+1][j-1];
		}
		else if (i == rows -1  && j == 0 ){
			right = M[i][j+1];
			above = M [i-1][j];
			aboveRight = M[i-1][j+1];
		}
		else if (i == rows -1  && j == columns -1){
			left = M [i][j-1];
			above = M [i-1][j];
			aboveRight = M[i-1][j+1];
		}
		else if (i == 0 && j > 0 && j < columns -1){
			left = M [i][j-1];
			right = M[i][j+1];
			below = M[i+1][j];
			belowLeft = M[i+1][j-1];
			belowRight = M[i+1][j+1];
		}
		else if (i == rows -1  && j > 0 && j < columns -1){
			left = M [i][j-1];
			right = M[i][j+1];
			above = M [i-1][j];
			aboveLeft = M[i-1][j-1];
			aboveRight = M[i-1][j+1];

		}
		else if (i > 0  && i < rows - 1 && j == 0 ){
			right = M[i][j+1];
			above = M [i-1][j];
			below = M[i+1][j];
			aboveRight = M[i-1][j+1];
			belowRight = M[i+1][j+1];
		}
		else if (i > 0  && i < rows - 1 && j == columns -1  ){
			left = M [i][j-1];
			above = M [i-1][j];
			aboveLeft = M[i-1][j-1];
			below = M[i+1][j];
			belowLeft = M[i+1][j-1];
		}
		count = left + right + above + below + aboveLeft + aboveRight + belowLeft + belowRight ;
		return count;
	}

	//The below code returns the counts of the 1's of the neighbours.
		public static int howManyNeighoursAreOnes2(int i, int j, int rows, int columns){
			int count = 0; int indexes [][] = new int [8][2] ; // You can also return the actual indexes or coordinates of elements which are 1

			int left=0; int right = 0; int above = 0; int below = 0; int aboveLeft = 0; int aboveRight = 0; int  belowLeft = 0; int belowRight =0;

			int leftIdx = 0; int rightIdx = 0; int aboveIdx = 0; int belowIdx = 0; int aboveLeftIdx = 0; int aboveRightIdx = 0; int  belowLeftIdx = 0;int belowRightIdx =0;

			leftIdx = j-1; rightIdx = j+1; aboveIdx = i-1 ; belowIdx = i+1 ;

			left = leftIdx >= 0 && leftIdx <= columns -1 ? M[i][leftIdx] : 0;
			right = rightIdx >= 0 && rightIdx <= columns -1 ?  M[i][rightIdx] : 0;
			above = aboveIdx >= 0 ? M[aboveIdx][j] : 0 ;
			below = belowIdx <= rows -1 ? M[belowIdx][j] :  0;
			aboveRight = rightIdx <= columns -1  && aboveIdx >=0 ? M[aboveIdx][rightIdx] : 0 ;
			aboveLeft = leftIdx >= 0   && aboveIdx >=0 ? M[aboveIdx][leftIdx] : 0 ;
			belowRight = rightIdx <= columns -1 &&  belowIdx <= rows -1 ? M[belowIdx][rightIdx] : 0 ;
			belowLeft = leftIdx >=0 &&  belowIdx <= rows -1 ? M[belowIdx][leftIdx] :0;

			count = left + right + above + below + aboveLeft + aboveRight + belowLeft + belowRight ;
			return count;
		}

		public static int[][] findCoordinatesOfAllNeighbouringOnes(int i, int j, int rows, int columns){
			int count = 0; int indexes [][] = new int [8][2] ; // You can also return the actual indexes or coordinates of elements which are 1

			int left=0; int right = 0; int above = 0; int below = 0; int aboveLeft = 0; int aboveRight = 0; int  belowLeft = 0; int belowRight =0;

			int leftIdx = 0; int rightIdx = 0; int aboveIdx = 0; int belowIdx = 0; int aboveLeftIdx = 0; int aboveRightIdx = 0; int  belowLeftIdx = 0;int belowRightIdx =0;

			leftIdx = j-1; rightIdx = j+1; aboveIdx = i-1 ; belowIdx = i+1 ;

			left = leftIdx >= 0 && leftIdx <= columns -1 ? M[i][leftIdx] : 0;
			right = rightIdx >= 0 && rightIdx <= columns -1 ?  M[i][rightIdx] : 0;
			above = aboveIdx >= 0 ? M[aboveIdx][j] : 0 ;
			below = belowIdx <= rows -1 ? M[belowIdx][j] :  0;
			aboveRight = rightIdx <= columns -1  && aboveIdx >=0 ? M[aboveIdx][rightIdx] : 0 ;
			aboveLeft = leftIdx >= 0   && aboveIdx >=0 ? M[aboveIdx][leftIdx] : 0 ;
			belowRight = rightIdx <= columns -1 &&  belowIdx <= rows -1 ? M[belowIdx][rightIdx] : 0 ;
			belowLeft = leftIdx >=0 &&  belowIdx <= rows -1 ? M[belowIdx][leftIdx] :0;

			count = left + right + above + below + aboveLeft + aboveRight + belowLeft + belowRight ;
			//return count;
			return null;
		}

	Map <Window, Integer> onesSizes = new HashMap<Window, Integer>();


	public static int findAdjOnes(int row, int col ){
		//other than for row == 0 , row == rows or col = 0 or col = colums
		//the rest of the entries in the array will will 8 neighbours.
		Window w = new Window();
		if (col != 0 ) {
			if ( M[row] [col - 1] == 1)
				w.setLeft(row,col -1);
		}//left
//		if (col != columns-1 ){ M[row] [col + 1];} //right
//		if (row != 0 ){ M[row - 1] M [col] ;}//above
//		if (row != rows-1 ) M[row + 1] [col];} //below
//		if ( (row != 0 ) && (col != 0) ) {M[row - 1] [col - 1];} //above left
//		if ( (row != 0 )&& (col != columns-1) ) {[row - 1] [col + 1]; }//above right
//		if ( (row != rows -1 )&& (col != 0) )[row + 1] [col - 1] //below left
//		if ( (row != rows -1 )&& (col != columns-1) )	[row + 1] [col + 1] //below right

		return 0;
	}
}



class Window {
    // The index pairs for which a value of 1 is set in the array the rest will
    // have coordinates of -1
    int[] source = {-1, -1}; // w.r.t this point we are calculating top left ,
    // right etc..
    int[] topLeft = {-1, -1};
    int[] topright = {-1, -1};
    int[] above = {-1, -1};
    int[] below = {-1, -1};
    int[] left = {-1, -1};
    int[] right = {-1, -1};
    int[] bottomLeft = {-1, -1};
    int[] bottomRight = {-1, -1};

    public void setSource(int index0, int index1) {
        this.source[0] = index0;
        this.source[1] = index1;
    }

    public void setTopLeft(int index0, int index1) {
        this.topLeft[0] = index0;
        this.topLeft[1] = index1;
    }

    public void setTopright(int index0, int index1) {
        this.topright[0] = index0;
        this.topright[1] = index1;
    }

    public void setAbove(int index0, int index1) {
        this.above[0] = index0;
        this.above[1] = index1;
    }

    public void setBelow(int index0, int index1) {
        this.below[0] = index0;
        this.below[1] = index1;
    }

    public void setLeft(int index0, int index1) {
        left[0] = index0;
        left[1] = index1;
    }

    public void setRight(int index0, int index1) {
        this.right[0] = index0;
        this.right[1] = index1;
    }

    public void setBottomLeft(int index0, int index1) {
        this.bottomLeft[0] = index0;
        this.bottomLeft[1] = index1;
    }

    public void setBottomRight(int index0, int index1) {
        this.bottomRight[0] = index0;
        this.bottomRight[1] = index1;
    }

    public void setSize(int size) {
        this.size = size;
    }

    int size = -1;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(above);
        result = prime * result + Arrays.hashCode(below);
        result = prime * result + Arrays.hashCode(bottomLeft);
        result = prime * result + Arrays.hashCode(bottomRight);
        result = prime * result + Arrays.hashCode(left);
        result = prime * result + Arrays.hashCode(right);
        result = prime * result + size;
        result = prime * result + Arrays.hashCode(source);
        result = prime * result + Arrays.hashCode(topLeft);
        result = prime * result + Arrays.hashCode(topright);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Window other = (Window) obj;

        //For the non-zero neighbours if they exactly match the coordinates then it is a full overlap overlap
        if (!Arrays.equals(above, other.above))
            return false;
        if (!Arrays.equals(below, other.below))
            return false;
        if (!Arrays.equals(bottomLeft, other.bottomLeft))
            return false;
        if (!Arrays.equals(bottomRight, other.bottomRight))
            return false;
        if (!Arrays.equals(left, other.left))
            return false;
        if (!Arrays.equals(right, other.right))
            return false;
        if (size != other.size)
            return false;
        if (!Arrays.equals(source, other.source))
            return false;
        if (!Arrays.equals(topLeft, other.topLeft))
            return false;
        if (!Arrays.equals(topright, other.topright))
            return false;
        return true;

        //Also check for partial overlap if any one of the above matches then return 1
    }
}
