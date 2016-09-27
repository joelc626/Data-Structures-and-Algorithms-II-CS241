/* Joel Castro
 * CS241 - Data Structures and Algorithms II
 * Project 3
 * Driver: Sorter.java
 *
 * Implement Selection, Heap, Merge and QuickSort.
 * Test them on random arrays of increasing orders of magnitude
 * to examine their scaling behavior.
 */

public  class SelectionSort implements SortAlgorithm {
    private SortTimer t;

    public void sort(double[] a, SortTimer t) {
        for(int i = a.length - 1; i > 0; i--){
            int maxIndex = 0;

            for(int j = 0; j <= i; j++) {
                t.addComparison();
                if(a[maxIndex] < a[j])
                    maxIndex = j;
            }
            swap(a, i, maxIndex);
            t.addMoves(3); //swap is 3 moves
        }
    }

    public void swap(double[] a, int i, int max) {
        double temp = a[i];
        a[i] = a[max];
        a[max] = temp; 
    }
}
