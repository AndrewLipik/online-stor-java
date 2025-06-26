package org.example.slstoreprog;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class AdminRoot {
    public void OpenAdminRoot(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("adminRoot.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1900, 1000);
            stage.setTitle("Admin");
            stage.setScene(scene);
            stage.show();
            URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
            String path = null;
            String path2;
            try {
                path2 = Paths.get(u1.toURI()).toFile().getAbsolutePath();
                path  = STR."file:///\{path2}";
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Image image = new Image(path);
            stage.getIcons().add(image);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
