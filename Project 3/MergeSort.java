/* Joel Castro
 * CS241 - Data Structures and Algorithms II
 * Project 3
 * Driver: Sorter.java
 *
 * Implement Selection, Heap, Merge and QuickSort.
 * Test them on random arrays of increasing orders of magnitude
 * to examine their scaling behavior.
 */

public class MergeSort implements SortAlgorithm {
    private SortTimer t;

    public void sort(double[] a, SortTimer t) {
        this.t = t;
        sort(a, 0, a.length - 1); 
    }

    void sort(double[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(a, left, mid);
            sort(a, mid + 1, right);
            merge(a, left, mid, right);      
        }
    }

    void merge(double[] a, int p, int m, int q) {
        t.addComparison();
        if(a[m] <= a[m + 1])
            return; //meaning a[p...q] is already sorted
        int left = p, right = m + 1, fresh = 0;
        double[] tempArr = new double[q + 1 - p];
        
        while(left <= m && right <= q) {
            t.addComparisons(2);
            if (a[left] <= a[right]) {
                tempArr[fresh++] = a[left++];
                t.addMove();
            }
            if (a[left] >= a[right]) {
                tempArr[fresh++] = a[right++];
                t.addMove();
            }
        }

        if (left <= m) {
                  //(copyFrom, startPos, copyTo, desPos, int length)
            System.arraycopy(a, left, a, left + q - m, m + 1 - left);
            //shift a[left...m] right by (q - m) positions to a[left + q - m...q]
            t.addMoves(m + 1 - left);
        }

        System.arraycopy(tempArr, 0, a, p, fresh);
        //copy tempArr[0...fresh - 1] to a[p...p + fresh - 1]
        t.addMoves(fresh);
    }
}
