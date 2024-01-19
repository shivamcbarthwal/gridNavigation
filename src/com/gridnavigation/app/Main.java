package com.gridnavigation.app;

import com.gridnavigation.app.exceptions.InvalidMovementFormatException;

import java.util.List;

import static com.gridnavigation.app.helper.FileReaderHelper.*;

public class Main {

    public static void main(String[] args) throws InvalidMovementFormatException {
        // Replace with the actual file path
        String gridFilePath = "src/carte v2.txt";
        GridMap gridMap = readGridFromFile(gridFilePath);
        if (gridMap == null) {
            System.out.println("Failed to load grid map from file.");
            return;
        }

        // Replace with the actual file path
        String characterFilePath = "src/character.txt";
        Position initialPosition = readInitialPositionFromFile(characterFilePath);
        if (initialPosition == null) {
            System.out.println("Failed to read initial position from file.");
            return;
        }

        List<Movement> movements = readMovementsFromFile(characterFilePath);
        if (movements == null) {
            System.out.println("Failed to read movements from file.");
            return;
        }

        Character character = new Character(gridMap, initialPosition);
        for (Movement movement : movements) {
            character.move(movement);
            System.out.println("Character's current position: " + character.getPosition());
        }
    }
}
