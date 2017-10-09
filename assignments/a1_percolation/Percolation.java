package a1_percolation;

public class Percolation {
    private int[][] id;
    private int openSites = 0;
    private int side;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        side = n;
        id = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                id[i][j] = 0;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (id[row][col] == 0) {
            id[row * col] = row * col;
            openSites++;
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return id[row][col] > 0;
    }


    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 0; i < side - 1; i++)
            if (id[row*col] == i)
                return true;

        return false;
    }


    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }


    // does the system percolate?
    public boolean percolates() {


        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}