# Rubik's Cube Solver GUI with JavaFX

## Course Overview

Transform your command-line Rubik's Cube solver into an interactive 3D desktop application. You'll learn JavaFX fundamentals, 3D graphics, and how to integrate a visual interface with your existing solving algorithm.

**Prerequisites:** Completed Rubik's Cube solver using Kociemba's Two-Phase Algorithm (Java)

**Estimated Time:** 1-2 weeks

---

## Module 1: JavaFX Fundamentals

### Introduction

JavaFX is Java's modern GUI framework that replaced Swing. It provides powerful 2D and 3D graphics capabilities, making it perfect for visualizing your Rubik's Cube solver.

### Lesson Content

#### What is JavaFX?

JavaFX is a software platform for creating desktop applications with rich user interfaces. Key features include:
- Scene graph architecture for organizing UI elements
- CSS styling support
- Built-in 3D graphics support
- FXML for declarative UI design (we'll use programmatic approach)

#### Basic JavaFX Application Structure

Every JavaFX application follows this pattern:

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApp extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // This is where your app starts
        primaryStage.setTitle("My Application");
        
        // Create scene and set it on stage
        // Scene contains all your UI elements
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

**Key Concepts:**
- **Stage**: The window (like a theater stage)
- **Scene**: The content displayed in the window (like a scene in a play)
- **Nodes**: Individual UI elements (buttons, labels, shapes, etc.)

#### Layouts in JavaFX

JavaFX provides several layout containers:

- **BorderPane**: Divides space into top, bottom, left, right, center
- **VBox**: Stacks elements vertically
- **HBox**: Arranges elements horizontally
- **StackPane**: Layers elements on top of each other (useful for 3D)
- **GridPane**: Grid-based layout

For your Rubik's Cube app, you'll likely use:
- `BorderPane` for overall layout (3D cube in center, controls on sides)
- `VBox`/`HBox` for button groups

#### Basic Example

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Create a button
        Button btn = new Button("Click Me");
        btn.setOnAction(e -> System.out.println("Button clicked!"));
        
        // Create layout and add button
        VBox root = new VBox(10); // 10px spacing
        root.getChildren().add(btn);
        
        // Create scene and show
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello JavaFX");
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

#### Setting Up JavaFX in Your Project

**If using Java 11+**, JavaFX is separate from the JDK:

**Maven** (`pom.xml`):
```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21.0.1</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21.0.1</version>
    </dependency>
</dependencies>
```

**Gradle** (`build.gradle`):
```gradle
plugins {
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.1.0'
}

javafx {
    version = "21.0.1"
    modules = ['javafx.controls', 'javafx.fxml']
}
```

### Assignment 1: Hello Rubik's Cube

**Goal:** Create a basic JavaFX application with a title and a button.

**Requirements:**
1. Create a new JavaFX application class
2. Set the window title to "Rubik's Cube Solver"
3. Create a button labeled "Solve Cube"
4. When clicked, print "Solving..." to console
5. Set window size to 800x600

**Hint:** Use the example code above as a template.

---

## Module 2: JavaFX 3D Basics

### Introduction

JavaFX has built-in 3D graphics support. You'll learn how to create 3D shapes, position them in 3D space, add lighting, and handle camera controls.

### Lesson Content

#### 3D Coordinate System in JavaFX

JavaFX uses a right-handed coordinate system:
- **X-axis**: Left (-) to Right (+)
- **Y-axis**: Top (-) to Bottom (+) ⚠️ Note: Y is inverted!
- **Z-axis**: Screen (-) to You (+)

#### Creating a 3D Scene

To render 3D content, you need:

1. **3D Shapes** (Box, Sphere, Cylinder, etc.)
2. **Camera** (PerspectiveCamera)
3. **Lighting** (AmbientLight, PointLight)
4. **Group** to hold 3D objects

```java
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

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
        scene.setCamera(camera);
        
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
```

#### Key 3D Concepts

**Materials:**
```java
PhongMaterial material = new PhongMaterial();
material.setDiffuseColor(Color.BLUE);      // Base color
material.setSpecularColor(Color.WHITE);    // Highlight color
material.setSpecularPower(10);             // Shininess
```

**Transformations:**
```java
// Translation (move)
box.setTranslateX(50);
box.setTranslateY(-20);
box.setTranslateZ(100);

// Rotation
Rotate rotateX = new Rotate(45, Rotate.X_AXIS);
box.getTransforms().add(rotateX);

// Scale
box.setScaleX(1.5);
box.setScaleY(1.5);
box.setScaleZ(1.5);
```

**Camera:**
```java
PerspectiveCamera camera = new PerspectiveCamera(true);
camera.setTranslateZ(-500);  // Move camera back
camera.setFieldOfView(45);   // Field of view angle
scene.setCamera(camera);
```

**Lighting:**
```java
// Ambient light (overall illumination)
AmbientLight ambient = new AmbientLight(Color.gray(0.3));

// Point light (like a light bulb)
PointLight point = new PointLight(Color.WHITE);
point.setTranslateX(200);
point.setTranslateY(-200);
point.setTranslateZ(-500);

root.getChildren().addAll(ambient, point);
```

#### Mouse Camera Controls

Make your 3D scene interactive:

```java
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
```

### Assignment 2: 3D Cube with Mouse Control

**Goal:** Create a 3D scene with a rotating cube you can control with the mouse.

**Requirements:**
1. Create a 3D box (100x100x100)
2. Color it with a PhongMaterial (any color)
3. Add a PerspectiveCamera positioned at z = -500
4. Add ambient lighting
5. Implement mouse drag controls to rotate the cube
6. Window size: 800x600

**Bonus:** Add a PointLight that creates shadows/highlights on the cube.

---

## Module 3: Building a 3D Rubik's Cube

### Introduction

Now you'll create a visual representation of a Rubik's Cube using JavaFX 3D. You'll learn how to position 27 small cubes (cubies) in a 3x3x3 grid and color their faces to represent the cube's state.

### Lesson Content

#### Rubik's Cube Structure

A 3x3x3 Rubik's Cube consists of:
- **8 corner pieces** (3 visible faces each)
- **12 edge pieces** (2 visible faces each)
- **6 center pieces** (1 visible face each)
- **1 core** (hidden, no visible faces)

Total: 27 cubies (small cubes), but only 26 are visible.

#### Problem: Multi-Colored Faces

**Challenge:** JavaFX Box can only have ONE material (one color). But a Rubik's Cube piece needs different colors on different faces!

**Solution:** Use 6 thin rectangles (one per face) overlaid on a black core box.

#### Creating a Multi-Colored Cubie

```java
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
    private Color[] faceColors = new Color[6];
    
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
```

#### Creating the Full 3x3x3 Cube

```java
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class RubiksCubeVisual extends Group {
    private VisualCubie[][][] cubies = new VisualCubie[3][3][3];
    
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
```

#### Putting It All Together

```java
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
        Scene scene = new Scene(root, 800, 600, true);
        scene.setFill(Color.LIGHTGRAY);
        
        // Add camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-600);
        scene.setCamera(camera);
        
        // Add lighting
        AmbientLight ambient = new AmbientLight(Color.gray(0.4));
        PointLight point = new PointLight(Color.WHITE);
        point.setTranslateX(200);
        point.setTranslateY(-200);
        point.setTranslateZ(-500);
        root.getChildren().addAll(ambient, point);
        
        // Add mouse controls
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
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

### Assignment 3: Build Your 3D Rubik's Cube

**Goal:** Create a visual 3D Rubik's Cube that displays in solved state.

**Requirements:**
1. Implement the `VisualCubie` class with multi-colored faces
2. Implement the `RubiksCubeVisual` class that creates 27 cubies
3. Initialize the cube in solved state with correct colors
4. Add mouse rotation controls
5. The cube should be centered and clearly visible

**Bonus:** Add keyboard controls (arrow keys) to rotate the view.

---

## Module 4: Integrating with Your Solver

### Introduction

Now you'll connect your visual Rubik's Cube with your existing Java solver code. You'll learn how to sync the visual state with your `Cubie` representation and apply moves to both simultaneously.

### Lesson Content

#### Understanding the Mapping

Your solver uses:
```java
rubikscube.Cubie // Piece representation
- cornerPerm[8], cornerOrient[8]
- edgePerm[12], edgeOrient[12]
```

Your visual uses:
```java
RubiksCubeVisual
- VisualCubie[3][3][3] // Visual cubies in 3D grid
```

**Strategy:** Keep solver's `Cubie` as the **source of truth**, visual follows it.

#### Synchronizing Solver State with Visual

```java
import rubikscube.Cubie;
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
```

#### Testing the Integration

```java
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import rubikscube.Cubie;

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
```

### Assignment 4: Connect Solver to Visual

**Goal:** Integrate your solver's `Cubie` with the visual representation.

**Requirements:**
1. Modify `RubiksCubeVisual` to accept a `Cubie` parameter in constructor
2. Implement `syncWithSolver()` method that reads solver state and updates visual
3. Implement corner, edge, and center update methods
4. Test by applying keyboard moves (U, R, F keys)
5. Verify the visual updates correctly after each move

**Testing Checklist:**
- [ ] Cube starts in solved state
- [ ] Pressing 'U' rotates top face
- [ ] Pressing 'R' rotates right face
- [ ] Colors match expected positions
- [ ] Multiple moves work correctly

---

## Module 5: Adding UI Controls

### Introduction

Now you'll add buttons and controls for scrambling, solving, and manually applying moves. You'll learn about JavaFX layouts and event handling.

### Lesson Content

#### Layout Structure

We'll use a `BorderPane`:
- **Center**: 3D Rubik's Cube
- **Right**: Control buttons (Scramble, Solve, Reset)
- **Bottom**: Move buttons (U, R, F, D, L, B)

```java
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import rubikscube.Cubie;
import rubikscube.TwoPhase;

public class RubiksCubeGUI extends Application {
    
    private RubiksCubeVisual cubeVisual;
    private Cubie solverCubie;
    private Label statusLabel;
    
    @Override
    public void start(Stage primaryStage) {
        // Initialize solver cube
        solverCubie = new Cubie();
        cubeVisual = new RubiksCubeVisual(solverCubie);
        
        // Create 3D scene for cube
        Group cubeGroup = new Group(cubeVisual);
        SubScene subScene = new SubScene(cubeGroup, 600, 600, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.LIGHTGRAY);
        
        // Camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-600);
        subScene.setCamera(camera);
        
        // Lighting
        AmbientLight ambient = new AmbientLight(Color.gray(0.4));
        PointLight point = new PointLight(Color.WHITE);
        point.setTranslateX(200);
        point.setTranslateY(-200);
        point.setTranslateZ(-500);
        cubeGroup.getChildren().addAll(ambient, point);
        
        // Mouse controls
        initMouseControl(cubeVisual, subScene);
        
        // Main layout
        BorderPane root = new BorderPane();
        root.setCenter(subScene);
        root.setRight(createControlPanel());
        root.setBottom(createMovePanel());
        
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rubik's Cube Solver");
        primaryStage.show();
    }
    
    private VBox createControlPanel() {
        VBox panel = new VBox(15);
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setPrefWidth(200);
        panel.setStyle("-fx-background-color: #f5f5f5;");
        
        Label title = new Label("Controls");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        Button resetBtn = new Button("Reset Cube");
        resetBtn.setPrefWidth(150);
        resetBtn.setOnAction(e -> resetCube());
        
        Button scrambleBtn = new Button("Random Scramble");
        scrambleBtn.setPrefWidth(150);
        scrambleBtn.setOnAction(e -> scrambleCube());
        
        Button solveBtn = new Button("Solve Cube");
        solveBtn.setPrefWidth(150);
        solveBtn.setOnAction(e -> solveCube());
        
        statusLabel = new Label("Ready");
        statusLabel.setWrapText(true);
        statusLabel.setStyle("-fx-padding: 10px; -fx-background-color: #ffffff; " +
                           "-fx-border-color: #ccc; -fx-border-radius: 5px;");
        statusLabel.setPrefHeight(100);
        statusLabel.setAlignment(Pos.TOP_LEFT);
        
        panel.getChildren().addAll(title, resetBtn, scrambleBtn, solveBtn, 
                                   new Label("Status:"), statusLabel);
        return panel;
    }
    
    private HBox createMovePanel() {
        HBox panel = new HBox(10);
        panel.setPadding(new Insets(15));
        panel.setAlignment(Pos.CENTER);
        panel.setStyle("-fx-background-color: #e0e0e0;");
        
        Label label = new Label("Manual Moves:");
        label.setStyle("-fx-font-weight: bold;");
        
        String[] moveNames = {"U", "U2", "U'", "R", "R2", "R'", 
                              "F", "F2", "F'", "D", "D2", "D'",
                              "L", "L2", "L'", "B", "B2", "B'"};
        
        panel.getChildren().add(label);
        
        for (int i = 0; i < moveNames.length; i++) {
            final int moveIndex = i;
            Button btn = new Button(moveNames[i]);
            btn.setMinWidth(40);
            btn.setOnAction(e -> applyMove(moveIndex));
            panel.getChildren().add(btn);
        }
        
        return panel;
    }
    
    private void resetCube() {
        solverCubie = new Cubie();
        cubeVisual.setSolverCubie(solverCubie);
        cubeVisual.syncWithSolver();
        statusLabel.setText("Cube reset to solved state");
    }
    
    private void scrambleCube() {
        java.util.Random random = new java.util.Random();
        int numMoves = 20 + random.nextInt(10); // 20-30 random moves
        
        for (int i = 0; i < numMoves; i++) {
            int move = random.nextInt(18);
            solverCubie.applyMove(move);
        }
        
        cubeVisual.syncWithSolver();
        statusLabel.setText("Cube scrambled with " + numMoves + " random moves");
    }
    
    private void solveCube() {
        statusLabel.setText("Solving...");
        
        // Run solver in background thread to avoid freezing UI
        new Thread(() -> {
            String solution = TwoPhase.solve(solverCubie, 25, 10);
            
            // Update UI on JavaFX thread
            javafx.application.Platform.runLater(() -> {
                if (solution.startsWith("Error")) {
                    statusLabel.setText("Error: " + solution);
                } else {
                    statusLabel.setText("Solution found!\n" + 
                                      "Length: " + solution.length() + " moves\n" + 
                                      solution);
                    applySolution(solution);
                }
            });
        }).start();
    }
    
    private void applySolution(String solution) {
        // Animate the solution
        new Thread(() -> {
            for (int i = 0; i < solution.length(); i++) {
                char c = solution.charAt(i);
                int move = charToMove(c);
                
                if (move >= 0) {
                    solverCubie.applyMove(move);
                    
                    javafx.application.Platform.runLater(() -> {
                        cubeVisual.syncWithSolver();
                    });
                    
                    try {
                        Thread.sleep(300); // Pause between moves
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            javafx.application.Platform.runLater(() -> {
                statusLabel.setText("Solved! Cube is now in solved state.");
            });
        }).start();
    }
    
    private void applyMove(int move) {
        cubeVisual.applyMove(move);
        statusLabel.setText("Applied move: " + moveToString(move));
    }
    
    private int charToMove(char c) {
        switch (c) {
            case 'U': return 0;
            case 'R': return 3;
            case 'F': return 6;
            case 'D': return 9;
            case 'L': return 12;
            case 'B': return 15;
            default: return -1;
        }
    }
    
    private String moveToString(int move) {
        String[] moves = {"U", "U2", "U'", "R", "R2", "R'", 
                         "F", "F2", "F'", "D", "D2", "D'",
                         "L", "L2", "L'", "B", "B2", "B'"};
        return moves[move];
    }
    
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    
    private void initMouseControl(Group group, SubScene subScene) {
        group.getTransforms().addAll(rotateX, rotateY);
        
        subScene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = rotateX.getAngle();
            anchorAngleY = rotateY.getAngle();
        });
        
        subScene.setOnMouseDragged(event -> {
            rotateX.setAngle(anchorAngleX - (anchorY - event.getSceneY()));
            rotateY.setAngle(anchorAngleY + (anchorX - event.getSceneX()));
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
```

### Assignment 5: Build the Full GUI

**Goal:** Create the complete GUI with all controls.

**Requirements:**
1. Implement the control panel (Reset, Scramble, Solve buttons)
2. Implement the move panel (all 18 moves)
3. Make Scramble button work (20-30 random moves)
4. Make Solve button call your TwoPhase solver
5. Animate the solution (show moves one by one)
6. Display status messages

**Testing:**
- [ ] Reset button returns cube to solved state
- [ ] Scramble creates a random scramble
- [ ] Solve finds and displays a solution
- [ ] Manual move buttons work
- [ ] Solution animation is visible

---

## Module 6: Adding Move Animations

### Introduction

Right now moves happen instantly. You'll add smooth animations to make moves rotate in 3D, just like a real Rubik's Cube.

### Lesson Content

#### Animation Strategy

Instead of instantly updating colors, we'll:
1. Group the 9 cubies affected by a move
2. Rotate them 90° around the appropriate axis
3. Update the solver state
4. Re-sync colors

#### Implementing Face Rotation Animation

Add these methods to `RubiksCubeVisual.java`:

```java
import javafx.animation.RotateTransition;
import javafx.util.Duration;

public class RubiksCubeVisual extends Group {
    // ... existing code ...
    
    private boolean isAnimating = false;
    
    /**
     * Apply move with animation
     */
    public void applyMoveAnimated(int move, Runnable onComplete) {
        if (isAnimating) return;
        isAnimating = true;
        
        int face = move / 3;
        int turns = move % 3 + 1;
        
        // Get cubies to rotate
        VisualCubie[] faceCubies = getFaceCubies(face);
        Group faceGroup = new Group(faceCubies);
        
        // Temporarily add to scene
        this.getChildren().add(faceGroup);
        
        // Determine rotation axis and angle
        javafx.geometry.Point3D axis = getRotationAxis(face);
        double angle = turns * 90;
        if (face == 0 || face == 2 || face == 4) {
            angle = -angle; // Reverse for U, F, L faces
        }
        
        // Create rotation animation
        RotateTransition rotation = new RotateTransition(Duration.millis(300), faceGroup);
        rotation.setAxis(axis);
        rotation.setByAngle(angle);
        
        rotation.setOnFinished(e -> {
            // Remove animation group
            this.getChildren().remove(faceGroup);
            
            // Apply move to solver
            solverCubie.applyMove(move);
            
            // Sync visual with new state
            syncWithSolver();
            
            isAnimating = false;
            if (onComplete != null) {
                onComplete.run();
            }
        });
        
        rotation.play();
    }
    
    private VisualCubie[] getFaceCubies(int face) {
        VisualCubie[] cubies = new VisualCubie[9];
        int index = 0;
        
        switch (face) {
            case 0: // U face
                for (int x = 0; x < 3; x++) {
                    for (int z = 0; z < 3; z++) {
                        cubies[index++] = visualCubies[x][0][z];
                    }
                }
                break;
            case 1: // R face
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        cubies[index++] = visualCubies[2][y][z];
                    }
                }
                break;
            case 2: // F face
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        cubies[index++] = visualCubies[x][y][2];
                    }
                }
                break;
            case 3: // D face
                for (int x = 0; x < 3; x++) {
                    for (int z = 0; z < 3; z++) {
                        cubies[index++] = visualCubies[x][2][z];
                    }
                }
                break;
            case 4: // L face
                for (int y = 0; y < 3; y++) {
                    for (int z = 0; z < 3; z++) {
                        cubies[index++] = visualCubies[0][y][z];
                    }
                }
                break;
            case 5: // B face
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        cubies[index++] = visualCubies[x][y][0];
                    }
                }
                break;
        }
        
        return cubies;
    }
    
    private javafx.geometry.Point3D getRotationAxis(int face) {
        switch (face) {
            case 0: // U
            case 3: // D
                return Rotate.Y_AXIS;
            case 1: // R
            case 4: // L
                return Rotate.X_AXIS;
            case 2: // F
            case 5: // B
                return Rotate.Z_AXIS;
            default:
                return Rotate.Y_AXIS;
        }
    }
}
```

#### Updating the GUI to Use Animations

Modify the `applyMove` and `applySolution` methods in `RubiksCubeGUI.java`:

```java
private void applyMove(int move) {
    cubeVisual.applyMoveAnimated(move, () -> {
        statusLabel.setText("Applied move: " + moveToString(move));
    });
}

private void applySolution(String solution) {
    final int[] index = {0};
    
    // Create sequential animation
    applyNextSolutionMove(solution, 0);
}

private void applyNextSolutionMove(String solution, int index) {
    if (index >= solution.length()) {
        javafx.application.Platform.runLater(() -> {
            statusLabel.setText("Solved! Cube is now in solved state.");
        });
        return;
    }
    
    char c = solution.charAt(index);
    int move = charToMove(c);
    
    if (move >= 0) {
        cubeVisual.applyMoveAnimated(move, () -> {
            // Apply next move after this one completes
            applyNextSolutionMove(solution, index + 1);
        });
    } else {
        // Skip non-move characters
        applyNextSolutionMove(solution, index + 1);
    }
}
```

### Assignment 6: Implement Move Animations

**Goal:** Add smooth 3D animations when moves are applied.

**Requirements:**
1. Implement `applyMoveAnimated()` method
2. Implement `getFaceCubies()` to select affected cubies
3. Use `RotateTransition` for smooth rotation
4. Animation duration: 300ms
5. Update manual moves to use animation
6. Update solution playback to use animation

**Bonus:** Add speed control slider for animation speed.

---

## Final Project: Complete Interactive Rubik's Cube Solver

### Project Requirements

Build a fully functional Rubik's Cube solver GUI with the following features:

#### Core Features (Required)

**1. 3D Visualization**
- 3D Rubik's Cube rendered with proper colors
- Mouse drag controls to rotate view
- Smooth, clear rendering with proper lighting
- All 6 faces colored correctly (Orange, Blue, White, Red, Green, Yellow)

**2. Manual Scrambling**
- Buttons for all 18 moves (U, U2, U', R, R2, R', F, F2, F', D, D2, D', L, L2, L', B, B2, B')
- Moves animate smoothly in 3D (300ms rotation)
- Visual state updates correctly after each move
- Moves are blocked during animation

**3. Random Scrambling**
- Button generates random scramble (20-30 moves)
- Scramble applies with animation
- Status shows number of moves applied

**4. Solve Function**
- Button that solves current cube state
- Uses your TwoPhase algorithm
- Displays solution string in status area
- Shows number of moves in solution
- Handles errors gracefully (Error 2-8)
- Runs in background thread (doesn't freeze UI)

**5. Solution Animation**
- Solution plays back automatically move by move
- Each move is animated and visible
- Smooth sequential playback
- Shows completion message

**6. Reset Function**
- Returns cube to solved state instantly
- Clears any status messages
- Updates visual correctly

#### Bonus Features (Optional)

**7. Animation Speed Control**
- Slider to adjust animation speed
- Range: 0.5x to 3x normal speed
- Updates apply immediately

**8. Move History**
- Display list of last 20 applied moves
- Undo last move button
- Clear history button
- Scroll view if more than visible area

**9. Import/Export**
- Load cube state from text file (your original format)
- Export current state to file
- Copy scramble sequence to clipboard
- Paste scramble from clipboard

**10. Statistics Panel**
- Track number of solves
- Average solution length
- Best (shortest) solution
- Total moves applied
- Current session time

**11. Visual Improvements**
- Custom color schemes
- Adjustable cube size
- Toggle grid lines on/off
- Camera zoom controls

**12. Additional Features**
- Step-by-step solution mode (manual advance)
- Pause/resume solution playback
- Highlight current moving face
- Sound effects for moves

### Deliverables

1. **Complete Working Application**
   - All core features functional
   - Clean, bug-free operation
   - Responsive UI

2. **Source Code**
   - Well-organized package structure
   - Clear class separation
   - Meaningful variable names
   - Proper error handling

3. **Documentation**
   - README.md with:
     - Project description
     - How to build and run
     - Features implemented
     - Controls/usage guide
     - Known issues or limitations
     - Future improvements
   - Code comments for complex logic

4. **Build System**
   - Maven or Gradle configuration
   - All dependencies specified
   - Build instructions

### Evaluation Criteria

**Functionality (40%)**
- All core features work correctly
- No crashes or major bugs
- Solver integration works properly
- Animations are smooth

**Code Quality (25%)**
- Clean, readable code
- Proper class organization
- Good naming conventions
- Adequate commenting
- Error handling

**User Experience (20%)**
- Intuitive interface
- Clear visual feedback
- Responsive controls
- Helpful status messages
- Professional appearance

**Integration (15%)**
- Proper connection between solver and visual
- Correct state synchronization
- Efficient updates
- No visual glitches

### Project Structure

Recommended file organization:

```
rubikscube-gui/
├── src/
│   └── rubikscube/
│       ├── Cubie.java (your existing solver)
│       ├── RubiksCube.java
│       ├── Tables.java
│       ├── TwoPhase.java
│       └── gui/
│           ├── RubiksCubeGUI.java (main application)
│           ├── RubiksCubeVisual.java (3D cube)
│           └── VisualCubie.java (single cubie)
├── pom.xml or build.gradle
└── README.md
```

### Tips for Success

**Development Approach:**
1. Start simple - get basic cube display working first
2. Test each feature before moving on
3. Commit working code frequently
4. Debug visually - print cube state when debugging

**Common Challenges:**
- **Coordinate mapping**: Test each corner/edge position individually
- **Animation timing**: Use callbacks to chain animations
- **UI freezing**: Always use background threads for solving
- **Visual glitches**: Ensure proper synchronization

**Testing Checklist:**
- [ ] Cube displays correctly in solved state
- [ ] All 18 manual moves work
- [ ] Scramble generates valid scrambles
- [ ] Solver finds solutions for scrambled cubes
- [ ] Solution animation plays correctly
- [ ] Reset returns to solved state
- [ ] Mouse rotation works smoothly
- [ ] No crashes or errors in console

**Performance Tips:**
- Use `SubScene` for 3D content (better than full `Scene`)
- Enable scene antialiasing for smooth edges
- Limit animation duration (too fast = hard to see, too slow = boring)
- Test with multiple scrambles to ensure consistency

### Submission Guidelines

When your project is complete:

1. **Test thoroughly**
   - Try all features multiple times
   - Test edge cases (already solved, unsolvable states)
   - Check for memory leaks (run for extended time)

2. **Clean up code**
   - Remove debug print statements
   - Add missing comments
   - Format code consistently
   - Remove unused imports

3. **Update README**
   - Add screenshots if possible
   - Document all features
   - Note any known issues

4. **Prepare demo**
   - Be ready to explain your code
   - Know how each component works
   - Understand the integration points

---

## Additional Resources

### JavaFX Documentation
- [Official JavaFX Documentation](https://openjfx.io/)
- [JavaFX 3D Tutorial](https://docs.oracle.com/javafx/2/3d_graphics/jfxpub-3d_graphics.htm)
- [JavaFX API Reference](https://openjfx.io/javadoc/21/)
- [JavaFX CSS Reference](https://openjfx.io/javadoc/21/javafx.graphics/javafx/scene/doc-files/cssref.html)

### Rubik's Cube Resources
- [Kociemba's Algorithm Explanation](https://kociemba.org/cube.htm)
- [Cube Notation Guide](https://ruwix.com/the-rubiks-cube/notation/)
- [Visual Cube State Representation](https://ruwix.com/online-puzzle-simulators/)
- [Rubik's Cube Wiki](https://www.speedsolving.com/wiki/)

### Java Best Practices
- **Separation of Concerns**: Keep UI code separate from logic
- **Threading**: Use background threads for long operations
- **Error Handling**: Handle exceptions gracefully, show user-friendly messages
- **Documentation**: Document complex algorithms and non-obvious code
- **Testing**: Test edge cases thoroughly

### Common Pitfalls to Avoid

**1. UI Thread Issues**
- ❌ **Wrong**: Running solver directly in button handler
- ✅ **Right**: Using `new Thread()` and `Platform.runLater()`

**2. Animation Conflicts**
- ❌ **Wrong**: Allowing moves during animation
- ✅ **Right**: Using `isAnimating` flag to block concurrent moves

**3. Coordinate Confusion**
- ❌ **Wrong**: Assuming solver and visual coordinates match directly
- ✅ **Right**: Using explicit mapping arrays for corners/edges

**4. Visual Z-Fighting**
- ❌ **Wrong**: Stickers exactly on cube surface
- ✅ **Right**: Small offset (0.1) to prevent flickering

**5. Memory Leaks**
- ❌ **Wrong**: Creating new objects in animation loops
- ✅ **Right**: Reusing objects and cleaning up listeners

**6. State Synchronization**
- ❌ **Wrong**: Updating visual without updating solver
- ✅ **Right**: Always update solver first, then sync visual

### Debugging Tips

**Visual Debugging:**
```java
// Print cube state
System.out.println("Corner Perm: " + Arrays.toString(cube.cornerPerm));
System.out.println("Edge Perm: " + Arrays.toString(cube.edgePerm));

// Highlight specific cubie
visualCubies[x][y][z].setStyle("-fx-border-color: red; -fx-border-width: 3;");
```

**Animation Debugging:**
```java
// Slow down to see what's happening
rotation.setDuration(Duration.millis(2000)); // 2 seconds

// Add debug callbacks
rotation.setOnFinished(e -> System.out.println("Animation complete!"));
```

**Thread Debugging:**
```java
// Verify you're on JavaFX thread
System.out.println("On FX thread: " + Platform.isFxApplicationThread());
```

---

## Conclusion

Congratulations! You've learned how to:

✅ Build JavaFX applications from scratch
✅ Create and manipulate 3D graphics
✅ Handle user interaction (mouse, keyboard, buttons)
✅ Integrate visual representation with algorithmic solver
✅ Create smooth animations and responsive UI
✅ Build a complete desktop application

Your Rubik's Cube solver is now transformed from a command-line tool into an interactive, visual application that you can showcase, share, and be proud of!

### What You've Accomplished

**Module 1**: Learned JavaFX basics - windows, scenes, layouts, buttons
**Module 2**: Mastered 3D graphics - shapes, cameras, lighting, transformations  
**Module 3**: Built a 3D Rubik's Cube with multi-colored faces
**Module 4**: Connected visual to your existing solver algorithm
**Module 5**: Created full GUI with controls and status display
**Module 6**: Added smooth 3D animations for realistic movement

### Next Steps

**Enhance Your Project:**
- Add timer for speedcubing practice
- Implement different cube sizes (2x2, 4x4)
- Create move tutorials/patterns
- Add multiplayer scramble challenges
- Build optimal solver comparison

**Share Your Work:**
- Upload to GitHub with screenshots
- Create a video demonstration
- Write a blog post about the development process
- Present at a local coding meetup

**Explore Related Topics:**
- Other twisty puzzles (Pyraminx, Megaminx, Square-1)
- Computer vision for cube scanning
- Machine learning for solving
- Web version with JavaFX WebStart or rewrite in JavaScript

**Continue Learning:**
- Advanced JavaFX effects and animations
- 3D game development
- Algorithm optimization
- UI/UX design principles

### Final Thoughts

Building this GUI is more than just adding a visual layer to your solver - it's about making your work accessible and interactive. You've taken a complex algorithmic project and transformed it into something anyone can use and appreciate.

Remember:
- **Start small, iterate often** - Don't try to implement everything at once
- **Test constantly** - Catch bugs early before they compound
- **Ask for help** - The JavaFX and cubing communities are helpful
- **Have fun** - This is a creative project, enjoy the process!

You now have the skills to build professional desktop applications with complex 3D visualizations. This is a strong portfolio piece that demonstrates:
- Object-oriented design
- Algorithm implementation
- 3D graphics programming
- User interface development
- Software engineering best practices

**Happy coding, and enjoy your interactive Rubik's Cube solver!** 🎉🧩

---

## Appendix: Quick Reference

### Move Encoding
```
U=0, U2=1, U'=2
R=3, R2=4, R'=5
F=6, F2=7, F'=8
D=9, D2=10, D'=11
L=12, L2=13, L'=14
B=15, B2=16, B'=17
```

### Face Color Mapping
```
0 = Orange (U - Up)
1 = Blue (R - Right)
2 = White (F - Front)
3 = Red (D - Down)
4 = Green (L - Left)
5 = Yellow (B - Back)
```

### Corner Positions
```
URF=0, UFL=1, ULB=2, UBR=3
DFR=4, DLF=5, DBL=6, DRB=7
```

### Edge Positions
```
UR=0, UF=1, UL=2, UB=3
DR=4, DF=5, DL=6, DB=7
FR=8, FL=9, BL=10, BR=11
```

### Common JavaFX Imports
```java
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.geometry.*;
```

### Useful Code Snippets

**Background Task:**
```java
new Thread(() -> {
    // Do work
    Platform.runLater(() -> {
        // Update UI
    });
}).start();
```

**Simple Animation:**
```java
RotateTransition rt = new RotateTransition(Duration.millis(300), node);
rt.setByAngle(90);
rt.setAxis(Rotate.Y_AXIS);
rt.play();
```

**File Chooser:**
```java
FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Open Cube State");
File file = fileChooser.showOpenDialog(stage);
```

---

## Glossary

**Artifact** - A self-contained piece of code or content in the course

**BorderPane** - JavaFX layout that divides space into 5 regions

**Cubie** - Individual small cube in a Rubik's Cube; also your solver class

**G1 Subgroup** - Intermediate cube state in Kociemba's algorithm

**JavaFX** - Java's GUI framework for desktop applications

**Node** - Any visual element in JavaFX scene graph

**PhongMaterial** - Material type for 3D objects with lighting

**Platform.runLater()** - Method to execute code on JavaFX UI thread

**Scene Graph** - Hierarchical tree structure of visual elements

**SubScene** - Embedded 3D scene within a larger 2D scene

**Stage** - Top-level window in JavaFX application

**Two-Phase Algorithm** - Kociemba's method for solving Rubik's Cube

---

## Troubleshooting

### Problem: JavaFX not found
**Solution:** Make sure you've added JavaFX dependencies to Maven/Gradle. For Java 11+, JavaFX is separate.

### Problem: 3D scene appears black
**Solution:** Check that you've enabled depth buffer: `new Scene(root, w, h, true)` and added lighting.

### Problem: Cube colors don't update
**Solution:** Verify `syncWithSolver()` is being called after solver state changes.

### Problem: Animation is choppy
**Solution:** Ensure animations aren't overlapping. Use `isAnimating` flag to prevent concurrent moves.

### Problem: Coordinates seem wrong
**Solution:** Double-check your corner/edge position mappings. Test one piece at a time.

### Problem: UI freezes during solve
**Solution:** Run solver in background thread using `new Thread()`.

### Problem: Z-fighting (flickering faces)
**Solution:** Add small offset (0.1) to stickers so they're slightly above cube surface.

### Problem: Mouse controls don't work
**Solution:** Ensure you're adding transforms to the correct Group and listening on the correct Scene/SubScene.

---

## FAQ

**Q: Can I use a different color scheme?**
A: Yes! Just modify the `FACE_COLORS` array in `RubiksCubeVisual`.

**Q: How do I make the cube bigger/smaller?**
A: Change the `SIZE` constant in `VisualCubie` class.

**Q: Can I add more faces (4x4, 5x5)?**
A: Yes, but you'd need to modify the solver algorithm too. Start with your 3x3 working perfectly first.

**Q: Why use rectangles instead of textured Box?**
A: Rectangles give us independent control over each face color, which Box materials don't support.

**Q: Can I deploy this as an executable?**
A: Yes! Use jpackage (Java 14+) or tools like Launch4j to create native executables.

**Q: How do I add sound effects?**
A: Use JavaFX `AudioClip` class to play sounds on move events.

**Q: Can I make this into a web app?**
A: Not directly - JavaFX is for desktop. Consider rewriting with Three.js for web.

**Q: My animation doesn't wait for previous to finish**
A: Use callbacks or sequential chaining as shown in Module 6.

**Q: How do I save/load cube states?**
A: Use your existing file format or serialize the Cubie object to JSON/XML.

**Q: Can I add a timer for speedsolving?**
A: Yes! Use JavaFX `Timeline` or `AnimationTimer` for precise timing.

---

## Acknowledgments

This course was created to help transform your Rubik's Cube solver from a command-line tool into an interactive desktop application. 

**Resources Used:**
- JavaFX official documentation
- Kociemba's Two-Phase Algorithm
- The Odin Project course structure inspiration

**Community:**
- Thanks to the JavaFX community for excellent 3D graphics support
- Speedcubing community for cube notation standards
- Open source Rubik's Cube solver projects for inspiration

---

**Course Complete! 🎓**

You now have everything you need to build your interactive Rubik's Cube solver. Start with Module 1 and work your way through. Don't hesitate to experiment and make it your own!

**Good luck, and happy coding!** 🚀