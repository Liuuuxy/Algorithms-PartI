import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int size;
    private int t;
    private double[] a;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new java.lang.IllegalArgumentException("n is out of bounds");
        if (trials <= 0) throw new java.lang.IllegalArgumentException("trials is out of bounds");
        size = n;
        t = trials;
        a = new double[size];

        for (int i = 0; i < t; i++) {
            a[i] = findPercolationThreshold();
        }
    }

    private double findPercolationThreshold() {
        Percolation p = new Percolation(size);
        int i, j;
        int count = 0;
        while (!p.percolates()) {
            do {
                i = StdRandom.uniform(size)
                        + 1; // uniform(size) is to get a random number from (0,n),but the size of the precolation should be started with 1
                j = StdRandom.uniform(size) + 1;
            } while (p.isOpen(i, j));
            count++;
            p.open(i, j);
        }
        return (double) count / (size * size);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(a);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (t == 1) return Double.NaN;
        return StdStats.stddev(a);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(t);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = 200;
        int t = 100;
        PercolationStats stats = new PercolationStats(n, t);
        StdOut.printf("mean = %f\n", stats.mean());
        StdOut.printf("stddev = %f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = %f, %f\n", stats.confidenceLo(),
                      stats.confidenceHi());
    }

}
