public class SinglyLinkedList<E> {

    // Private Node subclass
    private static class Node<E> {
        // Node holds a value and a pointer to the next node
        private E value;
        private Node<E> next;

        // Construct a new node with a value and the next node to point to
        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        // Getters and setters
        public E getValue() { return value; }
        public void setValue(E value) { this.value = value; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> next) { this.next = next; }
    }

    // Fields
    private int length = 0;
    private Node<E> head = null;
    private Node<E> tail = null;

    // Empty Constructor
    public SinglyLinkedList() {}

    public void addFirst(E item) {
        head = new Node<>(item, head);
        if (isEmpty())
            tail = head;
        length++;
    }

    public void addLast(E item) {
        Node<E> newLast = new Node<>(item, null);
        if (isEmpty())
            head = newLast;
        else
            tail.setNext(newLast);
        tail = newLast;
        length++;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;
        E oldHeadValue = head.getValue();
        head = head.getNext();
        length--;
        if (isEmpty())
            tail = null;
        else if (length == 1)
            tail = head;
        return oldHeadValue;
    }

    public E removeLast() {
        if (isEmpty())
            return null;
        return remove(length - 1);
    }

    public E get(int index) {
        if (index > (length - 1) || index < 0) // If index out of bounds
            return null;
        Node<E> node = getNode(index);
        return node.getValue();
    }

    private Node<E> getNode(int index) {
        if (index > (length - 1) || index < 0) // If index out of bounds
            return null;
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public void set(int index, E item) {
        if (index > (length - 1) || index < 0) // If index out of bounds
            return;
        getNode(index).setValue(item);
    }

    public E remove(int index) {
        if (index > (length - 1) || index < 0) // If index out of bounds
            return null;
        else if (index == 0) // First
            return removeFirst();
        else {
            // Point the previous node to the node after ths one to be deleted
            Node<E> before = getNode(index - 1);
            E oldValue = before.getNext().getValue();
            before.setNext(before.getNext().getNext());

            // If removing the last node, set the previous node as the new tail
            if (index == length - 1)
                tail = before;

            length--;

            if (isEmpty())
                head = null;
            else if (length == 1)
                head = tail;
            return oldValue;
        }
    }

    public int getLength() { return length; }

    public boolean isEmpty() { return length == 0; }

    public E getFirst() { 
        if (head == null)
            return null;
        return head.getValue(); 
    }

    public E getLast() {
        if (tail == null)
            return null;
        return tail.getValue(); 
    }
    
    public void print() {
        if (isEmpty())
            return;
        Node<E> n = head;
        while (n != null) {
            System.out.print(n.getValue() + " => ");
            n = n.getNext();
        }
    }

    // public static void main(String[] args) {
    //     SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    //     list.addLast(1);
    //     list.addFirst(2);
    //     list.addLast(124);
    //     list.addFirst(1829);
    //     list.addLast(-1234);
    //     list.addFirst(2891);
    //     list.addLast(-1245);
    //     list.print();
    //     System.out.println("");
    //     System.out.println("Head: " + list.getFirst());
    //     System.out.println("Tail: " + list.getLast());
    //     System.out.println("Length: " + list.getLength());
    //     System.out.println("Second-last item: " + list.get(list.getLength() - 2));
    //     list.removeFirst();
    //     list.removeLast();
    //     list.removeFirst();
    //     list.removeFirst();
    //     list.removeLast();
    //     list.set(0, 23);
    //     list.print();
    //     System.out.println("");
    //     System.out.println("Head: " + list.getFirst());
    //     System.out.println("Tail: " + list.getLast());
    //     System.out.println("Length: " + list.getLength());
    //     System.out.println("Second-last item: " + list.get(list.getLength() - 2));
    // }

}
