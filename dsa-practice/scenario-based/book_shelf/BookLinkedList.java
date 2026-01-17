package book_shelf;

public class BookLinkedList {
    private Node head;

    // Inner class to represent a node in the list
    private static class Node {
        Book data;
        Node next;

        Node(Book data) {
            this.data = data;
            this.next = null;
        }
    }

    public BookLinkedList() {
        this.head = null;
    }

    // Add a book to the end of the list
    public void add(Book book) {
        Node newNode = new Node(book);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Remove a book by title, returns true if successful
    public boolean remove(String title) {
        if (head == null) return false;

        // Check if head is the node to remove
        if (head.data.getTitle().equals(title)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.getTitle().equals(title)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Find a book by title
    public Book find(String title) {
        Node current = head;
        while (current != null) {
            if (current.data.getTitle().equals(title)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // Display all books in the list
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
    
    public boolean isEmpty() {
        return head == null;
    }
}