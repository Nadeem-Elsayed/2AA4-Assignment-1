package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver {
    private Maze maze;
    private Player player;
    private Position start;
    private Position end;
    public MazeSolver(Maze maze){
        this.maze = maze;
        maze.detectEndPoints();
        start = maze.getStart();
        end = maze.getEnd();
        player = new Player(start);
    }
    //TODO
    public String solveCanonical(){
        try {
            boolean hadRight = false;
            while (!player.getPosition().equals(end)) {
                if (hadRight && !wallOnRight()) {
                    player.turnRight();
                    player.moveForward();
                    hadRight = false;
                }
                if (wallAhead() && wallOnRight() && wallOnLeft()) {//if surrounded move back
                    player.turnRight();
                    player.turnRight();
                    hadRight = true;
                } else if (wallAhead() && wallOnRight()){
                    player.turnLeft();
                    player.moveForward();
                    hadRight = true;
                } else if (wallAhead() && wallOnLeft()){
                    player.turnRight();
                    player.moveForward();
                    hadRight = false;
                } else if (wallOnLeft() && wallOnRight()){
                    player.moveForward();
                    hadRight = true;
                } else if (wallAhead()){
                    player.turnRight();
                    player.moveForward();
                    hadRight = false;
                } else if (wallOnLeft()){
                    player.moveForward();
                    hadRight = false;
                } else if (wallOnRight()) {
                    player.moveForward();
                    hadRight = true;
                } else {//no walls around
                    player.turnRight();
                    player.moveForward();
                    hadRight = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (player.getPosition().equals(end)) {
        }
        return player.getPlayerPath();
    }
    public String solveFactorized(){
        return null;
    }
    public boolean wallOnLeft(){
        Player checkPlayer = new Player(new Position(player.getPosition()));
        Direction temp = player.getDirection();
        checkPlayer.setDirection(temp);
        checkPlayer.turnLeft();
        checkPlayer.moveForward();
        if (maze.getElement(checkPlayer.getPosition()).isWall()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean wallOnRight(){
        Player checkPlayer = new Player(new Position(player.getPosition()));
        Direction temp = player.getDirection();
        checkPlayer.setDirection(temp);
        checkPlayer.turnRight();
        checkPlayer.moveForward();
        if (maze.getElement(checkPlayer.getPosition()).isWall()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean wallAhead(){
        Player checkPlayer = new Player(new Position(player.getPosition()));
        checkPlayer.setDirection(player.getDirection());
        checkPlayer.moveForward();
        if (maze.getElement(checkPlayer.getPosition()).isWall()) {
            return true;
        } else {
            return false;
        }
    }
    //for debugging purposes
    public void printMaze(){
        try {
            for (int i = 0; i < 12; i++) {
                try {
                    for (int j = 0; j < 100; j++) {
                        if (player.getPosition().equals(new Position(j, i))) {
                            switch (player.getDirection()) {
                                case NORTH:
                                    System.out.print("^");
                                    break;
                                case EAST:
                                    System.out.print(">");
                                    break;
                                case SOUTH:
                                    System.out.print("v");
                                    break;
                                case WEST:
                                    System.out.print("<");
                                    break;
                            }
                        } else if (maze.getElement(new Position(j,i)).isWall()){
                            System.out.print("#");
                        } else {
                            System.out.print(" ");
                        }
                    }
                } catch (Exception e) {
                }
                System.out.println();
            }
        } catch (Exception e) {
        }
    }
}
