package ca.mcmaster.se2aa4.mazerunner;

public class Path extends Space{
    public Path() {
        super();
        super.representation = " ";
    }

    public boolean isWall() {
        return false;
    }

    public boolean isPath() {
        return true;
    }
}
