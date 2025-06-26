module org.example.slstoreprog {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens org.example.slstoreprog to javafx.fxml;
    exports org.example.slstoreprog;
}