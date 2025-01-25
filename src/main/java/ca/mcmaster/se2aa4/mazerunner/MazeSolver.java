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
        maze.detectEndPoints();
        try {
            int runs = 5;
            while (!player.getPosition().equals(end) && runs > 0) {
                runs--;
                System.out.println("pos: " + player.getPosition().getPosX() + player.getPosition().getPosY());
                System.out.println(player.getPlayerPath());
                System.out.println("ahead, right, left: " + wallAhead() + wallOnRight() + wallOnLeft());
                if (wallAhead() && wallOnRight() && wallOnLeft()) {//if surrounded move back
                    player.turnRight();
                    player.turnRight();
                } else if (wallAhead() && wallOnRight()){
                    player.turnLeft();
                    player.moveForward();
                } else if (wallAhead() && wallOnLeft()){
                    player.turnRight();
                    player.moveForward();
                } else if (wallOnLeft() && wallOnRight()){
                    player.moveForward();
                } else if (wallAhead()){
                    player.turnRight();
                    player.moveForward();
                } else if (wallOnLeft()){
                    player.moveForward();
                } else if (wallOnRight()) {
                    player.moveForward();
                } else {//no walls around
                    player.turnRight();
                    player.moveForward();
                }
            }
        } catch (Exception e) {

        }
        if (player.getPosition().equals(end)) {
            System.out.println("Yippee!");
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
}
