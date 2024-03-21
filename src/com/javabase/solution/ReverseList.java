package com.javabase.solution;

public class ReverseList {

    /**
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head){

        ListNode prev = head;
        ListNode current = head.next;
        prev.next = null;
        while(current != null){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    public static ListNode recursionList(ListNode head){

        if(head == null || head.next == null){
            return head;
        }

        ListNode next = recursionList(head.next);
        head.next.next = head;
        head.next = null;

        return next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode item = l1;
        while(item != null){
            System.out.print(item.val);
            item = item.next;
            if(item != null){
                System.out.print("->");
            }
        }

        /*System.out.println();

        item = reverseList(l1);
        while(item != null){
            System.out.print(item.val);
            item = item.next;
            if(item != null){
                System.out.print("->");
            }
        }*/

        System.out.println();

        item = recursionList(l1);
        while(item != null){
            System.out.print(item.val);
            item = item.next;
            if(item != null){
                System.out.print("->");
            }
        }

    }
}
