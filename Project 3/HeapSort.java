/* Joel Castro
 * CS241 - Data Structures and Algorithms II
 * Project 3
 * Driver: Sorter.java
 *
 * Implement Selection, Heap, Merge and QuickSort.
 * Test them on random arrays of increasing orders of magnitude
 * to examine their scaling behavior.
 */

public class HeapSort implements SortAlgorithm {
    private SortTimer t;
    int size;
    private double[] heap;

    public void sort(double[] a, SortTimer t) {
        this.t = t;
        size = a.length;
        heap = new double[size];
        for(int i = 0; i < size; i++) {
            heap[i] = a[i];
            t.addMove();
        }

        heapify(heap);

        //fixed array
        for (int j = 0; j < a.length; j++) {
            a[j] = poll();
            t.addMove(); //moving/returning new list
        }
    }

    //running at O(size), convert input array arr into a heap
    private void heapify(double[] a) {
        int lastP = size / 2 - 1; //lastP = index of last node with at least one child
        for(int i = lastP; i >= 0; i--) //for i = lastP down to 0
            siftDown(i);
    }

    //sift down node at position a as needed to restore heap property}
    private void siftDown(int i) {
        int b = i;

        while((b < size / 2) && (heap[b] > heap[smallerChild(b)])) {
            int sC = smallerChild(b);

            t.addComparison();
            swap(b, sC);
            b = sC;
        }

        //get correct comparison count even if (b < size / 2) == true
        if((b < size / 2) && (heap[b] > heap[smallerChild(b)]) == false)
            t.addComparison();
    }

    //delete and return minimum queue element and suitably reorganize queue
    public double poll() {
        double e = heap[0];

        swap(0, size - 1);
        size--; //delete last element
        siftDown(0);
        return e;
    }

    //return the index of the smaller child of a parent
    public int smallerChild(int i) {
        if((2 * i + 2 < size) && (heap[2 * i + 2] < heap[2 * i + 1])) {
            t.addComparison();
            return (2 * i + 2);
        }

        //get correct comparison count even if (2 * i + 2 < size)
        if((2 * i + 2 < size) && (heap[2 * i + 2] < heap[2 * i + 1]) == false)
            t.addComparison();
        
        return (2 * i + 1);
    }

    //swap the first and last element
    public void swap(int pos1, int pos2) {
        double tmp;

        tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
        
        t.addMoves(3); //swap adds 3 moeves
    }
}

