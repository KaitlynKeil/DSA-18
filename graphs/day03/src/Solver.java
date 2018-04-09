/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            if(prev != null) {
                cost = prev.moves + board.manhattan();
            }
            else {
                cost = board.manhattan();
            }
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        @Override
        public int compareTo(State x) {
            return cost - x.cost;
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        while(state.prev != null) {
            return root(state.prev);
        }
        return state;
    }

    public class StateComparator implements Comparator<State>
    {
        public int compare( State x, State y )
        {
            return x.cost - y.cost;
        }
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        solutionState = new State(initial, 0, null);
        if (isSolvable()) {
            PriorityQueue<State> open = new PriorityQueue<>();//11, new StateComparator());
            HashSet<State> closed = new HashSet<>();
            open.add(solutionState);
            while(!open.isEmpty()) {
                closed.add(solutionState);
                solutionState = open.poll();
                if(solutionState.board.isGoal()) {
                    solved = true;
                    break;
                }
                for(Board neighbor : solutionState.board.neighbors()) {
                    State newState = new State(neighbor, solutionState.moves + 1, solutionState);
                    if(!closed.contains(newState) && !open.contains(newState)) {
                        open.add(newState);
                    }
                }
            }
            minMoves = solutionState.moves;
        }

    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        Stack<Board> sol = new Stack<>();
        State state = solutionState;
        sol.add(state.board);
        while(state.prev != null) {
            state = state.prev;
            sol.add(state.board);
        }
        return sol;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
