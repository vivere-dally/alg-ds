package tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class HeapTest {

    @Test
    void add_minHeap() {
        // Arrange
        Heap<Integer> heap = new Heap<Integer>(Comparator.naturalOrder());

        // Act
        heap.add(1);
        heap.add(2);

        // Assert
        Assertions.assertEquals(heap.size(), 2);
        Assertions.assertEquals(heap.top(), 1);
    }

    @Test
    void add_maxHeap() {
        // Arrange
        Heap<Integer> heap = new Heap<Integer>(Comparator.reverseOrder());

        // Act
        heap.add(1);
        heap.add(2);

        // Assert
        Assertions.assertEquals(heap.size(), 2);
        Assertions.assertEquals(heap.top(), 2);
    }

    @Test
    void remove_minHeap() {
        // Arrange
        Heap<Integer> heap = new Heap<Integer>(Comparator.naturalOrder());

        // Act
        heap.add(1);
        heap.add(2);
        heap.remove();

        // Assert
        Assertions.assertEquals(heap.size(), 1);
        Assertions.assertEquals(heap.top(), 2);
    }

    @Test
    void remove_maxHeap() {
        // Arrange
        Heap<Integer> heap = new Heap<Integer>(Comparator.reverseOrder());

        // Act
        heap.add(1);
        heap.add(2);
        heap.remove();

        // Assert
        Assertions.assertEquals(heap.size(), 1);
        Assertions.assertEquals(heap.top(), 1);
    }

    @Test
    void isEmpty_true() {
        // Arrange
        Heap<Integer> heap = new Heap<Integer>(Comparator.reverseOrder());

        // Act

        // Assert
        Assertions.assertTrue(heap.isEmpty());
    }

    @Test
    void isEmpty_false() {
        // Arrange
        Heap<Integer> heap = new Heap<Integer>(Comparator.reverseOrder());

        // Act
        heap.add(1);

        // Assert
        Assertions.assertFalse(heap.isEmpty());
    }

    @Test
    void stress_minHeap() {
        // Arrange
        Heap<Integer> heap = new Heap<Integer>(Comparator.naturalOrder());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());

        Random random = new Random();
        int[] numbers = random.ints(10000, 10, 10000).toArray();

        // Act & Assert
        for (int number : numbers) {
            heap.add(number);
            pq.add(number);

            Assertions.assertEquals(heap.top(), pq.peek());
        }

        Assertions.assertArrayEquals(pq.toArray(), heap.toArray());

        while (!heap.isEmpty()) {
            Assertions.assertEquals(pq.poll(), heap.remove());
        }
    }
}