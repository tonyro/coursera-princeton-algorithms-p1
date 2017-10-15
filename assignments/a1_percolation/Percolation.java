package a1_percolation;

public class Percolation {
    private int[] id;
    private int openSites = 0;
    private int sideLength; // length of square side == N
    private int[] topRow;
    private int[] bottomRow;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n > 0) {
            sideLength = n;
            id = new int[n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    id[getIndex(i, j)] = 0;
        } else
            throw new IllegalArgumentException();
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if((row > 0) && (col > 0)) {
            int i = getIndex(row, col);
            if (id[i] == 0) {
                id[i] = getClosestParent(row, col);
                openSites++;
            }
        }
        else
            throw new IllegalArgumentException();
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return id[getIndex(row, col)] > 0;
    }


    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 0; i < sideLength - 1; i++)
            if (id[getIndex(row, col)] == i)
                return true;

        return false;
    }


    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }


    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < sideLength; i++)
            if (isFull(sideLength, i))
                return true;

        return false;
    }

    public int root(int row, int col) {
        int i = getIndex(row, col);
        while (i != id[i])
            i = id[i];

        return i;
    }

    private int getIndex(int row, int col) {
        return row * sideLength + (col + 1);
    }

    private int[] getCoords(int index) {
        int[] coords = new int[2];
        coords[0] = index / sideLength;
        coords[1] = index - 1 - sideLength * coords[0];

        return coords;
    }

    private int getClosestParent(int row, int col) {
        int parentIndex;

        int left = getIndex(row, col-1);
        int right = getIndex(row, col+1);
        int up = getIndex(row-1, col);
        int down = getIndex(row+1, col);

        // if all surrounding sites are closed, set index based on self
        if ((id[left] == 0) && (id[right] == 0) && (id[up] == 0) && (id[down] == 0))
                return row * sideLength + (col + 1);

        return 0;
    }

    public void setIndex(int row, int col, int index) {
        id[getIndex(row, col)] = index;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}