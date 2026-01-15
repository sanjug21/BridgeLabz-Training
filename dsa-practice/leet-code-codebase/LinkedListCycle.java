public class LinkedListCycle {
    // 141. Linked List Cycle
    // https://leetcode.com/problems/linked-list-cycle/

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        LinkedListCycle obj = new LinkedListCycle();

        // Create a list with a cycle: 3 -> 2 -> 0 -> -4 -> (back to 2)
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        head.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2; // Cycle points back to node index 1 (value 2)

        System.out.println("Has Cycle: " + obj.hasCycle(head)); // Expected: true

        // Create a list without a cycle: 1
        ListNode headNoCycle = new ListNode(1);
        System.out.println("Has Cycle (Single Node): " + obj.hasCycle(headNoCycle)); // Expected: false
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move slow pointer by 1
            fast = fast.next.next;     // Move fast pointer by 2
            if (slow == fast) {
                return true;           // Cycle detected
            }
        }
        return false;
    }
}
