package a1_percolation;

public class Percolation {
    private int[][] id;
    private int openSites = 0;
    private int sideLength; // length of square side == N
    private int[] topRow;
    private int[] bottomRow;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n > 0) {
            sideLength = n;
            id = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    id[i][j] = 0;
        } else
            throw new IllegalArgumentException();
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        int[] left = new int[]{row, col-1};
        int[] right = new int[]{row, col+1};
        int[] up = new int[]{row-1, col};
        int[] down = new int[]{row+1, col-1};

        if((row > 0) && (col > 0)) {
            if (id[row][col] == 0) {
                id[row][col] = row * sideLength + (col + 1);
                openSites++;
            }
        }
        else
            throw new IllegalArgumentException();
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return id[row][col] > 0;
    }


    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 0; i < sideLength - 1; i++)
            if (id[row][col] == i)
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
        int[] coords;
        int r = row;
        int c = col;
        while (getIndex(r, c) != id[r][c]) {
            coords = getCoords(id[r][c]);
            r = coords[0];
            c = coords[1];
        }

        return getIndex(r, c);
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

    public void setIndex(int row, int col, int index) {
        id[row][col] = index;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}