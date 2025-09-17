package core.basesyntax;

import java.util.List;
import java.util.Objects;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        size++;
    }

    @Override
    public void add(T value, int index) {
        boundsCheckForAdd(index);

        Node<T> newNode = new Node<>(value);
        // Adding to the head or tail of the list
        if (index == 0) {
            if (head == null) {
                head = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            // adding whithin the borders of the list
            Node<T> currNode = getNode(index);
            Node<T> prev = currNode.prev;

            currNode.prev = newNode;
            newNode.next = currNode;
            newNode.prev = prev;
            prev.next = newNode;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (T item : list) {
            add(item);
        }
    }

    @Override
    public T get(int index) {

        boundsCheck(index);

        Node<T> currentNode;

        if (index < size / 2) {
            currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
        }

        return currentNode.value;
    }

    @Override
    public T set(T value, int index) {
        boundsCheck(index);
        Node<T> currentNode = getNode(index);
        T oldValue = currentNode.value;
        currentNode.value = value;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Node<T> node = getNode(index);

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        size--;
        return node.value;
    }

    @Override
    public boolean remove(T object) {
        Node<T> current = head;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(current.value, object)) {

                if (current.prev == null) {
                    head = current.next;

                    if (head != null) {
                        head.prev = null;
                    }
                }

                else if (current.next == null) {
                    current.prev.next = null;
                    tail = current.prev;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<T> getNode(int index) {
        boundsCheck(index);

        Node<T> currentNode;

        if (index < size / 2) {
            currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
        }

        return currentNode;
    }

    private void boundsCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Out of bounds;");
        }
    }

    private void boundsCheckForAdd(int index) { // for add(value, index)
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Out of bounds: " + index);
        }
    }
}
