package ca.mcmaster.se2aa4.mazerunner;

public class CheckCommand extends Command{
    private Player tester;

    public CheckCommand(Maze maze, String input_path){
        super(maze, input_path);
        super.input_path = convertToCanonized(input_path);
        maze.detectEndPoints();
    }

    /*
     * converts strings to canonized forms
     * this method can also take in a regular canonized string
     * parses numbers across multiple indices ex: 12F would have the number 12 parsed and then
     * F would be added 12 times to the canonized path
     */
    public String convertToCanonized(String factorized){
        //get rid of whitespace
        String filtered = factorized.trim();
        String canonized = "";
        int count = 0;
        int min_index = -1;
        int max_index = -1;
        for (int i = 0; i < filtered.length(); i++) {
            char c = filtered.charAt(i);
            if (c>= '0' && c <= '9') {//if number
                if (i == 0) {//beginning is special
                    min_index = 0;
                    max_index = 0;
                    //if char before also a number
                } else if (filtered.charAt(i-1) >= '0' && filtered.charAt(i-1) <= '9') {
                    max_index = i;
                } else {//standalone number in middle of string
                    min_index = i;
                    max_index = i;
                }
            } else {//char is F R or L
                if (i == 0) {
                    canonized = canonized + filtered.charAt(i);
                } else if (filtered.charAt(i-1) >= '0' && filtered.charAt(i-1) <= '9') {
                    //if the char before is a number then parse that number and add this char n number of times
                    count = Integer.parseInt(filtered.substring(min_index, max_index+1));
                    for (int j = 0; j < count; j++) {
                        canonized = canonized + filtered.charAt(i);
                    }
                } else {//standalone char in middle of string
                    canonized = canonized + filtered.charAt(i);
                }
            }
        }
        return canonized;
    }
    /*
     * checks each move to see if the player goes through a wall
     * if finished reading path at endpoint then success
     */
    public boolean isProper(){
        tester = new Player(maze.getStart());
        for (int i = 0; i < input_path.length(); i++) {
            switch (input_path.charAt(i)) {
                case 'F', 'f':
                    tester.moveForward();
                    break;
                case 'L', 'l':
                    tester.turnLeft();
                    break;
                case 'R', 'r':
                    tester.turnRight();
                    break;
                default:
                    break;
            }
            //ending up in a wall is failure
            if (maze.getElement(tester.getPosition()).isWall()) {
                return false;
            }
        }//if we reach the end return true
        if (tester.getPosition().equals(maze.getEnd())){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean execute() {
        return isProper();
    }
}
