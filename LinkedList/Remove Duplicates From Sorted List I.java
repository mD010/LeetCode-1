/*
	Remove Duplicates from Sorted List
	Given a sorted linked list, delete all duplicates such that each element appear only once.

	For example,
	Given 1->1->2, return 1->2.
	Given 1->1->2->3->3, return 1->2->3.
	Tags: LinkedList
*/


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 public class Solution {
 	//O(n)解法
 	public ListNode deleteDuplicates(ListNode head) {
 		if (head == null || head.next == null)
 			return head;
 		LinkedList cur = new LinkedList(-1);
 		cur = head;
 		while (head != null) {
 			while (cur.next != null && cur.next.next != null) {}
 				if (cur.val = cur.next.val) {
 					cur.next = cur.next.next;
 				}
 				cur = cur.next;
 			}
 		}
 		return head;
 	}
 	//Solution3 chapter9
 	public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }
 }





