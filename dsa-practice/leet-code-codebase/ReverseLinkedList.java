public class ReverseLinkedList {
    // 206. Reverse Linked List
    // https://leetcode.com/problems/reverse-linked-list/

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);

        ReverseLinkedList obj=new ReverseLinkedList();
        ListNode ans = obj.reverseList(head);
        
        System.out.print("Reversed List: ");
        while(ans!=null){
            System.out.print(ans.val+" ");
            ans=ans.next;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode nextTemp=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nextTemp;
        }
        return prev;
    }
}
