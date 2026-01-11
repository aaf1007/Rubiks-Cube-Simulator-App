package com.aaf1007;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class RubiksCubeVisual extends Group {
    private final VisualCubie[][][] cubies = new VisualCubie[3][3][3];
    
    // Standard Rubik's Cube colors
    private static final Color ORANGE = Color.ORANGE;  // Up
    private static final Color BLUE = Color.BLUE;      // Right
    private static final Color WHITE = Color.WHITE;    // Front
    private static final Color RED = Color.RED;        // Down
    private static final Color GREEN = Color.GREEN;    // Left
    private static final Color YELLOW = Color.YELLOW;  // Back
    
    public RubiksCubeVisual() {
        createCube();
        initializeSolvedState();
    }
    
    private void createCube() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    VisualCubie cubie = new VisualCubie(x - 1, y - 1, z - 1);
                    cubies[x][y][z] = cubie;
                    this.getChildren().add(cubie);
                }
            }
        }
    }
    
    private void initializeSolvedState() {
        // Up face (y = 0)
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                cubies[x][0][z].setFaceColor(0, ORANGE);
            }
        }
        
        // Down face (y = 2)
        for (int x = 0; x < 3; x++) {
            for (int z = 0; z < 3; z++) {
                cubies[x][2][z].setFaceColor(3, RED);
            }
        }
        
        // Front face (z = 2)
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cubies[x][y][2].setFaceColor(2, WHITE);
            }
        }
        
        // Back face (z = 0)
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                cubies[x][y][0].setFaceColor(5, YELLOW);
            }
        }
        
        // Right face (x = 2)
        for (int y = 0; y < 3; y++) {
            for (int z = 0; z < 3; z++) {
                cubies[2][y][z].setFaceColor(1, BLUE);
            }
        }
        
        // Left face (x = 0)
        for (int y = 0; y < 3; y++) {
            for (int z = 0; z < 3; z++) {
                cubies[0][y][z].setFaceColor(4, GREEN);
            }
        }
    }
}
