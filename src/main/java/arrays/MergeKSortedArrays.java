package arrays;

import java.util.PriorityQueue;

 class QueueNode implements Comparable<QueueNode>{
	int array , index , value;

	public QueueNode(int array, int index, int value) {
		super();
		this.array = array;
		this.index = index;
		this.value = value;
		
	}

	@Override
	public String toString() {
		return "QueueNode [array=" + array + ", index=" + index + ", value="
				+ value + "]";
	}

	@Override
	public int compareTo(QueueNode o) {
		if (this.value > o.value ) return 1; 
		else if (this.value < o.value) return -1; //input is greatr
		return 0;
	}
	
}
 
 
//Merge k sorted arrays into one sorted array 
public class MergeKSortedArrays {
	
	public static void main (String args [] ){
		int arrays[][] = {{1,4,7}, {2,5,8}, {3,6,9,10}}; //Take this inpur from main using args
		System.out.println(mergeArray(arrays) );
	}

	public static int[] mergeArray (int arrays [][] ){
		int size = 0;
		PriorityQueue<QueueNode> pq = new PriorityQueue<QueueNode>();

		for (int i = 0 ; i < arrays.length; i ++ ) {
			size = size + arrays[i].length;
			if (arrays[i].length > 0 ) pq.add(new QueueNode(i, 0, arrays[i][0]));
		}
		
		int [] result = new int [size];
		
		for ( int i = 0; ! pq.isEmpty() ; i++){
			QueueNode qnmin = pq.poll();
			int arrayIndex = qnmin.array;
			int valIndex = qnmin.index;
			int newIndex= valIndex +1;
			result[i] = qnmin.value;
			//Insert the newIndex into prioroty Q
			if (newIndex < arrays[arrayIndex].length){
				QueueNode qn = new QueueNode(arrayIndex, newIndex, arrays[arrayIndex][newIndex]);
				pq.add(qn);

			}
		}
		
		return result;
	}
	
}
