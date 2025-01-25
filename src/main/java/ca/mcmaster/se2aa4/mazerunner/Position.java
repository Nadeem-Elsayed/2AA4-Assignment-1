package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    private int pos_x;
    private int pos_y;
    public Position(int pos_x, int pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    public int getPosX(){
        return pos_x;
    }
    public int getPosY(){
        return pos_y;
    }
    public void setPosX(int pos_x){
        this.pos_x = pos_x;
    }
    public void setPosY(int pos_y){
        this.pos_y = pos_y;
    }
}
