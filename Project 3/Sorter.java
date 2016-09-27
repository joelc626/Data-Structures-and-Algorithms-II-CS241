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

public abstract class Sorter implements SortAlgorithm {
    public static void main(String[] args) {
        String tmp = null;
        int p = 0;

        try {
            tmp = args[0];
            p = Integer.parseInt(args[1]);
        }
        catch (ArrayIndexOutOfBoundsException e) { 
            System.out.println("Usage: java Sorter method[shmq] power[1-6]");
            System.out.println("(selection, heap, merge, quick)");
            System.exit(0);
        }

        catch (NumberFormatException e) {
            System.out.println("Usage: java Sorter method[shmq] power[1-6]");
            System.out.println("(selection, heap, merge, quick)");
            System.exit(0);
        }

        char m = tmp.charAt(0);

        SortAlgorithm s = null;
        SortTimer t = new SortTimer();

        //Step 1. Select sorting algorithm
        switch(m) {
            case 's':
                s = new SelectionSort();
                System.out.println("SelectionSort");
                break;
            case 'h':
                s = new HeapSort();
                System.out.println("HeapSort");
                break;
            case 'm':
                s = new MergeSort();
                System.out.println("MergeSort");
                break;
            case 'q':
                s = new QuickSort();
                System.out.println("QuickSort");
                break;
            default:
                System.out.println("ERROR! " + tmp + " Not a method option!");
                System.exit(0);
        }

        //Step 2. Create and sort array
        System.out.println("\tn \tmicroseconds \tcomparisons \tmoves");

        double[] a;
        for(int i = 1; i <= p; i++) {
            int n = (int)Math.pow(10, i);
            long time = 0, comp = 0, move = 0;

            //do 5 trials and take average
            for (int j = 0; j < 5; j++) {
                a = randomArray(n);
                t.reset();
                s.sort(a, t);
                if(!verify(a)) {
                    System.out.println("SORT ERROR!");
                    System.exit(1);
                }

                time += t.getElapsedTime() / 5000;
                comp += t.getComparisons() / 5;
                move += t.getMoves() / 5;
            }
            //print results 
            System.out.printf("%9d%19d%15d%10d\n", n, time, comp, move);
        }
    }

    public static double[] randomArray(int size) {
        double[] tmp = new double[size];
        Random rand = new Random();
        for(int i =0; i < size; i++)
            tmp[i] = rand.nextDouble();
        return tmp;
    }

    //verify the array is sorted correctly from smallest to largest
    public static boolean verify(double[] a) {
        for(int i = 0; i < a.length - 1; i++)
            if(a[i] > a[i + 1])
                return false;
        return true;
    }
}
