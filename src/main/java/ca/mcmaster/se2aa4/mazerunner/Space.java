package ca.mcmaster.se2aa4.mazerunner;

public abstract class Space {
    protected String representation;
    public Space(){
    }
    public String getRepresentation(){
        return representation;
    }
    public abstract boolean isWall();
    public abstract boolean isPath();
}
