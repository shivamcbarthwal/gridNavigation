package com.gridnavigation.app;

import com.gridnavigation.app.exceptions.OutsOfBoundsPositionException;

/**
 * The GridMap class represents a grid-based map used for character movement.
 * It provides methods to initialize the grid, set cell states, and validate
 * positions.
 */
public class GridMap {
    /**
     * The CellState enum represents the possible states of a cell in the grid.
     * A cell can be either passable or impassable.
     */
    public enum CellState {
        PASSABLE, IMPASSABLE
    }

    private CellState[][] grid;
    private int width;
    private int height;

    /**
     * Constructs a GridMap object with the specified width and height.
     * The grid is initialized with all cells set to passable, except for the border
     * cells which are set to impassable.
     *
     * @param width  the width of the grid
     * @param height the height of the grid
     */
    public GridMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new CellState[width][height];
        initGrid();
    }

    /**
     * Initializes the grid by setting all cells to passable, except for the border
     * cells which are set to impassable.
     */
    private void initGrid() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = CellState.PASSABLE; // Explicitly setting all cells as passable
            }
        }
        setBorderAsImpassable();
    }

    /**
     * Validates if the given position is within the bounds of the grid.
     * Throws an OutsOfBoundsPositionException if the position is out of bounds.
     *
     * @param position the position to validate
     * @throws OutsOfBoundsPositionException if the position is out of bounds
     */
    public void validatePosition(Position position) throws OutsOfBoundsPositionException {
        if (position.getX() < 0 || position.getX() >= width ||
                position.getY() < 0 || position.getY() >= height) {
            throw new OutsOfBoundsPositionException("Position " + position + " is out of bounds.");
        }
    }

    /**
     * Sets the border cells of the grid to impassable.
     */
    private void setBorderAsImpassable() {
        for (int i = 0; i < width; i++) {
            grid[i][0] = CellState.IMPASSABLE;
            grid[i][height - 1] = CellState.IMPASSABLE;
        }

        for (int j = 0; j < height; j++) {
            grid[0][j] = CellState.IMPASSABLE;
            grid[width - 1][j] = CellState.IMPASSABLE;
        }
    }

    /**
     * Checks if the cell at the specified coordinates is passable.
     *
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @return true if the cell is passable, false otherwise
     */
    public boolean isCellPassable(int x, int y) {
        try {
            validatePosition(new Position(x, y));
            return grid[x][y] == CellState.PASSABLE;
        } catch (OutsOfBoundsPositionException e) {
            return false;
        }
    }

    /**
     * Checks if the given position is a valid position on the grid.
     *
     * @param position the position to check
     * @return true if the position is valid, false otherwise
     */
    public boolean isValidPosition(Position position) {
        return isCellPassable(position.getX(), position.getY());
    }

    /**
     * Sets the cell at the specified coordinates to impassable.
     * If the coordinates are invalid, nothing happens.
     *
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     */
    public void setCellAsImpassable(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = CellState.IMPASSABLE;
        } // Optionally, add an else clause to handle invalid coordinates
    }

    /**
     * Sets the state of the cell at the specified coordinates.
     * If the coordinates are invalid, an IllegalArgumentException is thrown.
     *
     * @param x     the x-coordinate of the cell
     * @param y     the y-coordinate of the cell
     * @param state the state to set for the cell
     * @throws IllegalArgumentException if the coordinates are invalid
     */
    public void setCellState(int x, int y, CellState state) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            grid[x][y] = state;
        } else {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
    }
}
