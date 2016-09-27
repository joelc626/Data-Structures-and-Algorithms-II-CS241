/* Joel Castro
 * CS241 - Data Structures and Algorithms II
 * Project 3
 * Driver: Sorter.java
 *
 * Implement Selection, Heap, Merge and QuickSort.
 * Test them on random arrays of increasing orders of magnitude
 * to examine their scaling behavior.
 */

import java.util.Random;

public class QuickSort implements SortAlgorithm {
    Random rand = new Random();
    private SortTimer t;

    public void sort(double[] a, SortTimer t) {
        this.t = t;
        sort(a, 0, a.length - 1);

    }

    void sort(double[] a, int p, int q) {
        if (p < q) {
            int pivotIndex = p + rand.nextInt(q + 1 - p);
            int pos = partition(a, p, q, pivotIndex);
            sort(a, p, pos - 1);
            sort(a, pos + 1, q);
        }
    }

    int partition(double[] a, int left, int right, int pivotIndex) {
        double pivotElement = a[pivotIndex];

        //Move pivot to the end
        swap(a, pivotIndex, right);

        //storeIndex represents the boundary between small and large elements
        int storeIndex = left;
        for(int i = left; i < right; i++) {
            t.addComparison();
            if(a[i] <= pivotElement)
                swap(a, storeIndex++, i);
        }

        //Move pivot to its final place
        swap(a, right, storeIndex);

        return storeIndex;
    }

    public void swap(double[] a, int pos1, int pos2) {
        double temp = a[pos1];
        a[pos1] = a[pos2];
        a[pos2] = temp;

        t.addMoves(3);
    }
}
