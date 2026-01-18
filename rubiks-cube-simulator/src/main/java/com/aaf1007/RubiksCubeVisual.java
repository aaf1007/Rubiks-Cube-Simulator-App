package com.aaf1007;

import com.aaf1007.rubikscube.Cubie;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class RubiksCubeVisual extends Group {
    private VisualCubie[][][] visualCubies = new VisualCubie[3][3][3];
    private Cubie solverCubie; // Reference to solver's cube state
     
    // Color mapping: solver face index -> JavaFX Color
    private static final Color[] FACE_COLORS = {
        Color.ORANGE,  // U = 0
        Color.BLUE,    // R = 1
        Color.WHITE,   // F = 2
        Color.RED,     // D = 3
        Color.GREEN,   // L = 4
        Color.YELLOW   // B = 5
    };
    
    public RubiksCubeVisual() {
        this(new Cubie());
    }

    public RubiksCubeVisual(Cubie solverCubie) {
        this.solverCubie = solverCubie;
        createCube();
        syncWithSolver();
    }
    
    private void createCube() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    VisualCubie cubie = new VisualCubie(x - 1, y - 1, z - 1);
                    visualCubies[x][y][z] = cubie;
                    this.getChildren().add(cubie);
                }
            }
        }
    }
    
    /**
     * Reads the solver's Cubie state and updates all visual cubies
     */
    public void syncWithSolver() {
        resetColors();
        updateCorners();
        updateEdges();
        updateCenters();
    }
    
    private void resetColors() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    for (int face = 0; face < 6; face++) {
                        visualCubies[x][y][z].setFaceColor(face, Color.BLACK);
                    }
                }
            }
        }
    }
    
    private void updateCorners() {
        // Visual positions (x, y, z) for each corner
        int[][] cornerPositions = {
            {2, 0, 2}, // URF = 0
            {0, 0, 2}, // UFL = 1
            {0, 0, 0}, // ULB = 2
            {2, 0, 0}, // UBR = 3
            {2, 2, 2}, // DFR = 4
            {0, 2, 2}, // DLF = 5
            {0, 2, 0}, // DBL = 6
            {2, 2, 0}  // DRB = 7
        };
        
        // Which faces are visible for each corner position
        int[][] cornerFaces = {
            {0, 1, 2}, // URF: Up, Right, Front
            {0, 2, 4}, // UFL: Up, Front, Left
            {0, 4, 5}, // ULB: Up, Left, Back
            {0, 5, 1}, // UBR: Up, Back, Right
            {3, 2, 1}, // DFR: Down, Front, Right
            {3, 4, 2}, // DLF: Down, Left, Front
            {3, 5, 4}, // DBL: Down, Back, Left
            {3, 1, 5}  // DRB: Down, Right, Back
        };
        
        for (int pos = 0; pos < 8; pos++) {
            int piece = solverCubie.cornerPerm[pos];
            int orient = solverCubie.cornerOrient[pos];
            
            int[] visualPos = cornerPositions[pos];
            int x = visualPos[0];
            int y = visualPos[1];
            int z = visualPos[2];
            
            int[] pieceFaces = cornerFaces[piece];
            
            // Apply orientation
            for (int i = 0; i < 3; i++) {
                int faceIndex = cornerFaces[pos][i];
                int colorIndex = pieceFaces[(i + orient) % 3];
                Color color = FACE_COLORS[colorIndex];
                visualCubies[x][y][z].setFaceColor(faceIndex, color);
            }
        }
    }
    
    private void updateEdges() {
        int[][] edgePositions = {
            {2, 0, 1}, // UR = 0
            {1, 0, 2}, // UF = 1
            {0, 0, 1}, // UL = 2
            {1, 0, 0}, // UB = 3
            {2, 2, 1}, // DR = 4
            {1, 2, 2}, // DF = 5
            {0, 2, 1}, // DL = 6
            {1, 2, 0}, // DB = 7
            {2, 1, 2}, // FR = 8
            {0, 1, 2}, // FL = 9
            {0, 1, 0}, // BL = 10
            {2, 1, 0}  // BR = 11
        };
        
        int[][] edgeFaces = {
            {0, 1}, // UR
            {0, 2}, // UF
            {0, 4}, // UL
            {0, 5}, // UB
            {3, 1}, // DR
            {3, 2}, // DF
            {3, 4}, // DL
            {3, 5}, // DB
            {2, 1}, // FR
            {2, 4}, // FL
            {5, 4}, // BL
            {5, 1}  // BR
        };
        
        for (int pos = 0; pos < 12; pos++) {
            int piece = solverCubie.edgePerm[pos];
            int orient = solverCubie.edgeOrient[pos];
            
            int[] visualPos = edgePositions[pos];
            int x = visualPos[0];
            int y = visualPos[1];
            int z = visualPos[2];
            
            int[] pieceFaces = edgeFaces[piece];
            
            for (int i = 0; i < 2; i++) {
                int faceIndex = edgeFaces[pos][i];
                int colorIndex = pieceFaces[(i + orient) % 2];
                Color color = FACE_COLORS[colorIndex];
                visualCubies[x][y][z].setFaceColor(faceIndex, color);
            }
        }
    }
    
    private void updateCenters() {
        visualCubies[1][0][1].setFaceColor(0, FACE_COLORS[0]); // U
        visualCubies[2][1][1].setFaceColor(1, FACE_COLORS[1]); // R
        visualCubies[1][1][2].setFaceColor(2, FACE_COLORS[2]); // F
        visualCubies[1][2][1].setFaceColor(3, FACE_COLORS[3]); // D
        visualCubies[0][1][1].setFaceColor(4, FACE_COLORS[4]); // L
        visualCubies[1][1][0].setFaceColor(5, FACE_COLORS[5]); // B
    }
    
    /**
     * Apply a move to both solver and visual
     */
    public void applyMove(int move) {
        solverCubie.applyMove(move);
        syncWithSolver();
    }
    
    /**
     * Update the solver reference (used for reset)
     */
    public void setSolverCubie(Cubie solverCubie) {
        this.solverCubie = solverCubie;
    }
}
