package com.aaf1007;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import com.aaf1007.rubikscube.Cubie;

public class IntegrationTest extends Application {
    
    private RubiksCubeVisual cubeVisual;
    private Cubie solverCubie;
    
    @Override
    public void start(Stage primaryStage) {
        // Create solver cube
        solverCubie = new Cubie();
        
        // Create visual cube linked to solver
        cubeVisual = new RubiksCubeVisual(solverCubie);
        
        Group root = new Group(cubeVisual);
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.LIGHTGRAY);
        
        // Camera and lighting
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-600);
        scene.setCamera(camera);
        
        AmbientLight ambient = new AmbientLight(Color.gray(0.4));
        PointLight point = new PointLight(Color.WHITE);
        point.setTranslateX(200);
        point.setTranslateY(-200);
        point.setTranslateZ(-500);
        root.getChildren().addAll(ambient, point);
        
        // Mouse controls
        initMouseControl(cubeVisual, scene);
        
        // Test: Apply moves with keyboard
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case U:
                    cubeVisual.applyMove(0); // U move
                    System.out.println("Applied U move");
                    break;
                case R:
                    cubeVisual.applyMove(3); // R move
                    System.out.println("Applied R move");
                    break;
                case F:
                    cubeVisual.applyMove(6); // F move
                    System.out.println("Applied F move");
                    break;
            }
        });
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rubik's Cube - Integration Test");
        primaryStage.show();
    }
    
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    
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
    
    public static void main(String[] args) {
        launch(args);
    }
}