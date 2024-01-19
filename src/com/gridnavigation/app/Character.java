package com.gridnavigation.app;

/**
 * The Character class represents a character in a grid-based navigation system.
 * It keeps track of the character's position on the grid and provides methods
 * to move the character.
 */
public class Character {
    private Position position;
    private GridMap gridMap;

    /**
     * Constructs a Character object with the specified grid map and initial
     * position.
     *
     * @param gridMap         the grid map on which the character moves
     * @param initialPosition the initial position of the character
     */
    public Character(GridMap gridMap, Position initialPosition) {
        this.gridMap = gridMap;
        this.position = initialPosition;
    }

    /**
     * Returns the current position of the character.
     *
     * @return the current position of the character
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Moves the character in the specified direction.
     * If the new position is valid, the character's position is updated.
     * If the new position is invalid, an error message is printed.
     *
     * @param movement the movement direction
     * @return the new position of the character
     */
    public Position move(Movement movement) {
        Position newPosition = position.calculateNewPosition(movement);
        if (gridMap.isValidPosition(newPosition)) {
            position = newPosition;
        } else {
            System.out.println("An obstacle encountered. Invalid movement: " + movement);
        }
        return position;
    }
}
