public class MergeTwoSortedLists {
    // 21. Merge Two Sorted Lists
    // https://leetcode.com/problems/merge-two-sorted-lists/

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        MergeTwoSortedLists obj = new MergeTwoSortedLists();
        
        // List 1: 1 -> 2 -> 4
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        // List 2: 1 -> 3 -> 4
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        System.out.println("Merged List:");
        ListNode merged = obj.mergeTwoLists(l1, l2);
        printList(merged); // Expected: 1 1 2 3 4 4
    }

    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Dummy node acts as the start of the result list
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        // Attach the remaining part of the non-null list
        if (list1 != null) tail.next = list1;
        else if (list2 != null) tail.next = list2;

        return dummy.next;
    }
}
