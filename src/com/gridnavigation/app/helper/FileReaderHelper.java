package com.gridnavigation.app.helper;

import com.gridnavigation.app.GridMap;
import com.gridnavigation.app.Movement;
import com.gridnavigation.app.Position;
import com.gridnavigation.app.exceptions.InvalidMovementFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReaderHelper {
    /**
     * Reads the initial position from a file.
     * 
     * @param filePath the path of the file to read from
     * @return the initial position as a Position object, or null if the file does
     *         not exist or the format is invalid
     */
    public static Position readInitialPositionFromFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath), "UTF-8");
            String line = scanner.nextLine();
            scanner.close();

            // Check if the line has exactly one comma -> to avoid comma after 2nd
            // coordinate
            if (line.chars().filter(ch -> ch == ',').count() != 1) {
                System.out.println("Invalid initial position format.");
                return null;
            }

            String[] coordinates = line.split(",");
            if (coordinates.length == 2) {
                int x = Integer.parseInt(coordinates[0].trim()); // Trim leading/trailing spaces
                int y = Integer.parseInt(coordinates[1].trim()); // Trim leading/trailing spaces
                System.out.println(x + "," + y);
                return new Position(x, y);
            } else {
                System.out.println("Invalid initial position format.");
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Invalid initial position format: " + e.getMessage());
            return null;
        }
    }

    /**
     * Reads movements from a file and returns a list of Movement objects.
     * 
     * @param filePath the path of the file to read movements from
     * @return a list of Movement objects representing the movements read from the
     *         file
     * @throws InvalidMovementFormatException if the file contains an invalid
     *                                        movement format
     * 
     */
    public static List<Movement> readMovementsFromFile(String filePath) throws InvalidMovementFormatException {
        try {
            Scanner scanner = new Scanner(new File(filePath), "UTF-8");
            scanner.nextLine(); // Skip the first line
            String movements = scanner.nextLine();
            scanner.close();
            List<Movement> result = new ArrayList<>();
            for (char c : movements.toCharArray()) {
                if (java.lang.Character.isWhitespace(c)) {
                    continue; // Skip whitespace characters
                }
                switch (c) {
                    case 'N':
                        result.add(Movement.NORTH);
                        break;
                    case 'S':
                        result.add(Movement.SOUTH);
                        break;
                    case 'E':
                        result.add(Movement.EAST);
                        break;
                    case 'W':
                        result.add(Movement.WEST);
                        break;
                    default:
                        throw new InvalidMovementFormatException("Invalid movement format: " + c);

                }
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return null;
        }
    }

    /**
     * Reads a grid map from a file.
     *
     * @param filePath the path of the file to read the grid map from
     * @return the grid map read from the file, or null if the file is not found
     */
    public static GridMap readGridFromFile(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            List<String> lines = new ArrayList<>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

            int height = lines.size();
            int width = lines.get(0).length();

            // validation for inconsistent row lengths
            for (String line : lines) {
                if (line.length() != width) {
                    throw new IllegalArgumentException("Inconsistent row lengths in grid file.");
                }
            }

            GridMap gridMap = new GridMap(width, height);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    char c = lines.get(y).charAt(x);
                    boolean isPassable = (c == ' '); // ' ' represents a passable cell
                    gridMap.setCellState(x, y, isPassable ? GridMap.CellState.PASSABLE : GridMap.CellState.IMPASSABLE);
                }
            }

            return gridMap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null; // Or handle the error as appropriate
        }
    }
}
