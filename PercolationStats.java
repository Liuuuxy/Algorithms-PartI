/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class PercolationStats {

    private int size;
    private int t;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new java.lang.IllegalArgumentException("n is out of bounds");
        if (trials <= 0) throw new java.lang.IllegalArgumentException("trials is out of bounds");
        size = n;
        t = trials;

    }

    // sample mean of percolation threshold
    public double mean()

    // sample standard deviation of percolation threshold
    public double stddev()

    // low endpoint of 95% confidence interval
    public double confidenceLo()

    // high endpoint of 95% confidence interval
    public double confidenceHi()

    // test client (see below)
    public static void main(String[] args)

}