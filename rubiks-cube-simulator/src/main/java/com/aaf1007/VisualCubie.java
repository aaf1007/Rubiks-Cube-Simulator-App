package com.aaf1007;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class VisualCubie extends Group {
    private static final double SIZE = 50;
    private static final double GAP = 2;
    private static final double STICKER_SIZE = SIZE - GAP;
    private static final double OFFSET = STICKER_SIZE / 2 + 0.1;
    
    // Color indices: 0=Up, 1=Right, 2=Front, 3=Down, 4=Left, 5=Back
    private final Color[] faceColors = new Color[6];
    
    public VisualCubie(int x, int y, int z) {
        // Create black core box
        Box core = new Box(SIZE - GAP, SIZE - GAP, SIZE - GAP);
        PhongMaterial coreMaterial = new PhongMaterial(Color.BLACK);
        core.setMaterial(coreMaterial);
        
        // Position the cubie in 3D space
        this.setTranslateX(x * SIZE);
        this.setTranslateY(y * SIZE);
        this.setTranslateZ(z * SIZE);
        
        this.getChildren().add(core);
        
        // Initialize all faces as black (internal)
        for (int i = 0; i < 6; i++) {
            faceColors[i] = Color.BLACK;
        }
    }
    
    public void setFaceColor(int face, Color color) {
        faceColors[face] = color;
        updateFaces();
    }
    
    private void updateFaces() {
        // Remove old stickers
        this.getChildren().removeIf(node -> node instanceof Rectangle);
        
        // Add colored stickers for each face
        addSticker(faceColors[0], 0, -OFFSET, 0, Rotate.X_AXIS, 90);  // Up
        addSticker(faceColors[1], OFFSET, 0, 0, Rotate.Y_AXIS, 90);   // Right
        addSticker(faceColors[2], 0, 0, OFFSET, Rotate.X_AXIS, 0);    // Front
        addSticker(faceColors[3], 0, OFFSET, 0, Rotate.X_AXIS, -90);  // Down
        addSticker(faceColors[4], -OFFSET, 0, 0, Rotate.Y_AXIS, -90); // Left
        addSticker(faceColors[5], 0, 0, -OFFSET, Rotate.X_AXIS, 180); // Back
    }
    
    private void addSticker(Color color, double x, double y, double z, 
                           javafx.geometry.Point3D axis, double angle) {
        if (color == Color.BLACK) return; // Don't show internal faces
        
        Rectangle sticker = new Rectangle(STICKER_SIZE - 4, STICKER_SIZE - 4);
        sticker.setFill(color);
        
        sticker.setTranslateX(x);
        sticker.setTranslateY(y);
        sticker.setTranslateZ(z);
        
        sticker.getTransforms().add(new Rotate(angle, axis));
        
        this.getChildren().add(sticker);
    }
}
