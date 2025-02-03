package ca.mcmaster.se2aa4.mazerunner;

public class Player{
    private Direction direction = Direction.EAST;
    private Position position;
    private String player_path = "";
    public Player(Position position){
        this.position = position;
    }
    public Position getPosition(){
        return position;
    }
    public void setPlayerPath(String path){
        this.player_path = path;
    }
    public String getPlayerPath(){
        return new String(player_path);
    }
    public Direction getDirection(){
        return direction;
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }
    //change position based on direction as well as current position
    public void moveForward(){
        player_path = player_path + "F";
        switch (direction) {
            case EAST:
                position.setPosX(position.getPosX()+1);
                break;
            case WEST:
                position.setPosX(position.getPosX()-1);
                break;
            case SOUTH:
                position.setPosY(position.getPosY()+1);
                break;
            case NORTH:
                position.setPosY(position.getPosY()-1);
                break;
        }
    }
    //change position based on direction as well as current position
    public void turnLeft(){
        player_path = player_path + "L";
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
        }
    }
    //change position based on direction as well as current position
    public void turnRight(){
        player_path = player_path + "R";
        switch (direction) {
            case NORTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
        }
    }
}