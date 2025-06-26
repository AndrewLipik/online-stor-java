package org.example.slstoreprog;

import DBConnection.DBHandler;
import basket.Basket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BasketController implements Initializable {
    @FXML
    private ListView lvBasket;
    private String basket2;
    static String path;
    private ObservableList<Object> basket = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBHandler dbHandler = new DBHandler();
        lvBasket.setCellFactory(new Callback<ListView<Basket>, BasketWindow>() {
            @Override
            public BasketWindow call(ListView<Basket> basketListView) {
                return BasketWindow.newInstance();
            }

        });
        Connection connection = dbHandler.getDbConnection();
        String sqlUsers = "SELECT * from programUsers";
        try {
            PreparedStatement preparedStatementUsers = connection.prepareStatement(sqlUsers);
            ResultSet resultSetUsers = preparedStatementUsers.executeQuery();
                String sql = "SELECT * from base2_tg";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet2 = preparedStatement.executeQuery();
                while (resultSetUsers.next()) {
                    String name = resultSetUsers.getString("name");
                    System.out.println(name);
                    basket2 = resultSetUsers.getString("basket");
                    if (LogInController.userAccountName.equals(name)) {
                        System.out.println("Имя " + name);
                        System.out.println(basket2);
                        break;
                    }
                }
                    String[] words = basket2.split(";");
                   // System.out.println("wo "+words[4]);
                  //  for(String w : words){
                     //   System.out.println("w "+w);
                  //  }
                 //   System.out.println(words[0]);
                    while (resultSet2.next()) {
                        for (String splitGadget : words) {
                            String model = resultSet2.getString("name");
                            System.out.println("Модель:" + model);
                            if (model.equals(splitGadget)) {
                                Basket basket1 = new Basket();
                                basket1.setButtonText("Открыть");
                                model = resultSet2.getString("name");
                                String pathImage = resultSet2.getString("path");
                                String[] paths = pathImage.split(";");
                                String price = resultSet2.getString("price");
                                String color = resultSet2.getString("color");
                                String about = resultSet2.getString("aboutAll");
                                basket1.setLbDescription(model);
                                basket1.setLbDescriptionPrice(price);
                                basket1.setLbDescriptionAbout(about);
                                basket1.setLbDescriptionColor(color);
                                if (model.equals("iphone 15 pro max")) {
                                    System.out.println("ураа 15 pro max");
                                    URL u2 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_132648.png");
                                    try {
                                        path = "file:///" + Paths.get(u2.toURI()).toFile().getAbsolutePath();
                                        System.out.println("path 1 " + path);
                                    } catch (URISyntaxException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    if (model.equals("iphone 15 pro")) {
                                        System.out.println("ураа 15 pro");
                                        URL u2 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_134029.png");
                                        try {
                                            path = "file:///" + Paths.get(u2.toURI()).toFile().getAbsolutePath();
                                            System.out.println("path 2 " + path);
                                        } catch (URISyntaxException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        if (model.equals("iphone 15")) {
                                            System.out.println("ураа 15");
                                            URL u2 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_133917.png");
                                            try {
                                                path = "file:///" + Paths.get(u2.toURI()).toFile().getAbsolutePath();
                                                System.out.println("path 3 " + path);

                                            } catch (URISyntaxException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            if (model.equals("iphone 15 plus")) {
                                                System.out.println("ураа 15 plus");
                                                URL u2 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_134127.png");
                                                try {
                                                    path = "file:///" + Paths.get(u2.toURI()).toFile().getAbsolutePath();
                                                    System.out.println("path 4 " + path);
                                                } catch (URISyntaxException e) {
                                                    e.printStackTrace();
                                                }
                                            } else {
                                                //абсолютный путь
                                                System.out.println("path last " + pathImage);
                                                 path = pathImage;

                                            }
                                        }
                                    }
                                }
                                basket1.setImagePath(paths);
                                basket1.setListImagesPaths(paths);
                                basket.addAll(basket1);
                                lvBasket.setItems(basket);
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

    }
}