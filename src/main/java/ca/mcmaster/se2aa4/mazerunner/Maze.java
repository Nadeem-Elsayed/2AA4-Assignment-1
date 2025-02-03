package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class Maze {
    private ArrayList<ArrayList<Space>> maze = new ArrayList<ArrayList<Space>>();
    private int row_to_create = -1;
    private Position start;
    private Position end;
    public Maze() {
        maze.add(new ArrayList<Space>());
    }
    //add a wall or path to the maze row
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
    //add a new row to the maze
    public void addRow(){
        //first check if current row is completely populated till the end
        if (maze.size()>2) {
            while (maze.get(row_to_create).size()!=maze.get(row_to_create-1).size()) {
                addElement(' ');
            }
        }
        maze.add(new ArrayList<Space>());
        row_to_create++;
    }
    //use when finished describing maze to find start and end
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
    //get the element in a certain position, useful for checking if a certain spot is a wall or not
    public Space getElement(Position position){
        return maze.get(position.getPosY()).get(position.getPosX());
    }
    public Position getStart(){
        return new Position(start.getPosX(), start.getPosY());
    }
    public Position getEnd(){
        return new Position(end.getPosX(), end.getPosY());
    }
    //for debugging purposes
    public void printMaze(){
        for (int i = 0; i < maze.size(); i++) {
            try {
                for (int j = 0; j < maze.get(i).size(); j++) {
                    System.out.print("-");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
