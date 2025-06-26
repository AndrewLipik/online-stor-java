package org.example.slstoreprog;

import basket.Basket;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class BasketWindow extends ListCell<Basket> implements Initializable {
    @FXML
    private Button btnOpenBasket;
    @FXML
    private ImageView BasketImage;
    @FXML
    private AnchorPane basketRoot;
    @FXML
    private Label lbPriceInBasket;
    @FXML
    private Label lbModelInBasket;
    @FXML
    private Label lbAboutGadget;

    public AnchorPane getBasketRoot() {
        return basketRoot;
    }
    private Basket model;

    String text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateSelected(false);
        for(Node node:getBasketRoot().getChildrenUnmodifiable()) {
            node.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        commitEdit(model);
                    }
                }
            });
        }
        setGraphic(basketRoot);
    }

    public static BasketWindow newInstance() {
        FXMLLoader loader = new FXMLLoader(BasketWindow.class.getResource("basket.fxml"));
        try {
            loader.load();
            return loader.getController();
        } catch (IOException ex) {
            return null;
        }
    }
    @Override
    public void commitEdit(Basket newValue) {
        newValue = (newValue == null) ? this.model : newValue;
        super.commitEdit(newValue);
        newValue.setButtonText(btnOpenBasket.textProperty().get());
    }
    @Override
    protected void updateItem(Basket item, boolean empty) {
        super.updateItem(item, empty);
        getBasketRoot().getChildrenUnmodifiable().forEach(c -> c.setVisible(!empty));
        if (!empty && item != null && !item.equals(this.model)) {
            BasketImage.imageProperty().set(new Image("file:///"+item.getImagePath()));
            btnOpenBasket.textProperty().set(item.getButtonText());
            btnOpenBasket.setVisible(true);
            lbPriceInBasket.textProperty().set(item.getLbDescriptionPrice());
            lbModelInBasket.textProperty().set(item.getLbDescription());
            lbAboutGadget.textProperty().set(item.getLbDescriptionAbout());
        }
        btnOpenBasket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String textPrice = item.getLbDescriptionPrice();
                    String textModel = item.getLbDescription();
                    String textAbout=item.getLbDescriptionAbout();
                    String textColor=item.getLbDescriptionColor();
                    File file = new File(item.getImagePath());
                    System.out.println("file "+file);
                    String[] listImages = item.getListImagesPaths();
                    Image imageIn = new Image("file:///"+file);
                    BasketCall(textPrice, textModel, textAbout, textColor, imageIn, listImages);
                }catch(Exception e){
                        throw new RuntimeException(e);
                    }
                }
        });
        this.model=item;
    }

    public void BasketCall(String textPrice,String textModel,String textAbout,String textColor,Image imageIn, String[] listImages) throws Exception{
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(BasketWindow.class.getResource("inBasket.fxml"));
        Scene scene = new Scene(loader.load(), 1900, 1000);
        InBasketController inBasketController = loader.getController();
        inBasketController.getLbPriceInBasketIn().setText(textPrice);
        inBasketController.getLbModelInBasketIn().setText(textModel);
        inBasketController.getLbColorInBasketIn().setText(textColor);
        System.out.println("textAbout "+imageIn);
        inBasketController.getLbAboutGadgetIn().setText(textAbout);
        inBasketController.getImageInBasketIn().setImage(imageIn);
        inBasketController.setListImagesPath(listImages);
        primaryStage.setTitle("Корзина");
        primaryStage.setScene(scene);
        URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
        String path = null;
        String path2;
        try {
            path2 = Paths.get(u1.toURI()).toFile().getAbsolutePath();
            path = "file:///"+path2;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Image image = new Image(path);
        primaryStage.getIcons().add(image);

        primaryStage.show();

    }

    @Override
    public void updateSelected (boolean selected){
        super.updateSelected(selected);
        getBasketRoot().getChildrenUnmodifiable().forEach(c -> {
            c.setMouseTransparent(!selected);
        });
        if (selected) {
            startEdit();
        } else {
            if (model != null) {
                commitEdit(model);
            }
        }
    }
}
