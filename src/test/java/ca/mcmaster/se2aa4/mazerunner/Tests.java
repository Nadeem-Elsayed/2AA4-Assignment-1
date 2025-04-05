package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class Tests {
    private Maze maze;
    private SolveCommand solver;
    private CheckCommand checker;
    private String canonical;
    private String factorized;
    private Position start;
    private Position end;
    @BeforeEach
    public void initializeMaze(){
        //maze and mazesolver
        maze = new Maze();
        maze.addRow();
        /*
         * #####
         * #   #
         * # # #
         *   #  
         * #####
         */
        maze.addElement('#');
        maze.addElement('#');
        maze.addElement('#');
        maze.addElement('#');
        maze.addElement('#');
        maze.addRow();
        maze.addElement('#');
        maze.addElement(' ');
        maze.addElement(' ');
        maze.addElement(' ');
        maze.addElement('#');
        maze.addRow();
        maze.addElement('#');
        maze.addElement(' ');
        maze.addElement('#');
        maze.addElement(' ');
        maze.addElement('#');
        maze.addRow();
        maze.addElement(' ');
        maze.addElement(' ');
        maze.addElement('#');
        maze.addElement(' ');
        maze.addElement(' ');
        maze.addRow();
        maze.addElement('#');
        maze.addElement('#');
        maze.addElement('#');
        maze.addElement('#');
        maze.addElement('#');
        solver = new SolveCommand(maze, null);
        canonical = solver.solveCanonical();
        factorized = solver.convertToFactorized(canonical);
        //player
        start = maze.getStart();
        end = maze.getEnd();
    }
    @Test
    public void testPosition(){
        Position origin = new Position(0, 0);
        assertTrue(origin.getPosX() == 0 && origin.getPosY() == 0);
    }
    @Test
    public void testSolver(){
        assertTrue(canonical.equals("FLFFRFFRFFLF"));
    }
    @Test
    public void testCanonToFactor(){
        assertTrue(factorized.equals("F L 2F R 2F R 2F L F"));
    }
    @Test
    public void testStartingDirection(){
        assertTrue(new Player(new Position(0, 0)).getDirection().equals(Direction.EAST));
    }
    @Test
    public void testPlayerMovement(){
        //player starting position is start and they are facing east
        Player tempPlayer = new Player(new Position(0, 0));
        tempPlayer.moveForward();
        assertTrue(tempPlayer.getPosition().equals(new Position(1, 0)));
    }
    @Test
    public void testMazeEndPoints(){
        assertTrue((start.getPosX() == 0 && start.getPosY() == 3) && end.getPosX() == 4 && end.getPosY() == 3);
    }
    @Test
    public void testCheckerTrue(){
        checker = new CheckCommand(maze, canonical);
        assertTrue(checker.isProper());
    }
    @Test
    public void testCheckerFalse(){
        checker = new CheckCommand(maze, "FF");
        assertFalse(checker.isProper());
    }
    @Test
    public void testElementPath(){
        assertTrue(maze.getElement(end).isPath());
    }
    @Test
    public void testElementWall(){
        Position wall = new Position(start.getPosX(), start.getPosY()-1);
        assertTrue(maze.getElement(wall).isWall());
    }
}
