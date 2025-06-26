package org.example.slstoreprog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1900, 1000);
            stage.setTitle("Sl Store");
            stage.setScene(scene);
            URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
            String path = null;
            try {
                String path2 = Paths.get(u1.toURI()).toFile().getAbsolutePath();
                path  = "file:///"+path2;
            } catch (URISyntaxException e) {
               e.printStackTrace();
           }
            Image image = new Image(path);
            stage.getIcons().add(image);

           
            stage.show();
    }
        public static void main(String[] args) {
        launch();
    }
}