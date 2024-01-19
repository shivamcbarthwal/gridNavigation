package com.gridnavigation.app;

/**
 * The Position class represents a position in a grid.
 * It stores the x and y coordinates of the position.
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructs a Position object with the specified x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x coordinate of the position.
     *
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate of the position.
     *
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x coordinate of the position.
     *
     * @param x the new x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y coordinate of the position.
     *
     * @param y the new y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Calculates a new position based on the given movement.
     *
     * @param movement the movement direction
     * @return the new position
     */
    public Position calculateNewPosition(Movement movement) {
        switch (movement) {
            case NORTH:
                return new Position(x, y - 1);
            case SOUTH:
                return new Position(x, y + 1);
            case EAST:
                return new Position(x + 1, y);
            case WEST:
                return new Position(x - 1, y);
            default:
                return new Position(x, y);
        }
    }

    /**
     * Checks if this position is equal to the given position.
     *
     * @param position the position to compare with
     * @return true if the positions are equal, false otherwise
     */
    public boolean equals(Position position) {
        return x == position.getX() && y == position.getY();
    }

    /**
     * Returns a string representation of the position.
     *
     * @return a string representation of the position
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
