package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static String filepath;

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        logger.trace("** Reading CLI");
        Options options = new Options();
        options.addOption("i", true, "Whether taking a maze input or not");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            filepath = cmd.getOptionValue("i");
            
        } catch (ParseException pe) {
            logger.debug("An error has occurred");
        }
        Maze maze = new Maze();
        try {
            logger.info("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                        maze.addElement('#');
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                        maze.addElement(' ');
                    }
                }
                logger.trace(System.lineSeparator());
                maze.addRow();
            }
        } catch(Exception e) {
            logger.debug("/!\\ No file found /!\\");
            e.printStackTrace();
            logger.info("**PATH NOT COMPUTED");
            return;
        }
        logger.info("**** Computing path");
        MazeSolver maze_solver = new MazeSolver(maze);
        String canonical = maze_solver.solveCanonical();
        logger.info("PATH COMPUTED");
        logger.info("CANONICAL: " + canonical);
        logger.info("FACTORIZED: NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
