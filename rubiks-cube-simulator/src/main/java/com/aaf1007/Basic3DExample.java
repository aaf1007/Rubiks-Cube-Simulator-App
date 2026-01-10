package com.aaf1007;

import javafx.application.Application;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
// public class App extends Application {

//     private static Scene scene;

//     @Override
//     public void start(Stage stage) throws IOException {
//         // Button
//         Button btn = new Button("Solve");
//         btn.setOnAction(e->btn.setText("Solving..."));

//          // Create layout and add button
//         VBox root = new VBox(10); // 10px spacing
//         root.getChildren().add(btn);
//         root.setAlignment(Pos.CENTER);
//         root.setPrefSize(50, 50);

//         // Create a scene and show
//         scene = new Scene(root, 800, 600);
//         stage.setTitle("Rubiks Cube");
//         stage.setScene(scene);
//         stage.show();
//     }

//     static void setRoot(String fxml) throws IOException {
//         scene.setRoot(loadFXML(fxml));
//     }

//     private static Parent loadFXML(String fxml) throws IOException {
//         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//         return fxmlLoader.load();
//     }

//     public static void main(String[] args) {
//         launch(args);
//     }

// }

/**
 *  For 3D
 */
public class Basic3DExample extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Create a 3D box
        Box box = new Box(100, 100, 100);
        
        // Add material (color/texture)
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.RED);
        box.setMaterial(material);
    
        // Rotate box to see it's 3D
        box.getTransforms().add(new Rotate(30, Rotate.X_AXIS));
        box.getTransforms().add(new Rotate(30, Rotate.Y_AXIS));
        
        // Create group to hold 3D objects
        Group root = new Group(box);
        
        // Create scene with depth buffer enabled
        Scene scene = new Scene(root, 800, 600, true);
        
        // Add camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-500);
        camera.setNearClip(0.1);
        camera.setFarClip(3000);
        scene.setCamera(camera);
        
        // Looking around
        // scene.setCursor();
        // Add lighting
        AmbientLight ambient = new AmbientLight(Color.WHITE);
        root.getChildren().add(ambient);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("3D Box");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
