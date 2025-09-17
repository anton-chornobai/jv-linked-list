package core.basesyntax;

public class Node<N> {
    private Node<N> next;
    private Node<N> prev;
    private N value;

    public Node(N value) {
        this.value = value;
    }

    // Getters
    public Node<N> getNext() {
        return next;
    }

    public Node<N> getPrev() {
        return prev;
    }

    public N getValue() {
        return value;
    }

    // Setters
    public void setNext(Node<N> next) {
        this.next = next;
    }

    public void setPrev(Node<N> prev) {
        this.prev = prev;
    }

    public void setValue(N value) {
        this.value = value;
    }
}
