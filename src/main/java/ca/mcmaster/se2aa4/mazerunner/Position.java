package ca.mcmaster.se2aa4.mazerunner;

public class Position{
    private int pos_x;
    private int pos_y;
    public Position(int pos_x, int pos_y) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    public Position(Position other){
        this.pos_x = other.pos_x;
        this.pos_y = other.pos_y;
    }
    public boolean equals(Position other){
        if (this.pos_x == other.pos_x && this.pos_y == other.pos_y) {
            return true;
        } else {
            return false;
        }
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
