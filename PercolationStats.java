import edu.princeton.cs.algs4.StdStats;
import java.util.Random;
//import static java.lang.Math.sqrt;
//import java.lang.Math.*;


public class PercolationStats {
    private double[] treshhold;
    private int t;

    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        t = trials;
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();
        treshhold = new double[trials];
//        System.out.println(treshhold.length);
        for (int trial = 1; trial <= trials; trial++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
//                int row = StdRandom.uniform(1, n);
//                int col = StdRandom.uniform(1, n);
                Random rand = new Random();
                int row = rand.nextInt(n) + 1;
                int col = rand.nextInt(n) + 1;
                p.open(row, col);
//                System.out.println(p.numberOfOpenSites());
//                randFinding:
//                while (true){
//                    int[] rowcol = StdRandom.permutation(12, 2);
//                    if (rowcol[0] != 0 && rowcol[1] != 0){
//                        System.out.println(rowcol[0] + " - " + rowcol[1]);
//                        break randFinding;
//                    }
//                }
            }
//            System.out.println(trial + " - " + p.numberOfOpenSites());
            treshhold[trial - 1] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {                         // sample mean of percolation threshold
        double mean = StdStats.mean(treshhold);
        return mean;
    }

    public double stddev() {                       // sample standard deviation of percolation threshold
        double stddev = StdStats.stddev(treshhold);
        return stddev;
    }

    public double confidenceLo() {            // low  endpoint of 95% confidence interval
        double confidenceLo = mean() - 1.96 * stddev()/Math.sqrt(t);
        return confidenceLo;

    }

    public double confidenceHi() {                 // high endpoint of 95% confidence interval
        double confidenceHi = mean() + 1.96 * stddev()/Math.sqrt(t);
        return confidenceHi;
    }

    public static void main(String[] args) {        // test client (described below)
//        int[] a = StdRandom.permutation(20, 2);
//        System.out.println(a[0] + " - " + a[1]);
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);
//        for (double i : ps.treshhold) {
//            System.out.println(i);
//        }
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}