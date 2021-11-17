package tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<T> {
    private final Comparator<? super T> comparator;
    private final List<T> nodes;
    private int size;

    public Heap(Comparator<? super T> comparator) {
        this.comparator = comparator;
        this.nodes = new ArrayList<>();
        this.size = 0;
    }

    /**
     * Adds a new element. The element is added at the end of the heap. Then it is checked against his parents until the
     * constraint is respected (ascending, descending).
     * @param t the element to be added
     * @implSpec T: O(log(n))
     */
    public void add(T t) {
        // Boundary check
        if (this.nodes.size() > size) {
            this.nodes.set(size, t);
        } else {
            this.nodes.add(t);
        }

        int index = size;
        int parentIndex = size;
        do {
            parentIndex = Math.floorDiv(Math.max(parentIndex - 1, 0), 2);
            if (this.comparator.compare(this.nodes.get(parentIndex), this.nodes.get(index)) < 0) {
                break;
            }

            this.swap(parentIndex, index);
            index = parentIndex;
        } while (parentIndex > 0);

        size++;
    }


    /**
     * Removes the element on top (the highest priority). Then the element from the end of the heap is moved on the first
     * position then it is checked against his highest priority child until the constraint is respected (ascending,
     * descending).
     * @implSpec T: O(log(n))
     * @return the highest priority element
     */
    public T remove() {
        if (size == 0) {
            throw new IllegalStateException("The heap is empty");
        }

        T returnValue = this.nodes.get(0);
        this.nodes.set(0, this.nodes.get(--size));
        boolean placed = false;
        int index = 0;
        while (!placed) {
            int leftIndex = index * 2 + 1;
            if (leftIndex >= size) {
                break;
            }

            T leftChild = this.nodes.get(leftIndex);
            T child = leftChild;
            int childIndex = leftIndex;

            int rightIndex = index * 2 + 2;
            if (rightIndex < size) {
                T rightChild = this.nodes.get(rightIndex);
                if (this.comparator.compare(leftChild, rightChild) > 0) {
                    child = rightChild;
                    childIndex = rightIndex;
                }
            }

            if (this.comparator.compare(this.nodes.get(index), child) > 0) {
                this.swap(index, childIndex);
                index = childIndex;
            }
            else {
                placed = true;
            }
        }

        return returnValue;
    }

    public T top() {
        if (size == 0) {
            return null;
        }

        return this.nodes.get(0);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public T[] toArray() {
        return (T[]) this.nodes.toArray();
    }

    private void swap(int from, int to) {
        T temp = this.nodes.get(from);
        this.nodes.set(from, this.nodes.get(to));
        this.nodes.set(to, temp);
    }
}
