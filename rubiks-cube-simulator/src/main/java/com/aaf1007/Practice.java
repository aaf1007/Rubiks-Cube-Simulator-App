// package com.aaf1007;

// import javafx.application.Application;
// import javafx.scene.AmbientLight;
// import javafx.scene.Group;
// import javafx.scene.PerspectiveCamera;
// import javafx.scene.PointLight;
// import javafx.scene.Scene;
// import javafx.scene.paint.Color;
// import javafx.scene.paint.PhongMaterial;
// import javafx.scene.shape.Box;
// import javafx.scene.transform.Rotate;
// import javafx.stage.Stage;

// /**
//  * JavaFX App
//  */
// // public class App extends Application {

// //     private static Scene scene;

// //     @Override
// //     public void start(Stage stage) throws IOException {
// //         // Button
// //         Button btn = new Button("Solve");
// //         btn.setOnAction(e->btn.setText("Solving..."));

// //          // Create layout and add button
// //         VBox root = new VBox(10); // 10px spacing
// //         root.getChildren().add(btn);
// //         root.setAlignment(Pos.CENTER);
// //         root.setPrefSize(50, 50);

// //         // Create a scene and show
// //         scene = new Scene(root, 800, 600);
// //         stage.setTitle("Rubiks Cube");
// //         stage.setScene(scene);
// //         stage.show();
// //     }

// //     static void setRoot(String fxml) throws IOException {
// //         scene.setRoot(loadFXML(fxml));
// //     }

// //     private static Parent loadFXML(String fxml) throws IOException {
// //         FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
// //         return fxmlLoader.load();
// //     }

// //     public static void main(String[] args) {
// //         launch(args);
// //     }

// // }

// /**
//  *  For 3D
//  */
// // public class Basic3DExample extends Application {

// //     private double mouseX, mouseY;
// //     private final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
// //     private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    
// //     @Override
// //     public void start(Stage primaryStage) {
// //         // Create a 3D box
// //         Box box = new Box(100, 100, 100);
        
// //         // Add material (color/texture)
// //         PhongMaterial material = new PhongMaterial();
// //         material.setDiffuseColor(Color.RED);
// //         box.setMaterial(material);
        
        
// //         // Create group to hold 3D objects
// //         Group root = new Group(box);
        
// //         // Add rotation transforms to the root group
// //         root.getTransforms().addAll(rotateX, rotateY);

// //         // Create scene with depth buffer enabled
// //         Scene scene = new Scene(root, 800, 600, true);
        
// //         // Add camera
// //         PerspectiveCamera camera = new PerspectiveCamera(true);
// //         camera.setTranslateZ(-500);
// //         camera.setNearClip(0.1);
// //         camera.setFarClip(3000);
// //         camera.setFieldOfView(45);
// //         scene.setCamera(camera);
        
// //         // Looking around
// //         // Mouse Press
// //         scene.setOnMousePressed(event -> {
// //             mouseX = event.getSceneX();
// //             mouseY = event.getSceneY();
// //         });
// //         // Mouse Drag
// //         scene.setOnMouseDragged(event -> {
// //             double deltaX = event.getSceneX() - mouseX;
// //             double deltaY = event.getSceneY() - mouseY;
// //             // 0.5 is basically a sensitivity
// //             rotateY.setAngle(rotateY.getAngle() + deltaX * 0.5);
// //             rotateX.setAngle(rotateX.getAngle() - deltaY * 0.5);
            
// //             mouseX = event.getSceneX();
// //             mouseY = event.getSceneY();
// //         });

// //         // scene.setOnMouseMoved(event -> {
// //         //     double deltaX = event.getSceneX() - mouseX;
// //         //     double deltaY = event.getSceneY() - mouseY;
// //         //     // 0.5 is basically a sensitivity
// //         //     rotateY.setAngle(rotateY.getAngle() + deltaX * 0.5);
// //         //     rotateX.setAngle(rotateX.getAngle() - deltaY * 0.5);
            
// //         //     mouseX = event.getSceneX();
// //         //     mouseY = event.getSceneY();
// //         // });

// //         // Add lighting
// //         AmbientLight ambient = new AmbientLight(Color.WHITE);
// //         root.getChildren().add(ambient);
        
// //         primaryStage.setScene(scene);
// //         primaryStage.setTitle("3D Box");
// //         primaryStage.show();
// //     }
    
// //     public static void main(String[] args) {
// //         launch(args);
// //     }
// // }

// public class Practice extends Application {

//     private double mouseX, mouseY;
//     private Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
//     private Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

//     @Override
//     public void start(Stage stage) {
//     //     Requirements:

//     //     Create a 3D box (100x100x100)
//     //     Color it with a PhongMaterial (any color)
//     //     Add a PerspectiveCamera positioned at z = -500
//     //     Add ambient lighting
//     //     Implement mouse drag controls to rotate the cube
//     //     Window size: 800x600
//     //     Bonus: Add a PointLight that creates shadows/highlights on the cube.
//         Box box = new Box(100,100,100);

//         PhongMaterial material = new PhongMaterial();
//         material.setDiffuseColor(Color.RED);
//         box.setMaterial(material);

//         Group root = new Group(box);
//         root.getTransforms().addAll(rotateX,rotateY);
//         // Window Size
//         Scene scene = new Scene(root, 800, 600, true);
//         scene.setFill(Color.WHITE);

//         PerspectiveCamera camera = new PerspectiveCamera(true);
//         camera.setTranslateZ(-500);
//         camera.setNearClip(0.1);
//         camera.setFarClip(3000);
//         scene.setCamera(camera);

//         AmbientLight ambient = new AmbientLight((Color.YELLOW));
//         root.getChildren().add(ambient);

//         PointLight light = new PointLight(Color.BLUE);
//         light.setTranslateX(100);
//         light.setTranslateY(80);
//         light.setTranslateZ(-200);
//         root.getChildren().add(light);

//         // Event handlers
//         scene.setOnMouseClicked(event -> {
//             mouseX = event.getSceneX();
//             mouseY = event.getSceneY();
//         });

//         scene.setOnMouseDragged(event -> {
//             double deltaX = event.getSceneX() - mouseX;
//             double deltaY = event.getSceneY() - mouseY;

//             rotateX.setAngle(rotateX.getAngle() + deltaX);
//             rotateY.setAngle(rotateY.getAngle() - deltaY);

//             mouseX = event.getSceneX();
//             mouseY = event.getSceneY();
//         });

//         stage.setTitle("Rubiks Cube GUI");
//         stage.setScene(scene);
//         stage.show();
//     }
// }