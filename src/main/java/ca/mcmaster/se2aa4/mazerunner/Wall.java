package ca.mcmaster.se2aa4.mazerunner;

public class Wall extends Space{

    public Wall() {
        super();
        super.representation = "#";
    }
    public boolean isWall() {
        return true;
    }
    public boolean isPath() {
        return false;
    }
}
