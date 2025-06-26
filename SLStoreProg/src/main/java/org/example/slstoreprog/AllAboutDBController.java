package org.example.slstoreprog;

import DBConnection.DBHandler;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AllAboutDBController implements Initializable {
    @FXML
    private ListView lvDB;
    @FXML
    private Button btnUpdatePrice;
    @FXML
    private Button btnDeleteGadget;

    @FXML
    private Label lbSelected2;
    @FXML
    private TextField entryEditPrice;
    @FXML
    private Label lbEditPrice;
    private Connection connection;
    private PreparedStatement preparedStatement;
    ObservableList<String> data;
    private String name2;
    private String priceUpdate;
    private int countDelete;
    private String rowsDelete;
    private String rowsUpdate;
    private String sql;
    private int clickDel;
    private int click;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBHandler dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();
        sql = "SELECT * from base2_tg";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSetGadgets=preparedStatement.executeQuery();
            data = FXCollections.observableArrayList();
            while (resultSetGadgets.next()) {
                name2 = resultSetGadgets.getString("name");
                data.add(name2);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        lvDB.setItems(data);
    }

    public void btnDeleteGadget(MouseEvent mouseEvent) throws SQLException {
        Duration duration=Duration.millis(90);
        TranslateTransition translateTransition = new TranslateTransition(duration,btnDeleteGadget);
        translateTransition.setByY(2);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.play();
        clickDel++;
        String selectedTex = (String) lvDB.getSelectionModel().getSelectedItem();
        if(selectedTex!=null){
            lbSelected2.setText(selectedTex);
            if(clickDel==1) {
                ResultSet resultSetUpdate = preparedStatement.executeQuery();
                while (resultSetUpdate.next()) {
                    name2 = resultSetUpdate.getString("name");
                    countDelete = resultSetUpdate.getInt("count");
                    System.out.println("name2 " + name2 + " selected " + selectedTex);
                    if (Objects.equals(selectedTex, name2)) {
                        lbEditPrice.setText("Текущее количество: ");
                        entryEditPrice.setText(String.valueOf(countDelete));
                        break;
                    }
                }
            }
            if (clickDel==2) {
                if (countDelete == 1) {
                    lbSelected2.setText("Удалено: " + selectedTex);
                    entryEditPrice.setText("0");
                    String del = "DELETE FROM base2_tg WHERE name=\"" + name2 + "\"";
                    preparedStatement = connection.prepareStatement(del);
                    preparedStatement.executeUpdate();
                } else {
                    rowsDelete = "UPDATE base2_tg SET count=? WHERE name=?";
                    PreparedStatement statement = connection.prepareStatement(rowsDelete);
                    statement.setString(1, entryEditPrice.getText());
                    statement.setString(2, selectedTex);
                    statement.executeUpdate();
                }
            }
        }

    }

    public void btnCloseAboutDB(MouseEvent mouseEvent) {
        lvDB.getScene().getWindow().hide();
    }

    public void btnUpdatePrice(MouseEvent mouseEvent) throws SQLException {
        Duration duration=Duration.millis(90);
        TranslateTransition translateTransition = new TranslateTransition(duration,btnUpdatePrice);
        //translateTransition.setByX(2);
        translateTransition.setByY(2);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);
        translateTransition.play();
        click++;
        String selectedTex = (String) lvDB.getSelectionModel().getSelectedItem();
        if (selectedTex != null) {
            lbSelected2.setText(selectedTex);
            if (click == 1) {
                System.out.println("click1 " + click);
                ResultSet resultSetUpdate = preparedStatement.executeQuery();
                while (resultSetUpdate.next()) {
                    name2 = resultSetUpdate.getString("name");
                    priceUpdate = resultSetUpdate.getString("price");
                    System.out.println("name2 " + name2 + " selected " + selectedTex);
                    if (Objects.equals(selectedTex, name2)) {
                        System.out.println("price" + priceUpdate);
                        lbEditPrice.setText("Текущая цена: ");
                        entryEditPrice.setText(priceUpdate);
                        break;
                    }
                }
            }
            if (click == 2) {
                System.out.println("click2 " + click);
                lbEditPrice.setText("Изменённая цена: ");
                rowsUpdate = "UPDATE base2_tg SET price=? WHERE name=?";
                PreparedStatement statement = connection.prepareStatement(rowsUpdate);
                statement.setString(1, entryEditPrice.getText());
                statement.setString(2, selectedTex);
                statement.executeUpdate();
                }
        }
    }
}
