//import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.In;


public class Percolation {
    private WeightedQuickUnionUF uf;
    private int greedSize;
    private boolean[] openSites;


    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException("n = " + n + " is <= 0");
        greedSize = n;
        uf = new WeightedQuickUnionUF(n * n + n * 2);
        openSites = new boolean[n * n + n * 2];
        for (int i = 0; i < openSites.length; i++) {
            if (i < n || i >= openSites.length - n) {
                openSites[i] = true;
                if ((i > 0 && i < n) || (i > openSites.length - n)) {
                    uf.union(i, i - 1);
                }
            }
        }
    }

    public void open(int row, int col) {
        isIndexValid(row, col);
        if (!isOpen(row, col)) openSites[xyTo1D(row, col)] = true;
        unionWithNaighbors(row, col);
    }

    private void unionWithNaighbors(int row, int col) {
        int ind = xyTo1D(row, col);
        int indR = ind + 1;
        int indL = ind - 1;
        int indB = ind + greedSize;
        int indT = ind - greedSize;
        if (openSites[indB]) uf.union(ind, indB);
        if (openSites[indT]) uf.union(ind, indT);
        if (col >= 1 && col < greedSize) {
            if (openSites[indR]) uf.union(ind, indR);
        }
        if (col > 1 && col <= greedSize) {
            if (openSites[indL]) uf.union(ind, indL);
        }
    }

    public boolean isOpen(int row, int col) {
        isIndexValid(row, col);
        return openSites[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        isIndexValid(row, col);
        if (uf.connected(0, xyTo1D(row, col))) return true;
        return false;
    }

    public int numberOfOpenSites() {
        int numberOfOpenSites = 0;
        for (int i = 0; i < openSites.length; i++) {
            if (i >= greedSize && i < openSites.length - greedSize && openSites[i]) numberOfOpenSites++;
        }
        return numberOfOpenSites;
    }

    public boolean percolates() {
        if (uf.connected(0, openSites.length - 1)) return true;
        return false;
    }

    private int xyTo1D(int row, int col) {
        isIndexValid(row, col);
        return greedSize * row + col - 1;
    }

    private void isIndexValid(int row, int col) {
        if (row <= 0 || row > greedSize) throw new IndexOutOfBoundsException("row index " + row + " out of bounds");
        if (col <= 0 || col > greedSize) throw new IndexOutOfBoundsException("col index " + col + " out of bounds");
    }

    public static void main(String[] args) {   // test client (optional)
//        System.out.println(6 % 5);
//        Percolation p = new Percolation(5);
//        for (boolean i : p.treshhold) System.out.println(i);

//        System.out.println(p.uf.connected(p.xyTo1D(1, 1), p.xyTo1D(1, 2)));
//        System.out.println(p.uf.count());
//        p.open(1, 1);
//        System.out.println(p.uf.connected(p.xyTo1D(1, 1), p.xyTo1D(1, 2)));
//        System.out.println(p.uf.count());
//        p.open(1, 2);
//        System.out.println(p.uf.connected(p.xyTo1D(1, 1), p.xyTo1D(1, 2)));
//        System.out.println(p.uf.count());
//        System.out.println("*** " + p.uf.connected(30, 34));
//        int[] a = {1, 2, 3};
//        int[] b = {4, 5, 6};

//        System.out.println(a + b);

    }
}