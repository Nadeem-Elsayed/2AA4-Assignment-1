package ca.mcmaster.se2aa4.mazerunner;

public abstract class Command {
    protected Maze maze;
    protected String input_path;
    public Command(Maze maze, String input_path){
        this.maze = maze;
        this.input_path = input_path;
    }
    public abstract boolean execute();
}