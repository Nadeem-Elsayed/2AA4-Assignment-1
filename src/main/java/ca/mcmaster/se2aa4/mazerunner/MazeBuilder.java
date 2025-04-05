package ca.mcmaster.se2aa4.mazerunner;

public class MazeBuilder {
    private Maze maze;
    public MazeBuilder(){
        maze = new Maze();
    }
    public MazeBuilder fillRow(String rowchars){
        for (int i = 0; i < rowchars.length(); i++) {
            maze.addElement(rowchars.charAt(i));
        }
        return this;
    }
    public MazeBuilder addRow(){
        maze.addRow();
        return this;
    }
    public Maze build(){
        return maze;
    }
}
