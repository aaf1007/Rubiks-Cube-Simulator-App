package com.aaf1007;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class RubiksCubeApp extends Application {
    
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    
    @Override
    public void start(Stage primaryStage) {
        // Create the 3D Rubik's Cube
        RubiksCubeVisual cube = new RubiksCubeVisual();
        
        Group root = new Group(cube);
        
        // Setup 3D scene
        Scene scene = new Scene(root, 1000, 800, true);
        scene.setFill(Color.LIGHTGRAY);
        // For making it full screen
        // primaryStage.setMaximized(true);
        
        // Add camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-600);
        camera.setNearClip(0.1);
        camera.setFieldOfView(45);
        camera.setFarClip(3000);
        scene.setCamera(camera);
        
        // Add lighting
        AmbientLight ambient = new AmbientLight(Color.gray(0.4));
        PointLight point = new PointLight(Color.WHITE);
        point.setTranslateX(200);
        point.setTranslateY(-200);
        point.setTranslateZ(-500);
        root.getChildren().addAll(ambient, point);
        
        // Add mouse and arrow controls
        initMouseControl(cube, scene);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Rubik's Cube Solver");
        primaryStage.show();
    }
    
    private void initMouseControl(Group group, Scene scene) {
        group.getTransforms().addAll(rotateX, rotateY);
        
        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = rotateX.getAngle();
            anchorAngleY = rotateY.getAngle();
        });
        
        scene.setOnMouseDragged(event -> {
            rotateX.setAngle(anchorAngleX - (anchorY - event.getSceneY()));
            rotateY.setAngle(anchorAngleY + (anchorX - event.getSceneX()));
        });
    }

/* 
    private void initArrowControl(Group group, Scene scene) {
        group.getTransforms().addAll(rotateX, rotateY);
        
        // Degrees per press
        double step = 5;

        scene.setOnKeyPressed((keyEvent) -> {
            switch (keyEvent.getCode()) {
                case UP:
                    rotateY.setAngle(rotateY.getAngle() + step);
                case DOWN:
                    rotateY.setAngle(rotateY.getAngle() - step);
                case LEFT:
                    rotateX.setAngle(rotateX.getAngle() + step);
                case RIGHT:
                    rotateX.setAngle(rotateX.getAngle() - step);
            }
        });

    }
*/

    public static void main(String[] args) {
        launch(args);
    }
}
