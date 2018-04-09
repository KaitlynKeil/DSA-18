import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    private LinkedList<Board> neighbors;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        neighbors = new LinkedList<>();
        tiles = b;
        n = b.length;
        goal = new int[n][n];

        int counter = 1;
        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++){
                goal[x][y] = counter;
                counter++;
            }
        }
        goal[n-1][n-1] = 0;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    /*
     * Size of the board
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int dist = 0;
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                if(tiles[x][y] != goal[x][y] && tiles[x][y] != 0) {
                    int target = tiles[x][y] -1;
                    int dest_row = target/n;
                    int dest_col = target%n;
                    dist += Math.abs((x-dest_col)) + Math.abs(y-dest_row);
                }
            }
        }
        return dist;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        return Arrays.deepEquals(tiles, goal);
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     * Rules:
     *
     */
    public boolean solvable() {
        // TODO: Your code here
        // TODO: NICK


        int parity = 0;
        int blankRow = 0; // the row with the blank tile


        for (int i = 0; i < n*n; i++){
            if(tiles[i/n][i%n] == 0){
                blankRow = i/3;
                continue;
            }
            for (int j = i + 1; j < n*n; j++) {
                if (tiles[i/n][i%n] > tiles[j/n][j%n] && tiles[j/n][j%n] != 0) {
                    parity++;
                }
            }
        }



        // solvability conditions:
        if (n % 2 == 0) { // even grid
            if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
                return parity % 2 == 0;
            } else { // blank on even row; counting from bottom
                return parity % 2 != 0;
            }
        } else { // odd grid
            return parity % 2 == 0;
        }
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        LinkedList<Board> states = new LinkedList<>();
        LinkedList<int[]> neighbors = neighborFinder();
        int[] curr_loc = blankFinder();

        for(int[] temp : neighbors){
            tiles[curr_loc[0]][curr_loc[1]] = tiles[temp[0]][temp[1]];
            tiles[temp[0]][temp[1]] = 0;
            states.add(new Board(deepCopy(tiles)));
            tiles[temp[0]][temp[1]] = tiles[curr_loc[0]][curr_loc[1]];
            tiles[curr_loc[0]][curr_loc[1]] = 0;

        }
        return states;
    }

    /*
        Makes a copy of the current 2d array
     */
    private int[][] deepCopy(int[][] matrix){
        int [][] myInt = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            myInt[i] = matrix[i].clone();
        return myInt;
    }


    /*
    Find the blank tile (returns int array of [x, y] if exists)
     */

    private int[] blankFinder(){
        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++){
                if(tiles[x][y] == 0){
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

    /*
    Find the neighbors of the blank tile (those that can be moved)
     */

    private LinkedList<int[]> neighborFinder(){
        LinkedList<int[]> neighborhood= new LinkedList<>();
        int[] curr_loc = blankFinder();
        int x = curr_loc[0];
        int y = curr_loc[1];
        if(x >0){
            neighborhood.add(new int[]{x-1, y});
        }
        if(x < n-1){
            neighborhood.add(new int[]{x+1, y});
        }
        if(y > 0){
            neighborhood.add(new int[]{x, y-1});
        }
        if(y < n-1) {
            neighborhood.add(new int[]{x, y+1});
        }


        return neighborhood;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String stuff = "\n";
        for(int[] x : tiles){
            for(int y : x){
                stuff = stuff + y + " ";
            }
            stuff = stuff + "\n";
        }
        return stuff;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
        for(Board x : it){
            System.out.println(x.toString());
        }
        System.out.println("\n\n\n\n");

        int[][] initState2 = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
        Board board2 = new Board(initState2);

        System.out.println("Size: " + board2.size());
        System.out.println("Solvable: " + board2.solvable());
        System.out.println("Manhattan: " + board2.manhattan());
        System.out.println("Is goal: " + board2.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it2 = board2.neighbors();
        for(Board x : it2){
            System.out.println(x.toString());
        }
    }
}
