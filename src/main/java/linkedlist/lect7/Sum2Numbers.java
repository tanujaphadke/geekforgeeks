package linkedlist.lect7;
/*
Add Two Numbers
https://leetcode.com/problems/add-two-numbers/
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

 */

import linkedlist.lect5_6.LLNode;

public class Sum2Numbers {


    public static double addTwoNumbersHelper(LLNode l1, LLNode l2) {
        double nu1 = findNum(l1, 0);
        double nu2 = findNum(l2, 0);
        return nu1 + nu2;
    }

    public static void main(String args[]) {
        LLNode head1 = new LLNode(2);
        LLNode sec = new LLNode(4);
        LLNode th = new LLNode(3);
        head1.next = sec;
        sec.next = th;

        LLNode head2 = new LLNode(5);
        LLNode sec2 = new LLNode(6);
        LLNode th2 = new LLNode(4);
        LLNode f2 = new LLNode(9);

        head2.next = sec2;
        sec2.next = th2;
        th2.next = f2;

        //addTwoNumbersHelper1(head1, head2);
        addTwoNumbersAndreturnAlistHelper(head1, head2);

    }

    //    Input: l1 = [2,4,3], l2 = [5,6,4]
    //Returns Number as decimal
    public static double findNum(LLNode l1, int pw) {
        double sum = 0;
        if (l1 == null) throw new RuntimeException("List cannot be null");
        if (l1.next == null) {
            return l1.val * Math.pow(10, pw);
        }
        sum = sum + findNum(l1.next, pw + 1);
        sum = sum + l1.val * Math.pow(10, pw);
        return sum;
    }

    //Returns Sum as decimal
    public static double findSum(LLNode l1, int pw) {
        double sum = 0;
        if (l1 == null) throw new RuntimeException("List cannot be null");
        if (l1.next == null) {
            return l1.val * Math.pow(10, pw);
        }
        sum = sum + findNum(l1.next, pw + 1);
        sum = sum + l1.val * Math.pow(10, pw);
        return sum;
    }


    public static LLNode addTwoNumbersAndreturnAlistHelper(LLNode l1, LLNode l2) {
        if (l1 == null || l2 == null) throw new RuntimeException("List cannot be null");
        LLNode ll = findSumAndReturnReverseList(l1, l2, 0);
        return ll;
    }

    //    Input: l1 = [2,4,3], l2 = [5,6,4]

    /**
     * THis algo returns a linked list head of the sum with sum in reverse order
     * @param l1
     * @param l2
     * @param carry
     * @return
     */
    private static LLNode findSumAndReturnReverseList(LLNode l1, LLNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return null;
        }
        int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
        int remainder = val % 10;// For example if val = 5 + 8 = 13 then 3 vcarry 1
        int quotient = val / 10; //lowerbound
        LLNode sumnode = new LLNode(remainder);
        LLNode next = findSumAndReturnReverseList((l1 != null ? l1.next : null),
                (l2 != null ? l2.next : null), quotient);
        sumnode.next = next;
        return sumnode;
    }

}

