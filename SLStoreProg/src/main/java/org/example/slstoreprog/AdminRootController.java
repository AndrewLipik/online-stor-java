package org.example.slstoreprog;

import DBConnection.DBHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdminRootController implements Initializable {
    @FXML
    private TextField entry1;
    @FXML
    private TextField entry2;
    @FXML
    private TextField entry3;
    @FXML
    private TextField entry4;
    @FXML
    private RadioButton rb;
    @FXML
    private ComboBox adminCombo;
    @FXML
    private Label lbCount;
    @FXML
    private Label lbPathToImage;
  //  String pathPictureGadget;
    static Errors findErr = new Errors("");
    String insert;
    boolean flag;
    boolean error=false;
    private String pr;
    private int count = 1;
    ObservableList<String> data;
    String allPath = "";
   
    static String adminComboBoxTex = "";
    ObservableList<String> comboBoxList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FilteredList<String> filteredItems = new FilteredList<String>(comboBoxList, t -> true);

        adminCombo.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = adminCombo.getEditor();
            if (editor.getText() != null) {
                adminCombo.show();
                adminComboBoxTex = editor.getText();
            }
            try {
                final String selected = (String) adminCombo.getSelectionModel().getSelectedItem();
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
                adminCombo.setItems(filteredItems);
            } catch (NullPointerException nullPointerException) {
                System.out.println(nullPointerException);
            }
        });
        try {
            URL u1 = getClass().getResource("/org/example/slstoreprog/colors.txt");
            String path = Paths.get(u1.toURI()).toFile().getAbsolutePath();
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                comboBoxList.add(line);
            }
            bufferedReader.close();
        } catch (IOException | URISyntaxException exceptions) {
            throw new RuntimeException(exceptions);
        }
        adminCombo.setItems(comboBoxList);
    }


    public void btnPictureGadget(MouseEvent mouseEvent) throws IOException {
        flag = false;
        ArrayList<String> arrayPathToGadget = new ArrayList<>();
        FileChooser fileChooser = new FileChooser();
        Window window = entry1.getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        arrayPathToGadget.add(file.getAbsolutePath());
        for(String i: arrayPathToGadget){
            allPath = allPath + i +";";
        }
       // pathPictureGadget = file.getAbsolutePath();
       // lbPathToImage.setText(STR."Выбранный путь: \{pathPictureGadget}");
        lbPathToImage.setText(STR."Выбранный путь \{allPath}");
    }

    public void btnSave(MouseEvent mouseEvent) throws SQLException {
        error=false;
        if (entry1.getText().isEmpty()) {
            findErr = new Errors("Введите модель устройства");
            findErr.ErrCall();
        } else {
            if (entry2.getText().isEmpty()) {
                findErr = new Errors("Введите цену");
                findErr.ErrCall();
            } else {
                if (entry3.getText().isEmpty()) {
                    findErr = new Errors("Введите номер продовца");
                    findErr.ErrCall();
                } else {
                    if (entry4.getText().isEmpty()) {
                        findErr = new Errors("Введите описание");
                        findErr.ErrCall();
                    } else {
                        if (adminCombo.getValue().toString().isEmpty()){
                            findErr = new Errors("Введите цвет");
                            findErr.ErrCall();
                        }else {
                            Reg1();
                        }
                    }
                }
            }
        }
    }

    public void Reg1() throws SQLException {
        DBHandler dbHandler = new DBHandler();
        insert = "INSERT INTO base2_tg(name,price,sellersNumber,aboutAll,path,color,whoBuy,address,count) Values(?,?,?,?,?,?,?,?,?)";
        Connection connection = dbHandler.getDbConnection();
        String sqlError = "SELECT * from base2_tg";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlError);
        ResultSet resultSetError = preparedStatement.executeQuery();
        while (resultSetError.next()) {
            String name = resultSetError.getString("name");
            System.out.println(name);
            if (entry1.getText().equals(name)) {
                findErr = new Errors("Данное устройство уже существует");
                findErr.ErrCall();
                error = true;
                break;
            }
        }
        data= FXCollections.observableArrayList();
        while (resultSetError.next()){
            String nameGadget = resultSetError.getString("name");
            data.add(nameGadget);
        }
        if (!error) {
            try {
                preparedStatement = connection.prepareStatement(insert);
                preparedStatement.setString(1, entry1.getText());
                preparedStatement.setString(2, entry2.getText());
                preparedStatement.setString(3, entry3.getText());
                preparedStatement.setString(4, entry4.getText());
                if (rb.isSelected()) {
                    pr = STR."new \{entry1.getText()}";
                } else {
                    pr = allPath;
                }
                preparedStatement.setString(5, pr);
                preparedStatement.setString(6, adminCombo.getValue().toString());
                preparedStatement.setString(7,"negative");
                preparedStatement.setString(8,"negative");
                preparedStatement.setInt(9,count);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void btnUsers(MouseEvent mouseEvent) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("allAboutDB.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 500);
        stage.setTitle("Sl Store");
        stage.setScene(scene);
        URL u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240127_173826.png");
        String path = null;
        try {
            assert u1 != null;
            path = STR."file:///\{Paths.get(u1.toURI()).toFile().getAbsolutePath()}";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert path != null;
        Image image = new Image(path);
        stage.getIcons().add(image);


        stage.show();
    }

    public void btnCloseAdminRoot(MouseEvent mouseEvent) {
        entry1.getScene().getWindow().hide();
    }

    public void btnLeftArrow(MouseEvent mouseEvent) {
        count--;
        lbCount.setText(String.valueOf(count));
    }

    public void btnRightArrow(MouseEvent mouseEvent) {
        count++;
        lbCount.setText(String.valueOf(count));
    }

}

