package org.example.slstoreprog;

import DBConnection.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import searchGadgets.SearchGadgets;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchGadgetsController implements Initializable {
    @FXML
    private ListView lvSearchGadgets;
    private ObservableList<Object> lv = FXCollections.observableArrayList();
    private int count;
    private String path15;
    URL u1;
    private Boolean checkSetPath = false;
    ArrayList<String> finalPath = new ArrayList<>();
    private String firstElementPath = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBHandler dbHandler = new DBHandler();
        lvSearchGadgets.setCellFactory(new Callback<ListView<SearchGadgets>, SearchGadgetsElement>() {
            @Override
            public SearchGadgetsElement call(ListView<SearchGadgets> param) {
                return SearchGadgetsElement.newInstance();
            }
        });

        Connection connection = dbHandler.getDbConnection();
        String sql = "SELECT * from base2_tg";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String path3 = resultSet.getString("path");
                String price = resultSet.getString("price");
                String color = resultSet.getString("color");
                String aboutAll = resultSet.getString("aboutAll");
                String sellersNumber = resultSet.getString("sellersNumber");
                String countDb = resultSet.getString("count");

                if (HelloController.comboBoxTex.equals(name)) {
                    count++;
                    SearchGadgets searchGadgets_1 = new SearchGadgets();
                    searchGadgets_1.setButtonText("Открыть");
                    searchGadgets_1.setLbDescription(name);
                    searchGadgets_1.setLbDescriptionPrice(price);
                    searchGadgets_1.setLbDescriptionAbout(aboutAll);
                    searchGadgets_1.setLbDescriptionColor(color);
                    searchGadgets_1.setLbDescriptionCount(countDb);
                    searchGadgets_1.setLbDescriptionSellersNumber(sellersNumber);
                    lv.addAll(searchGadgets_1);
                    lvSearchGadgets.setItems(lv);
                    if (name.equals("iphone 15 pro max")) {
                        u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_132648.png");
                        try {
                            assert u1 != null;
                            String path = STR."file:///\{Paths.get(u1.toURI()).toFile().getAbsolutePath()}";
                           // searchGadgets_1.setImagePath(path);

                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        break;
                    } else {
                        if (name.equals("iphone 15 pro")) {
                            u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_134029.png");
                            try {
                                assert u1 != null;
                                String path = STR."file:///\{Paths.get(u1.toURI()).toFile().getAbsolutePath()}";
                              //  searchGadgets_1.setImagePath(path);

                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                            break;
                        } else {
                            if (name.equals("iphone 15")) {
                                u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_133917.png");
                                try {
                                    assert u1 != null;
                                    String path = STR."file:///\{Paths.get(u1.toURI()).toFile().getAbsolutePath()}";
                                   // searchGadgets_1.setImagePath(path);

                                } catch (URISyntaxException e) {
                                    e.printStackTrace();
                                }
                                break;
                            } else {
                                if (name.equals("iphone 15 plus")) {
                                    u1 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_134127.png");
                                    try {
                                        assert u1 != null;
                                        String path = Paths.get(u1.toURI()).toFile().getAbsolutePath();
                                        //searchGadgets_1.setImagePath(path);
                                    } catch (URISyntaxException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                } else {
                                    /*String path1 = STR."file:///\{path3}";
                                    //абсолютный путь
                                    searchGadgets_1.setImagePath(path1);

                                     */

                                    String[] paths0 = path3.split(";");

                                    //  path15 = paths[0];
                                    searchGadgets_1.setImagePath(paths0);
                                    searchGadgets_1.setListImagesPaths(paths0);
                                    checkSetPath = true;
                                }
                            }
                        }
                    }
                }
            }
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            while (resultSet2.next()) {
                SearchGadgets searchGadgets_2 = new SearchGadgets();
                searchGadgets_2.setButtonText("Открыть");
                String name2 = resultSet2.getString("name");
                String path2 = resultSet2.getString("path");
                String price2 = resultSet2.getString("price");
                String color2 = resultSet2.getString("color");
                String abutAll2 = resultSet2.getString("aboutAll");
                String sellersNumber2 = resultSet2.getString("sellersNumber");
                String countDb2 = resultSet2.getString("count");

                searchGadgets_2.setLbDescription(name2);
                searchGadgets_2.setLbDescriptionPrice(price2);
                searchGadgets_2.setLbDescriptionAbout(abutAll2);
                searchGadgets_2.setLbDescriptionColor(color2);
                searchGadgets_2.setLbDescriptionCount(countDb2);
                searchGadgets_2.setLbDescriptionSellersNumber(sellersNumber2);
                if (name2.equals("iphone 15 pro max")) {
                     URL u2 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_132648.png");
                    try {
                        path15 = STR."file:///\{Paths.get(u2.toURI()).toFile().getAbsolutePath()}";

                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (name2.equals("iphone 15 pro")) {
                       URL u2 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_134029.png");
                        try {
                            assert u2 != null;
                            path15 = STR."file:///\{Paths.get(u2.toURI()).toFile().getAbsolutePath()}";

                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (name2.equals("iphone 15")) {
                           URL u2 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_133917.png");
                            try {
                                assert u2 != null;
                                path15 = STR."file:///\{Paths.get(u2.toURI()).toFile().getAbsolutePath()}";

                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (name2.equals("iphone 15 plus")) {
                              URL  u2 = getClass().getResource("/org/example/slstoreprog/IMG_20240204_134127.png");
                                try {
                                    assert u2 != null;
                                    path15 = STR."file:///\{Paths.get(u2.toURI()).toFile().getAbsolutePath()}";

                                } catch (URISyntaxException e) {
                                    e.printStackTrace();
                                }
                            } else {

                                String[] paths = path2.split(";");

                              //  path15 = paths[0];
                                if (!checkSetPath) {
                                    searchGadgets_2.setImagePath(paths);
                                    searchGadgets_2.setListImagesPaths(paths);
                                }
                               // finalPath.add(path15);

                                //абсолютный путь
                                 //   path15 = "file:///"+ path2;
                            }
                        }
                    }
               }
                lv.addAll(searchGadgets_2);
                lvSearchGadgets.setItems(lv);


            }

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
