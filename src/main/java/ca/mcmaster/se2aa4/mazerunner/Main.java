package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    private static String inputpath;

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        logger.trace("** Reading CLI");
        Options options = new Options();
        //cli for the file containing maze (-i) and one to check if a given path is correct (-p)
        options.addOption("i", true, "Whether taking a maze input or not");
        options.addOption("p", true, "Taking in path input or not");
        CommandLineParser parser = new DefaultParser();
        try {
            //retrieve the data from these arguments
            CommandLine cmd = parser.parse(options, args);
            filepath = cmd.getOptionValue("i");
            inputpath = cmd.getOptionValue("p");
            
        } catch (ParseException pe) {
            logger.debug("An error has occurred while parsing the CLI");
            System.out.println("Improper Command Line Arguments Given, terminating program");
            return;
        }
        if (filepath == null) {//can't do anything without a file path
            logger.info("No Path Provided, terminating program");
            System.out.println("No file path provided, ending program");
            return;
        }
        logger.trace("Creating Maze");

        //now that we have the file, we create a maze object using the data given
        Maze maze = new Maze();
        try {
            logger.info("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    maze.addRow();
                    for (int idx = 0; idx < line.length(); idx++) {
                        //if there is a hashtag we add a wall, otherwise we add a path
                        if (line.charAt(idx) == '#') {
                            logger.trace("WALL ");
                            maze.addElement('#');
                        } else if (line.charAt(idx) == ' ') {
                            logger.trace("PASS ");
                            maze.addElement(' ');
                        }
                    }
                    logger.trace(System.lineSeparator());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch(FileNotFoundException f) {
            logger.debug("/!\\ No file found /!\\");
            logger.info("**PATH NOT COMPUTED");
            System.out.println("Error, File Not Found, Terminating Program");
            return;
        }
        //if there is a path CLI given, check validity
        if (inputpath != null) {
            logger.info("**** Checking path validity");
            MazeChecker maze_checker = new MazeChecker(maze, inputpath);
            if (maze_checker.isProper() == true) {
                logger.info("SUCCESS, GIVEN PATH IS CORRECT");
                System.out.println("SUCCESS, GIVEN PATH IS CORRECT");
            } else {
                logger.info("FAILURE, GIVEN PATH IS INCORRECT");
                System.out.println("FAILURE, GIVEN PATH IS INCORRECT");
            }
        } else {//if we just have the maze, then find proper paths
            logger.trace("**** Computing path");
            MazeSolver maze_solver = new MazeSolver(maze);
            String canonical = maze_solver.solveCanonical();
            String factorized = maze_solver.convertToFactorized(canonical);
            logger.info("PATH COMPUTED");
            logger.info("CANONICAL: " + canonical);
            logger.info("FACTORIZED: " + factorized);
            System.out.println("PATHS CALCULATED");
            System.out.println("CANONICAL: " + canonical);
            System.out.println("FACTORIZED: " + factorized);
        }
        logger.info("** End of MazeRunner");
    }
}
