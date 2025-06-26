package org.example.slstoreprog;

import DBConnection.DBHandler;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    @FXML
    private ComboBox postOffices;
    @FXML
    private Label lbMonth;
    @FXML
    private Label lbDay;
    @FXML
    private Label lbTime;
    @FXML
    private Label lbModelInPayment;
    @FXML
    private Label lbPriceInPayment;
    @FXML
    private Button btnDelivery;
    @FXML
    private Button btnDeliveryman;
    @FXML
    private Button btnPostOffice;
    @FXML
    private ImageView imageInPayment;

    @FXML
    private TextField entryCity;
    @FXML
    private TextField entryDistrict;
    @FXML
    private TextField entryStreet;
    @FXML
    private TextField entryHouse;
    @FXML
    private TextField entryFlat;
    @FXML
    private Label lbDelivery;
    private int month = 1;
    private int day;
    private int timeCount;
    static String paymentComboBoxTex = "";
    String name;
    private DBHandler dbHandler;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;
    private String rows;
    private String rows2;
    //   private String address;
    private String name2;
    private String nameUser;
    private String deliveryAdmin;
    private String deliveryWay;
    private Boolean deliveryFlag = true;

    public ImageView getImageInPayment() {
        return imageInPayment;
    }
    public Label getLbModelInPayment() {
        return lbModelInPayment;
    }
    public Label getLbPriceInPayment(){
        return lbPriceInPayment;
    }

    ObservableList<String> comboBoxList = FXCollections.observableArrayList();
    String[] time = new String[]{" 9:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00", "18:00-19:00", "19:00-20:00",
            "20:00-21:00", "21:00-22:00", "22:00-23:00"};

    boolean transparentFlagPost = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dbHandler = new DBHandler();
        FilteredList<String> filteredItems = new FilteredList<String>(comboBoxList, t -> true);

        postOffices.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = postOffices.getEditor();
            if (editor.getText() != null) {
                postOffices.show();
                paymentComboBoxTex = editor.getText();
            }
            try {
                final String selected = (String) postOffices.getSelectionModel().getSelectedItem();
                Platform.runLater(() -> {
                    if (selected == null || !selected.equals(editor.getText())) {
                        filteredItems.setPredicate(item -> {

                            if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                                return true;
                            } else {
                                return false;
                            }
                        });
                    }
                });
                postOffices.setItems(filteredItems);
            } catch (NullPointerException nullPointerException) {
                System.out.println(nullPointerException);
            }
        });
        try {
            URL u1 = getClass().getResource("/org/example/slstoreprog/postOffices.txt");
            System.out.println("u1 " + u1);
            String path = Paths.get(u1.toURI()).toFile().getAbsolutePath();
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                comboBoxList.add(line);
            }
            bufferedReader.close();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        postOffices.setItems(comboBoxList);


        Date date = new Date();
        Calendar calendarMonth = Calendar.getInstance();
        Calendar calendarDay = Calendar.getInstance();
        calendarDay.add(Calendar.DAY_OF_MONTH, day);
        String dat = String.valueOf(calendarDay.get(Calendar.DAY_OF_MONTH));
        lbDay.setText(dat);

        calendarMonth.add(Calendar.MONTH, month);
        System.out.println("narrow " + calendarMonth.getTime());
        String dat2 = String.valueOf(calendarMonth.get(Calendar.MONTH));
        if (dat2.equals("1")) {
            lbMonth.setText("Январь");
        }
        if (dat2.equals("2")) {
            lbMonth.setText("Февраль");
        }
        if (dat2.equals("3")) {
            lbMonth.setText("Март");
        }
        if (dat2.equals("4")) {
            lbMonth.setText("Апрель");
        }
        if (dat2.equals("5")) {
            lbMonth.setText("Май");
        }
        if (dat2.equals("6")) {
            lbMonth.setText("Июнь");
        }
        if (dat2.equals("7")) {
            lbMonth.setText("Июль");
        }
        if (dat2.equals("8")) {
            lbMonth.setText("Август");
        }
        if (dat2.equals("9")) {
            lbMonth.setText("Сентябрь");
        }
        if (dat2.equals("10")) {
            lbMonth.setText("Октябрь");
        }
        if (dat2.equals("11")) {
            lbMonth.setText("Ноябрь");
        }
        if (dat2.equals("0")){
            lbMonth.setText("Декабрь");
        }

    }

    public void btnClosePaymentRoot(MouseEvent mouseEvent) {
        postOffices.getScene().getWindow().hide();
    }

    public void bntNextMonth(MouseEvent mouseEvent) {
        month++;
        ResourceBundle resourceBundle = null;
        URL url = null;
        initialize(url, resourceBundle);
    }

    public void btnBackMonth(MouseEvent mouseEvent) {
        month--;
        ResourceBundle resourceBundle = null;
        URL url = null;
        initialize(url, resourceBundle);
    }

    public void bntNextDay(MouseEvent mouseEvent) {
        day++;
        ResourceBundle resourceBundle = null;
        URL url = null;
        initialize(url, resourceBundle);

    }

    public void btnBackDay(MouseEvent mouseEvent) {
        day--;
        ResourceBundle resourceBundle = null;
        URL url = null;
        initialize(url, resourceBundle);
    }

    public void btnBackTime(MouseEvent mouseEvent) {
        timeCount--;
        System.out.println("timeCount 1 " + timeCount);
        if (timeCount < 0) {
            timeCount = timeCount + time.length;
            System.out.println("timeCount 2 " + timeCount);
        }
        System.out.println("timeCount 3 " + timeCount);
        lbTime.setText(time[timeCount]);
    }

    public void btnNextTime(MouseEvent mouseEvent) {
        timeCount++;
        if (timeCount >= time.length) {
            timeCount = timeCount - time.length;
        }
        lbTime.setText(time[timeCount]);

    }

    public void btnDelivery(MouseEvent mouseEvent) {
        deliveryFlag = true;
        Duration duration = Duration.millis(200);
        TranslateTransition translateTransition = new TranslateTransition(duration, btnDelivery);
        translateTransition.setByY(2);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.play();
        deliveryWay = STR."\{entryCity.getText()} \{entryDistrict.getText()} \{entryStreet.getText()} \{entryHouse.getText()} \{entryFlat.getText()}";
        connection = dbHandler.getDbConnection();
        String sql = "SELECT * from base2_tg";
        String sql2 = "SELECT * from programUsers";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement2 = connection.prepareStatement(sql2);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            rows = "UPDATE base2_tg SET address=?, whoBuy=? WHERE name=?";
            rows2 = "UPDATE programUsers SET delivery=? WHERE name=?";
            PreparedStatement statement = connection.prepareStatement(rows);
            PreparedStatement statement2 = connection.prepareStatement(rows2);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                if (lbModelInPayment.getText().equals(name)) {
                    name2 = name;

                    if(transparentFlagPost){
                        if (!Objects.equals(postOffices.getValue().toString(), "")) {
                            deliveryAdmin = postOffices.getValue().toString() + " (отделение почты)";
                            btnDelivery.setStyle("-fx-background-color: #2594f7; -fx-background-radius: 10");
                            btnDelivery.setText("Закказано!");
                        }
                        else {
                            btnDelivery.setStyle("-fx-background-color: red; -fx-background-radius: 10");
                            btnDelivery.setText("Выберете отделенние почты");
                        }

                    }
                    if(!transparentFlagPost){
                        if(Objects.equals(entryCity.getText(), "")){
                                deliveryFlag = false;
                                entryCity.setStyle("-fx-background-color: #18171a; -fx-border-color: red; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                        "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }else{
                            entryCity.setStyle("-fx-background-color: #18171a; -fx-border-color: #5e5e5e; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }
                        if(Objects.equals(entryDistrict.getText(), "")){
                            deliveryFlag = false;
                            entryDistrict.setStyle("-fx-background-color: #18171a; -fx-border-color: red; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }
                        else {
                            entryDistrict.setStyle("-fx-background-color: #18171a; -fx-border-color: #5e5e5e; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }
                        if(Objects.equals(entryStreet.getText(), "")){
                            deliveryFlag = false;
                            entryStreet.setStyle("-fx-background-color: #18171a; -fx-border-color: red; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }
                        else {
                            entryStreet.setStyle("-fx-background-color: #18171a; -fx-border-color: #5e5e5e; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }
                        if(Objects.equals(entryHouse.getText(), "")){
                            deliveryFlag = false;
                            entryHouse.setStyle("-fx-background-color: #18171a; -fx-border-color: red; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }
                        else {
                            entryHouse.setStyle("-fx-background-color: #18171a; -fx-border-color: #5e5e5e; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }
                        if(Objects.equals(entryFlat.getText(), "")){
                            deliveryFlag = false;
                            entryFlat.setStyle("-fx-background-color: #18171a; -fx-border-color: red; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }
                        else {
                            entryFlat.setStyle("-fx-background-color: #18171a; -fx-border-color: #5e5e5e; -fx-text-fill: #c2c2c2; -fx-border-width: 2;" +
                                    "-fx-border-radius: 10; -fx-background-radius: 10;");
                        }

                        if (deliveryFlag) {
                            deliveryAdmin = deliveryWay;
                            btnDelivery.setStyle("-fx-background-color: #2594f7; -fx-background-radius: 10");
                            btnDelivery.setText("Закказано!");
                        }else {
                            btnDelivery.setStyle("-fx-background-color: red; -fx-background-radius: 10");
                            btnDelivery.setText("Не все поля заполнены");
                        }
                    }



                       /* try {
                            if (postOffices.getValue().toString() != null) {
                                deliveryAdmin = postOffices.getValue().toString() + " (отделение почты)";
                                btnDelivery.setText("Закказано!");
                            }
                        } catch (NullPointerException e) {
                            System.out.println();
                        }
                    } else {
                        deliveryAdmin = deliveryWay;
                       // deliveryAdmin = entryDelivery.getText() + " (курьер)";
                        btnDelivery.setText("Закказано!");
                    }

                        */

                    while (resultSet2.next()) {
                        nameUser = resultSet2.getString("name");
                        if (nameUser.equals(LogInController.userAccountName)) {
                            statement2.setString(1, name + " " + lbMonth.getText() + " " + lbDay.getText() + " " + lbTime.getText() + " " + deliveryAdmin);
                            statement2.setString(2, nameUser);
                            statement2.executeUpdate();

                        }
                    }
                    statement.setString(1, lbMonth.getText() + " " + lbDay.getText() + " " + lbTime.getText() + " " + deliveryAdmin);
                    statement.setString(2, LogInController.userAccountTel);
                    statement.setString(3, name2);
                    statement.executeUpdate();
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnPostOffice(MouseEvent mouseEvent) {
        if (!transparentFlagPost){
            btnPostOffice.setStyle("-fx-background-color: transparent; -fx-text-fill: #2594f7;-fx-border-color: #2594f7; -fx-border-width: 2; -fx-border-radius: 10");
            btnDeliveryman.setStyle("-fx-background-color: transparent; -fx-text-fill: #5e5e5e;-fx-border-color: #5e5e5e; -fx-border-width: 2; -fx-border-radius: 10");
            transparentFlagPost = true;
            lbDelivery.setText("Отделение почты");
            postOffices.setOpacity(1.0);
            entryCity.setOpacity(0.0);
            entryDistrict.setOpacity(0.0);
            entryStreet.setOpacity(0.0);
            entryHouse.setOpacity(0.0);
            entryFlat.setOpacity(0.0);
        }
    }

    public void btnDeliveryman(MouseEvent mouseEvent) {
        if (transparentFlagPost){
            btnDeliveryman.setStyle("-fx-background-color: transparent; -fx-text-fill: #2594f7; -fx-border-color: #2594f7; -fx-border-width: 2; -fx-border-radius: 10");
            btnPostOffice.setStyle("-fx-background-color: transparent; -fx-text-fill: #5e5e5e;-fx-border-color: #5e5e5e; -fx-border-width: 2; -fx-border-radius: 10");
            transparentFlagPost = false;
            lbDelivery.setText("Курьер");
            postOffices.setOpacity(0.0);
            entryCity.setOpacity(1.0);
            entryDistrict.setOpacity(1.0);
            entryStreet.setOpacity(1.0);
            entryHouse.setOpacity(1.0);
            entryFlat.setOpacity(1.0);
        }
    }
}
