package org.example.slstoreprog;

import DBConnection.DBHandler;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InBasketController implements Initializable {
    @FXML
    private Label lbModelInBasketIn;
    @FXML
    private Label lbPriceInBasketIn;
    @FXML
    private Label lbAboutGadgetIn;
    @FXML
    private Label lbColorInBasketIn;
    @FXML
    private Button deleteGadgetFromBasket;
    @FXML
    private ImageView imageInBasketIn;
    private DBHandler dbHandler;
    private String tel2;
    private String updateBasketText="";
    private String[] listImagesPath;
    int countClick = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbHandler = new DBHandler();
    }

    public Label getLbAboutGadgetIn() {
        return lbAboutGadgetIn;
    }
    public Label getLbModelInBasketIn() {
        return lbModelInBasketIn;
    }
    public Label getLbPriceInBasketIn(){
        return lbPriceInBasketIn;
    }
    public Label getLbColorInBasketIn(){
        return lbColorInBasketIn;
    }
    public ImageView getImageInBasketIn(){
        return imageInBasketIn;
    }

    public void setListImagesPath(String[] listImagesPath){
        this.listImagesPath = listImagesPath;
    }

    public void btnCloseInBasket(MouseEvent mouseEvent) {
        lbAboutGadgetIn.getScene().getWindow().hide();
    }

    public void deleteGadgetFromBasket(MouseEvent mouseEvent) throws SQLException {
        Duration duration=Duration.millis(90);
        TranslateTransition translateTransition = new TranslateTransition(duration,deleteGadgetFromBasket);
        translateTransition.setByY(2);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.play();
        String sql = "SELECT * from programUsers";
        Connection connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        String basket;
        String tel;
        while (resultSet.next()) {
            tel = resultSet.getString("telNumber");
            basket = resultSet.getString("basket");
            if(LogInController.userAccountTel.equals(tel)){
                String[] words = basket.split(";");
                for(String split:words){
                    if (split.length() > 1) {
                        if (!split.equals(lbModelInBasketIn.getText())) {
                            updateBasketText += split + ";";
                        }
                    }

                }
            }
            }
            tel ="";
            basket ="";
                try {
                    preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet2 = preparedStatement.executeQuery();
                    String rows = "UPDATE programUsers SET basket=? WHERE telNumber=?";
                    PreparedStatement statement= connection.prepareStatement(rows);
                    while (resultSet2.next()) {
                        tel = resultSet2.getString("telNumber");
                      //  basket = resultSet2.getString("basket");
                        if(LogInController.userAccountTel.equals(tel)){
                            tel2= tel;
                            break;
                        }
                    }
                    statement.setString(1,updateBasketText);
                    statement.setString(2,tel2);
                    statement.executeUpdate();
                    deleteGadgetFromBasket.setText("Удалено");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    public void btnBuyInBasketIn(MouseEvent mouseEvent) throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(InBasketController.class.getResource("payment.fxml"));
        Scene scene = new Scene(loader.load(), 1900, 1000);
        PaymentController paymentController = loader.getController();
        paymentController.getImageInPayment().setImage(imageInBasketIn.getImage());
        paymentController.getLbModelInPayment().setText(lbModelInBasketIn.getText());
        paymentController.getLbPriceInPayment().setText(lbPriceInBasketIn.getText());
        primaryStage.setTitle("Покупка");
        URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
        String path = null;
        try {
            path = STR."file:///\{Paths.get(u1.toURI()).toFile().getAbsolutePath()}";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        Image image = new Image(path);
        primaryStage.getIcons().add(image);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void btnNextImage(MouseEvent mouseEvent) {
        countClick++;
        if (countClick >= listImagesPath.length) {
            countClick = 0;
        }
        Image image = new Image("file:///" + listImagesPath[countClick]);
        imageInBasketIn.setImage(image);


    }

    public void btnBackImage(MouseEvent mouseEvent) {
        countClick--;
        if (countClick >= listImagesPath.length) {
            countClick = 0;
        }
        if (countClick < 0){
            countClick = listImagesPath.length-1;
        }
        Image image = new Image("file:///" + listImagesPath[countClick]);
        imageInBasketIn.setImage(image);



    }
}
