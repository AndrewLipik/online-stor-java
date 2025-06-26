package org.example.slstoreprog;

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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import searchGadgets.SearchGadgets;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SearchGadgetsElement extends ListCell<SearchGadgets> implements Initializable {
    @FXML
    private Label lbModelGadget;
    @FXML
    private Button btnOpenGadget;
    @FXML
    private ImageView imageSearchGadget;
    @FXML
    private Label lbAboutGadget;
    public AnchorPane getRoot() {
        return root;
    }
    @FXML
    private AnchorPane root;
    @FXML
    private Label lbPriceGadget;
    @FXML
    private Label lbColorGadget;
    private SearchGadgets model;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateSelected(false);
        for(Node node:getRoot().getChildrenUnmodifiable()) {
            node.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        commitEdit(model);
                    }
                }
            });
        }
        setGraphic(root);
    }
    public static SearchGadgetsElement newInstance() {
        FXMLLoader loader = new FXMLLoader(SearchGadgetsElement.class.getResource("searchGadgets.fxml"));
        try {
            loader.load();
            return loader.getController();
        } catch (IOException ex) {
            return null;
        }
    }
    @Override
    public void commitEdit(SearchGadgets newValue) {
        newValue = (newValue == null) ? this.model : newValue;
        super.commitEdit(newValue);
        newValue.setButtonText(btnOpenGadget.textProperty().get());
    }
    @Override
    protected void updateItem(SearchGadgets item, boolean empty) {
        super.updateItem(item, empty);
        getRoot().getChildrenUnmodifiable().forEach(c -> c.setVisible(!empty));
        if (!empty && item != null && !item.equals(this.model)) {
            imageSearchGadget.imageProperty().set(new Image("file:///"+item.getImagePath()));
            btnOpenGadget.textProperty().set(item.getButtonText());
            lbPriceGadget.textProperty().set(item.getLbDescriptionPrice());
            lbAboutGadget.textProperty().set(item.getLbDescriptionAbout());
            lbModelGadget.textProperty().set(item.getLbDescription());
            lbColorGadget.textProperty().set(item.getLbDescriptionColor());

            Map<String, String> colors = new HashMap<>();

            colors.put("чёрный титан", "#696969");
            colors.put("белый титан", "#FFFFFF");
            colors.put("синий титан", "#2E3348");
            colors.put("натуральный титан", "#DEB887");
            colors.put("чёрный", "#000000");
            colors.put("голубой", "#4169E1");
            colors.put("зелёный", "#228B22");
            colors.put("жёлтый", "#FFFF00");
            colors.put("розовый", "#EE82EE");
            colors.put("синий", "#000080");
            colors.put("серебристый", "#A9A9A9");
            colors.put("золотой", "#DAA520");
            colors.put("чёмный фиолет", "");
            colors.put("графитовый", "");
            colors.put("небесно-голубой", "");
            colors.put("белый", "#FFFFFF");
            colors.put("фиолетовый", "#8A2BE2");
            colors.put("тёмно-зелёный", "#008000");
            colors.put("серый космос", "#C0C0C0");

            for (Map.Entry<String, String> entry : colors.entrySet()) {
                if (entry.getKey().equals(item.getLbDescriptionColor())) {
                    lbColorGadget.setTextFill(Paint.valueOf(entry.getValue()));
                }
            }
        }

        btnOpenGadget.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String textPrice=item.getLbDescriptionPrice();
                    String textModel=item.getLbDescription();
                    String textAbout=item.getLbDescriptionAbout();
                    String textColor=item.getLbDescriptionColor();
                    String textCount=item.getLbDescriptionCount();
                    String[] imagesList=item.getListImagesPaths();
                    String textSellersNumber=item.getLbDescriptionSellersNumber();
                    File file=new File(item.getImagePath());
                    Image imageIn=new Image("file:///"+file);
                    call(textPrice,textModel,textAbout,textColor,textCount,textSellersNumber,imageIn,imagesList);
                } catch (IOException e) {
                    throw new RuntimeException(e);}
            }
        });
        this.model=item;
    }
    public void call(String textPrice,String textModel,String textAbout,String textColor,String textCount,String textSellersNumber,Image imageIn, String[] imagesList) throws IOException {

        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(AboutGadgetController.class.getResource("aboutGadget.fxml"));
        Scene scene = new Scene(loader.load(), 1900, 1000);
        AboutGadgetController aboutGadgetController = loader.getController();
        aboutGadgetController.getLbPriceInGadget().setText(textPrice);
        aboutGadgetController.getLbModelInGadget().setText(textModel);
        aboutGadgetController.getLbAboutInGadget().setText(textAbout);
        aboutGadgetController.getLbColorInGadget().setText(textColor);
        aboutGadgetController.getLbCountInGadget().setText(textCount);
        aboutGadgetController.getLbSellersNumberInGadget().setText(textSellersNumber);
        aboutGadgetController.getImageInGadget().setImage(imageIn);
        aboutGadgetController.setListImagesPath(imagesList);
        primaryStage.setTitle("Подробнее");
        primaryStage.setScene(scene);
        URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
        String path = null;
        String path2;
        try {
            assert u1 != null;
            path2 = Paths.get(u1.toURI()).toFile().getAbsolutePath();
            path = STR."file:///\{path2}";
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
        getRoot().getChildrenUnmodifiable().forEach(c -> {
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
