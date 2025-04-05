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
    //hugs the right wall and searches for path
    //player keeps track of path
    public String solveCanonical(){
        try {
            boolean hadRight = false;
            while (!player.getPosition().equals(end)) {
                //printMaze();
                if (hadRight && !wallOnRight()) {
                    player.turnRight();
                    player.moveForward();
                    hadRight = false;
                } else if (wallAhead() && wallOnRight() && wallOnLeft()) {//if surrounded move back
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
        return player.getPlayerPath();
    }
    //finds the factorized solution
    public String solveFactorized(String canonical) {
        return convertToFactorized(solveCanonical());
    }
    //converts a canonical string to factorized counterpart
    public String convertToFactorized(String canonical){
        String filtered = canonical.replaceAll("\\s", "");
        int repeatcount = 1;
        String factorized = "";
        //print a number behind each number based on how many of it there are
        //doesn't print 1
        for (int i = 1; i < filtered.length(); i++) {
            if (filtered.charAt(i) == filtered.charAt(i-1)) {
                repeatcount++;
            } else {
                if (repeatcount > 1) {
                    factorized = factorized + repeatcount + filtered.charAt(i-1) + " ";
                } else {
                    factorized = factorized + filtered.charAt(i-1) + " ";
                }
                repeatcount = 1;
            }
        }
        if (repeatcount > 1) {
            factorized = factorized + repeatcount + filtered.charAt(filtered.length()-1) + " ";
        } else {
            factorized = factorized + filtered.charAt(filtered.length()-1);
        }
        return factorized;
    }
    //check if there is a wall on the left by sending out a test player
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
    //check if there is a wall on the right by sending out a test player
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
    //check if there is a wall ahead by sending out a test player
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
