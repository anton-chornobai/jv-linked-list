package core.basesyntax;

public class Node<N> {
    public Node<N> next;
    public Node<N> prev;
    public N value;
    
    public Node(N value) {
        this.value = value;
    }
}
