package org.example.slstoreprog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
public class AboutStore {

    @FXML
    private ImageView imageAboutSl;
    @FXML
    private Label lbAboutSlStore;
    private int count;
    Image image;

    private void openWebPage(String url) {
        try{
            URI uri=new URI(url);
            Desktop.getDesktop().browse(uri);

        }catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    public void bntCloseStore(MouseEvent mouseEvent) {
        lbAboutSlStore.getScene().getWindow().hide();
    }

    public void nextImageAboutSl(MouseEvent mouseEvent) {
        count++;
        if(count==1) {
            lbAboutSlStore.setText("Выберите подходящий телефон");
            URL u1 = getClass().getResource("/org/example/slstoreprog/main2.png");
            image = new Image(u1.toString());
        }
        if(count==2) {
            lbAboutSlStore.setText("Самые лучшие цены");
            URL u1 = getClass().getResource("/org/example/slstoreprog/main3.png");
            image = new Image(u1.toString());
        }
        if(count==5) {
            lbAboutSlStore.setText("Огромный выбор цветов");
            URL u1 = getClass().getResource("/org/example/slstoreprog/blackIphonePro.png");
            image = new Image(u1.toString());
        }


        imageAboutSl.setImage(image);

    }

    public void backAboutSl(MouseEvent mouseEvent) {
        count--;
    }

    public void btnBlackColor(MouseEvent mouseEvent) {
        URL u1 = getClass().getResource("/org/example/slstoreprog/blackIphonePro.png");
        image = new Image(u1.toString());
        imageAboutSl.setImage(image);
    }

    public void btnWhiteColor(MouseEvent mouseEvent) {
        URL u1 = getClass().getResource("/org/example/slstoreprog/whiteIphonePro.png");
        image = new Image(u1.toString());
        imageAboutSl.setImage(image);
    }

    public void btnTitaniumColor(MouseEvent mouseEvent) {
        URL u1 = getClass().getResource("/org/example/slstoreprog/titaniumIphonePro.png");
        image = new Image(u1.toString());
        imageAboutSl.setImage(image);
    }

    public void btnBlueColor(MouseEvent mouseEvent) {
        URL u1 = getClass().getResource("/org/example/slstoreprog/blueIphonePro.png");
        image = new Image(u1.toString());
        imageAboutSl.setImage(image);
    }

    public void btnSite(MouseEvent mouseEvent) {
        String url="https://slstoreweb.tilda.ws/";
        openWebPage(url);
    }

    public void btnTg(MouseEvent mouseEvent) {
        String url="https://t.me/SLStoreTelegramBot";
        openWebPage(url);
        System.out.println("t");
    }
}

