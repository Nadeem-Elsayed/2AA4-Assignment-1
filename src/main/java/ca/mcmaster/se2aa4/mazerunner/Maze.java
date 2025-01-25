package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Maze {
    private ArrayList<ArrayList<Space>> maze = new ArrayList<ArrayList<Space>>();
    private int row_to_create = 0;
    private Position start;
    private Position end;
    public Maze() {
        maze.add(new ArrayList<Space>());
    }
    public void addElement(char element){
        switch (element) {
            case '#':
                maze.get(row_to_create).add(new Wall());
                break;
            case ' ':
                maze.get(row_to_create).add(new Path());
                break;
            default:
                break;
        }
    }
    public void addRow(){
        maze.add(new ArrayList<Space>());
        row_to_create++;
    }
    //use when finished describing maze
    public void detectEndPoints(){
        // printMaze();
        int width = maze.get(0).size();
        int height = maze.size();
        for (int i = 0; i < height-1; i++) {
            if (maze.get(i).get(0).isPath()) {
                start = new Position(0, i);
            }
            if (maze.get(i).get(width-1).isPath()){
                end = new Position(width-1, i);
            }
        }
    }
    public Space getElement(Position position){
        return maze.get(position.getPosY()).get(position.getPosX());
    }
    public Position getStart(){
        return new Position(start.getPosX(), start.getPosY());
    }
    public Position getEnd(){
        return new Position(end.getPosX(), end.getPosY());
    }
}
