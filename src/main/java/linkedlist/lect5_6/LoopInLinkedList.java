
package linkedlist.lect5_6;

//http://stackoverflow.com/questions/10265576/java-retain-information-in-recursive-function
//http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
public class LoopInLinkedList {
	private static int k = 4;
	static LLNode HEAD =PopulateLinkedList.populateLoopedLL();;

	public static void main(String args[]) {
		//System.out.println(hasLoop(n1));
		countNodesInLoop(HEAD);
		removeLoopFlyod(HEAD);
}

	//This method returns true if there is a loop in the linked list.
	// 2 pointer method
	// 1--> 15--> 12 --> 21 --> 10 --> 66 --> 77
	//					 ^ --------------------|
	public static boolean hasLoop(LLNode node) {
		LLNode slow = node;
		LLNode fast = node;
		if (slow == null ) return false;

		while ( true ){
			if (fast.next == null ) return false;
			slow = slow.next;
			fast = fast.next.next;
	        if ( slow == null || fast == null )
	        	return false;
	        if (slow == fast ){
	        	System.out.println("SLOW " + slow + " FAST "+ fast);
	        	return true;
	        }
		}
	}

	// 1--> 15--> 12 --> 21 --> 10 --> 66 --> 77
	//					 ^ --------------------|
	// http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
	public static int countNodesInLoop(LLNode node) { //node is the start node

		LLNode someNodeInLoop = findANodeInsideLoop(node);

		if ( someNodeInLoop == null ) {
			System.out.println("NO cycle found returning");
			return -1;
		}
		System.out.println(someNodeInLoop.val);

		LLNode ptr2 = someNodeInLoop;
		//Let ptr1 stay fixed.
		int noOfNodesInLoop = 0;
		while (true){
			System.out.println("node ptr2 in loop " + ptr2.val );
			ptr2= ptr2.next;
			noOfNodesInLoop ++;
			if (someNodeInLoop == ptr2) break;
		}
		System.out.println("Total nodes in loop " + noOfNodesInLoop);
	return noOfNodesInLoop;
	}

	public static void removeLoopFlyod(LLNode node){
		LLNode startNode = node;// Fix one pointer at Head
		LLNode fastNode = node;
		System.out.println("Start node " + startNode.val + " Fast Node " + fastNode.val);
		//Fix another pointer at the "noOfNodesInLoop" th position from head
		int i = 0;

		int noOfNodesInLoop = countNodesInLoop(HEAD);

		while (i < noOfNodesInLoop){ //the fast node is at noOfNodesIntheLoop + 1 position
			fastNode = fastNode.next;
			i++;
		}
		// 1--> 15--> 12 --> 21 --> 10 --> 66 --> 77
		//					 ^ --------------------|
		System.out.println("Now Start node " + startNode.val + " Fast Node " + fastNode.val);
		//Now the start node is at HEAD and the fast node is at "noOfNodesInLoop" +1 th
		//position.
		while(startNode != fastNode){
			startNode= startNode.next;
			fastNode = fastNode.next;
			System.out.println("looping Start node " + startNode.val + " Fast Node " + fastNode.val);

		}
		//At this point we have starting of the Loop.
		//So now we want to get the pointer to the last node in the loop
		while (fastNode.next != startNode) {
			fastNode = fastNode.next;
		}
		//Means that the fast Node has now reached the end . Just make the next pointer of fast Node as null
		fastNode.next= null;
	}

	// 1--> 15--> 12 --> 21 --> 10 --> 66 --> 77
	//					 ^ --------------------|
	// Function to remove loop
    // 1--> 15--> 12 --> 21 --> 10 --> 66 --> 77
    //					 ^ --------------------|
    // Function to remove loop
    static void removeLoop(LLNode inLoopNode, LLNode head) {
        LLNode ppssibleStartNode = head, someLoopNode = null;

        /* Set a pointer to the beginning of the Linked List
         and move it one by one to find the first node which
         is part of the Linked List */
        while (true) {

            /* Now start a pointer from loop_node and check
             if it ever reaches ppssibleStartNode */
            someLoopNode = inLoopNode;
            while (someLoopNode.next != inLoopNode && someLoopNode.next != ppssibleStartNode) {
                someLoopNode = someLoopNode.next;
            }

            /* If someLoopNode reahced ppssibleStartNode then there is a loop. So
             break the loop */
            if (someLoopNode.next == ppssibleStartNode) {
                break;
            }

            /* If someLoopNode did't reach ppssibleStartNode then try the next
             * node after ptr1 */
            ppssibleStartNode = ppssibleStartNode.next;
        }

        /* After the end of loop ptr2 is the last node of
         the loop. So make next of ptr2 as NULL */
        someLoopNode.next = null;
    }


    // 1--> 15--> 12 --> 21 --> 10 --> 66 --> 77
	//					 ^ --------------------|
	//http://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
	public static LLNode findANodeInsideLoop(LLNode node) {

		LLNode slow = node;
		LLNode fast = node;
		if (slow == null ) return null;
		LLNode retNode =null;
		while ( true ){
			if (fast.next == null ) return null;
			slow = slow.next;
			fast = fast.next.next;
	        if ( slow == null || fast == null )
	        	return retNode;
	        if (slow == fast ){
	        	System.out.println("SLOW " + slow.val + " FAST "+ fast.val);
	        	return slow;
	        }
		}

	}


}
