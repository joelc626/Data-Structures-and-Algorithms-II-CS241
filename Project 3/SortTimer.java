/* Joel Castro
 * CS241 - Data Structures and Algorithms II
 * Project 3
 * Driver: Sorter.java
 *
 * Implement Selection, Heap, Merge and QuickSort.
 * Test them on random arrays of increasing orders of magnitude
 * to examine their scaling behavior.
 */

public class SortTimer {
    long comparisons, moves, time;

    void reset() {
        comparisons = 0;
        moves = 0;
        time = System.nanoTime();
    }

    void addComparison() {
        comparisons++;
    }   

    void addComparisons(int n) {
        comparisons += n;
    }

    void addMove() {
        moves++;
    }

    void addMoves(int n) {
        moves += n;
    }

    long getComparisons() {
        return comparisons;
    }

    long getMoves() {
        return moves;
    }

    long getElapsedTime() {
        return System.nanoTime() - time;
    }
}
