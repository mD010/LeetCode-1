/*
	Reverse Nodes in k-Group 
	Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
	If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
	You may not alter the values in the nodes, only nodes itself may be changed.

	Only constant memory is allowed.

	For example,
	Given this linked list: 1->2->3->4->5
	For k = 2, you should return: 2->1->4->3->5
	For k = 3, you should return: 3->2->1->4->5
	Tags: LinkedList
*/


public class Solution {
	 public ListNode reverseKGroup(ListNode head, int k) {
	 	if (head == null || k == 1)
	 		return head;
	 	ListNode dummy = new ListNode(0);
	 	dummy.next = head;
	 	int count = 0;
	 	ListNode pre = dummy;
	 	ListNode cur = head;
	 	while (cur != null) {
	 		count ++;
	 		ListNode next = cur.next;
	 		if (count == k) {
	 			pre = reverse(pre, next);
	 			count = 0;
	 		}
	 		cur = next;
	 	}
	 	return dummy.next;
	 }
    /**
     * Reverse a link list between pre and next exclusively
     * an example:
     * a linked list:
     * 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * |    |    |         |   
     * pre last cur       next
     * 
     * 
     * 0 -> 2 -> 1 -> 3 -> 4 -> 5 -> 6
     * |         |    |    | 
     * pre     last  cur  next
     *
     * 0 -> 3 -> 2 -> 1 -> 4 -> 5 -> 6
     * |         	  |    | 
     * pre    		 last  next(cur)
	 *	last 一直指向要交换的原先K个节点的首节点，不断移动cur，将cur.next到pre.next，
	 *	所以总体上看 只有cur在动
     * @param pre 
     * @param next
     * @return the reversed list's last node, which is the precedence of parameter next
     */

     //方法代表reverse所有在pre和next之间的结点，不包括pre和next
     //每一次while循环，last就指向交换后的 K个结点的 头一个
	 private static ListNode reverse(ListNode pre, ListNode next) {
	 	ListNode last = pre.next;
	 	ListNode cur = last.next;
	 	while (cur != next) {
	 		last.next = cur.next;
	 		cur.next = pre.next;
	 		pre.next = cur;
	 		cur = last.next;
	 	}
	 	return last
	 }
}