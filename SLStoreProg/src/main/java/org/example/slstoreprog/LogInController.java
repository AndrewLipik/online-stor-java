package org.example.slstoreprog;

import DBConnection.DBHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LogInController implements Initializable {
    @FXML
    private TextField logEntry1;
    @FXML
    private TextField logEntry2;
    @FXML
    private TextField signUpEntry1;
    @FXML
    private TextField signUpEntry2;
    @FXML
    private TextField signUpEntry3;
    @FXML
    private TextField signUpEntry4;
    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnRemember;
    @FXML
    private Button btnLoginRemember;
    static AdminRoot adminRoot;
    static Errors err=new Errors("");
    static String email;
    static String password;
    static String userAccountName="";
    static String userAccountTel="";
    static String userAccountImage="";
    static String userAccountBasket="";
    static String userDelivery=" доставка ожидается:";

    private String test = "test good";

    public String getTest(){
        return test;
    }

    private DBHandler dbHandler;
    private Connection connection;
    private PreparedStatement preparedStatement;
    String loginInsert;
    private int userLogin;
    int count;
    int loginCount;
    int user=1;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbHandler=new DBHandler();
    }

    public void btnLogIn(MouseEvent mouseEvent) {
        connection = dbHandler.getDbConnection();
        String sql = "SELECT * from programUsers";
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userLogin=0;
                email = resultSet.getString("email");
                System.out.println("email:" + email);
                password = resultSet.getString("password");
                System.out.println("Пароли:" + password);
                userAccountName=resultSet.getString("name");
                userAccountTel=resultSet.getString("telNumber");
                userAccountBasket=resultSet.getString("basket");
                userDelivery=resultSet.getString("delivery");

                if (logEntry1.getText().equals(email)) {
                    userLogin++;
                    if (logEntry2.getText().equals(password)) {
                        btnLogIn.setText("Добро пожаловать!");
                        loginCount++;
                        user++;
                        System.out.println(user);
                        System.out.println("mouse Event");
                        break;
                    }
                }
            }
            if(userLogin==0 & !logEntry1.getText().equals("8371")) {
                err = new Errors("Данного пользователя не существует");
                err.ErrCall();
                userAccountTel = "";
                userAccountName = "";
                userAccountBasket = "";
                userDelivery = "";

                if (loginCount == 0) {
                    err = new Errors("Неправильный пароль");
                    err.ErrCall();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(logEntry1.getText().equals("8371")){
            if(logEntry2.getText().equals("aLv")){
                adminRoot=new AdminRoot();
                adminRoot.OpenAdminRoot();
                System.out.println("admin 2");
                btnCloseLogRoot(mouseEvent);
            }
        }

    }

    public void btnSignUp(MouseEvent mouseEvent) {
        if (signUpEntry1.getText().isEmpty()) {
            err = new Errors("Введите ваше имя");
            err.ErrCall();
        } else {
            if (signUpEntry2.getText().isEmpty()) {
                err = new Errors("Введите выш email");
                err.ErrCall();
            } else {
                if (signUpEntry3.getText().isEmpty()) {
                    err = new Errors("Введите ваш номер");
                    err.ErrCall();
                } else {
                    if (signUpEntry4.getText().isEmpty()) {
                        err = new Errors("Введите ваш пароль");
                        err.ErrCall();
                    } else {
                        Register();
                    }
                }
            }
        }
    }
    public void Register(){
        btnSignUp.setText("Добро пожаловать!");
        user++;
        loginInsert = "INSERT INTO programUsers(name,email,telNumber,password,basket,delivery) Values(?,?,?,?,?,?)";
        connection = dbHandler.getDbConnection();
        String sql = "SELECT * from programUsers";

        try {
            preparedStatement = connection.prepareStatement(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        userAccountName=signUpEntry1.getText();
        userAccountTel=signUpEntry3.getText();
        userAccountImage="";
        userAccountBasket="";


        try {
            preparedStatement = connection.prepareStatement(loginInsert);
            preparedStatement.setString(1, signUpEntry1.getText());
            preparedStatement.setString(2, signUpEntry2.getText());
            preparedStatement.setString(3, signUpEntry3.getText());
            preparedStatement.setString(4, signUpEntry4.getText());
            preparedStatement.setString(5,"");
            preparedStatement.setString(6,"");

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnCloseLogRoot(MouseEvent mouseEvent) {
        logEntry1.getScene().getWindow().hide();
    }

    public void logEntry1(MouseEvent mouseEvent) {
        File file=new File("remember.txt");
        if(file.exists()){
            try {
                Scanner scanner=new Scanner(file);
                while (scanner.hasNextLine()){
                    count++;
                    String line=scanner.nextLine();
                    if(count==1) {
                        logEntry1.setText(line);
                        System.out.println(line);
                    }
                    if(count==2) {
                        logEntry2.setText(line);
                    }
                }
                scanner.close();
            }catch (FileNotFoundException fileNotFoundException){
                System.out.println("ошибка чтения файла");
            }
        }
    }

    public void btnForgot(MouseEvent mouseEvent) {
    }

    public void btnRemember(MouseEvent mouseEvent) {
        File file = new File("remember.txt");
        if (file.exists()) {
            try {
                btnRemember.setText("Сохранено");
                FileWriter fileWriter = new FileWriter("remember.txt");
                fileWriter.write(STR."\{signUpEntry2.getText()}\n\{signUpEntry4.getText()}");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("ошибка записи в файл");
                e.printStackTrace();
            }
        }
    }

    public void btnLoginRemember(MouseEvent mouseEvent) {
        try {
        File file = new File("remember.txt");
        if (file.exists()) {
            btnLoginRemember.setText("Сохранено");
            FileWriter fileWriter = new FileWriter("remember.txt");
            fileWriter.write(logEntry1.getText() + "\n" + logEntry2.getText());
            fileWriter.close();

        }} catch (IOException e) {
                System.out.println("ошибка записи в файл");
                e.printStackTrace();
            }
    }
}
