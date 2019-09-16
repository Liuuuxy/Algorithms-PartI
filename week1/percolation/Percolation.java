
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private int size;
    private int width;
    private int top;
    private int bottom;
    private QuickFindUF uf;
    private int[] open;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        size = n * n;
        width = n;
        open = new int[size];
        uf = new QuickFindUF(size + 2);
        top = size;
        bottom = size + 1;
    }

    private void doUnioin(int i, int j) {
        int index = ij(i, j);
        if (i == 1) {
            tryUnion(i, j, top);
        }
        if (i == width) {
            tryUnion(i, j, bottom);
        }
        if (isIn(i - 1, j)) {
            tryUnion(i - 1, j, index);
        }
        if (isIn(i + 1, j)) {
            tryUnion(i + 1, j, index);
        }
        if (isIn(i, j - 1)) {
            tryUnion(i, j - 1, index);
        }
        if (isIn(i, j + 1)) {
            tryUnion(i, j + 1, index);
        }
    }

    private boolean isIn(int i, int j) {
        return i <= width && j <= width && i > 0 && j > 0;
    }

    private void tryUnion(int i, int j, int k) {
        if (isOpen(i, j)) {
            uf.union(ij(i, j), k);
        }
    }

    private void checkBounds(int i, int j) {
        if (!isIn(i, j)) {
            throw new java.lang.IllegalArgumentException("row index i out of bounds");
        }
    }

    private int ij(int i, int j) {
        return ((i * width - width) + j) - 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBounds(row, col);
        int num = ij(row, col);
        open[num] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBounds(row, col);
        int num = ij(row, col);
        return (open[num] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkBounds(row, col);
        return uf.connected(size, ij(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return (uf.count());
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(size, size + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 2);
        p.open(2, 3);
        p.open(3, 2);
        boolean b = p.isFull(1, 1);
        StdOut.println(b);
    }
}