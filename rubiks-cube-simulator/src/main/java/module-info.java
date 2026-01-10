module com.aaf1007 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.aaf1007 to javafx.fxml;
    exports com.aaf1007;
}
