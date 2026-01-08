class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}
class DoubleNode <T> {
    T data;
    DoubleNode<T> next;
    DoubleNode<T> prev;

    public DoubleNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}


    



