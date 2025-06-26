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
import javafx.scene.paint.Color;
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
import java.util.Random;
import java.util.ResourceBundle;

public class AboutGadgetController implements Initializable {
    private DBHandler dbHandler;
    @FXML
    private Label lnOnBtnBasket;
    @FXML
    private Label lbPriceInGadget;
    @FXML
    private Label lbModelInGadget;
    @FXML
    private Label lbAboutInGadget;
    @FXML
    private Label lbColorInGadget;
    @FXML
    private Label lbSellersNumberInGadget;
    @FXML
    private Label lbCountInGadget;

    @FXML
    private Button btnAddInBasket;
    @FXML
    private Button btnBuyGadget;
    @FXML
    private Button btnNextImage;
    @FXML
    private ImageView imageInGadget;
    static String textAboutGadget;
    static String textModel;
    private int countPrice;
    private String[] listImagesPath;
    String priceStr = "";
    double priceBYN = 0.0;
    double priceDouble= 0.0;
    public Label getLbPriceInGadget(){
        return lbPriceInGadget;
    }
    public Label getLbModelInGadget() {
        return lbModelInGadget;
    }
    public Label getLbSellersNumberInGadget() {
        return lbSellersNumberInGadget;
    }
    public Label getLbCountInGadget(){
        return lbCountInGadget;
    }
    public Label getLbAboutInGadget(){
        return lbAboutInGadget;
    }
    public Label getLbColorInGadget(){return lbColorInGadget;}
    public ImageView getImageInGadget() {
        return imageInGadget;
    }

  /*  public AboutGadgetController(String[] listPaths){
        this.listImagesPath = listPaths;
    }
   */
    public void setListImagesPath(String[] listImagesPath){
        this.listImagesPath = listImagesPath;
    }
    public String[] getListImagesPath(){
        return listImagesPath;
    }

    private String name2;
    private String basket;
    int countClick = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbHandler=new DBHandler();
        Random random=new Random();
        Color randomColor=Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public void btnAddInBasket(MouseEvent mouseEvent) throws IOException {
        Duration duration=Duration.millis(90);
        TranslateTransition translateTransition = new TranslateTransition(duration,btnAddInBasket);
        translateTransition.setByY(2);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.play();

        if (LogInController.userAccountName.equals("")) {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(AboutGadgetController.class.getResource("Login.fxml"));
            Scene scene = new Scene(loader.load(), 1000, 600);
            primaryStage.setTitle("Войдите в аккаунт");
            URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
            String path = null;
            String path2;
            try {
                path2 = Paths.get(u1.toURI()).toFile().getAbsolutePath();
                path  = "file:///"+path2;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Image image = new Image(path);
            primaryStage.getIcons().add(image);
            primaryStage.setScene(scene);


            primaryStage.setScene(scene);
            primaryStage.show();



        } else {
            lnOnBtnBasket.setText("Добавлено");
            Connection connection = dbHandler.getDbConnection();
            String sql = "SELECT * from programUsers";
            textAboutGadget = lbModelInGadget.getText();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                String rows = "UPDATE programUsers SET basket=? WHERE name=?";
                PreparedStatement statement= connection.prepareStatement(rows);

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    basket=resultSet.getString("basket");
                        if(LogInController.userAccountName.equals(name)){
                            name2=name;
                            break;
                        }
                    }
                statement.setString(1,basket+";"+textAboutGadget);
                statement.setString(2,name2);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnCloseRootAboutGadget(MouseEvent mouseEvent) {
        lbPriceInGadget.getScene().getWindow().hide();
    }
    public void btnBuyGadget(MouseEvent mouseEvent) throws IOException {
        Duration duration=Duration.millis(90);
        TranslateTransition translateTransition = new TranslateTransition(duration,btnBuyGadget);
        translateTransition.setByY(2);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.play();
        textModel=lbModelInGadget.getText();
        if (LogInController.userAccountName.equals("")) {
            LogInController logInController = new LogInController();
            System.out.println(logInController.user);
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(AboutGadgetController.class.getResource("Login.fxml"));
            Scene scene = new Scene(loader.load(), 1000, 600);
            primaryStage.setTitle("Войдите в аккаунт");
            URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
            String path = null;
            String path2;
            try {
                path2 = Paths.get(u1.toURI()).toFile().getAbsolutePath();
                path  = "file:///"+path2;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            Image image = new Image(path);
            primaryStage.getIcons().add(image);
            primaryStage.setScene(scene);
            primaryStage.setScene(scene);
            primaryStage.show();


        } else {
                LogInController logInController = new LogInController();
                System.out.println(logInController.user);
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(AboutGadgetController.class.getResource("payment.fxml"));
                Scene scene = new Scene(loader.load(), 1900, 1000);
                PaymentController paymentController = loader.getController();
                paymentController.getImageInPayment().setImage(imageInGadget.getImage());
                paymentController.getLbModelInPayment().setText(lbModelInGadget.getText());
                paymentController.getLbPriceInPayment().setText(lbPriceInGadget.getText());
                primaryStage.setTitle("Покупка");
                URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
                String path = null;
                String path2;
                try {
                    path2 = Paths.get(u1.toURI()).toFile().getAbsolutePath();
                    path  = "file:///"+path2;
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                Image image = new Image(path);
                primaryStage.getIcons().add(image);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
    }

    public void btnPrice(MouseEvent mouseEvent) {
        countPrice++;
        if (countPrice == 1) {
            priceStr = lbPriceInGadget.getText();
            priceDouble = Double.parseDouble(priceStr);
             priceBYN = priceDouble*3.27;
        }
        if (countPrice%2 == 0) {
            lbPriceInGadget.setText(priceDouble + " $");
        }
        else {
            lbPriceInGadget.setText(priceBYN + " BYN");
        }
    }

    public void btnNextImage(MouseEvent mouseEvent) {
        countClick++;

        if (countClick >= listImagesPath.length) {
            countClick = 0;
        }
        Image image = new Image("file:///" + listImagesPath[countClick]);
        imageInGadget.setImage(image);

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
        imageInGadget.setImage(image);


    }
}
