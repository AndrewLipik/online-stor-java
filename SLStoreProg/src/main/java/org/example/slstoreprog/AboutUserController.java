package org.example.slstoreprog;

import DBConnection.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AboutUserController implements Initializable {
    @FXML
    private Label lbUser;
    @FXML
    private Label userTelNumber;
    @FXML
    private Label GadgetsInBasket;
    @FXML
    private ImageView imageUser;
    static LogIn log;
    static String UserPath;

    private String pathInFile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String convertedPath;
        lbUser.setText(LogInController.userAccountName);
        userTelNumber.setText(" Номер телефона: " + LogInController.userAccountTel);
        GadgetsInBasket.setText(" " + LogInController.userDelivery);

        File file = new File("imagePath.txt");
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    pathInFile = scanner.nextLine();
                }
                convertedPath = pathInFile.replace("\\","/");
                Image imageIn = new Image("file:///"+convertedPath);
                imageUser.setImage(imageIn);

            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("ошибка чтения файла");
            }
        }
    }

    public void btnLogIn(MouseEvent mouseEvent) {
        log=new LogIn();
        log.LoginRootCall();
        File file=new File("remember.txt");
        if(file.exists()){
            try {
                Scanner scanner=new Scanner(file);
                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                }
                scanner.close();
            }catch (FileNotFoundException fileNotFoundException){
                System.out.println("ошибка чтения файла");
            }
        }
        else {
            System.out.println("файла не существует");
        }


    }

    public void btnImage(MouseEvent mouseEvent) {
        File file1=new File("imagePath.txt");
        try {
            if(file1.createNewFile()){
                System.out.println("файл создан");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileChooser fileChooser = new FileChooser();
        Window window = lbUser.getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        UserPath = file.getAbsolutePath();
        System.out.println("userPath "+UserPath);
        System.out.println("открытие проводника");
        File fileImage = new File("imagePath.txt");
        if (fileImage.exists()) {
            try {
                FileWriter fileWriter = new FileWriter("imagePath.txt");
                fileWriter.write("\n"+UserPath);
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
