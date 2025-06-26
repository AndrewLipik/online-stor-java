package org.example.slstoreprog;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private ComboBox comboBox;
    static AnchorPane home;
    @FXML
    private ImageView imageHome;
    @FXML
    private ImageView imageBasket;
    @FXML
    private ImageView imageUser;


    Image homeWhiteImage = null;
    Image homeBlueImage = null;
    Image basketWhiteImage = null;
    Image basketBlueImage = null;
    Image userWhiteImage = null;
    Image userBlueImage = null;


    ObservableList<String> comboBoxList = FXCollections.observableArrayList();
    static String comboBoxTex = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL urlWhiteHome = getClass().getResource("/org/example/slstoreprog/home_white.png");
        URL urlBlueHome = getClass().getResource("/org/example/slstoreprog/home_blue.png");
        URL urlWhiteBasket = getClass().getResource("/org/example/slstoreprog/icon_shopping_bag_white.png");
        URL urlBlueBasket = getClass().getResource("/org/example/slstoreprog/icon_shopping_bag_blue.png");
        URL urlWhiteUser = getClass().getResource("/org/example/slstoreprog/user_white.png");
        URL urlBlueUser = getClass().getResource("/org/example/slstoreprog/user_blue.png");
        String pathWhiteHome = null;
        String pathBlueHome = null;
        String pathWhiteBasket = null;
        String pathBlueBasket = null;
        String pathWhiteUser = null;
        String pathBlueUser = null;
        try {
            pathWhiteHome = Paths.get(urlWhiteHome.toURI()).toFile().getAbsolutePath();
            pathBlueHome = Paths.get(urlBlueHome.toURI()).toFile().getAbsolutePath();
            pathWhiteBasket = Paths.get(urlWhiteBasket.toURI()).toFile().getAbsolutePath();
            pathBlueBasket = Paths.get(urlBlueBasket.toURI()).toFile().getAbsolutePath();
            pathWhiteUser = Paths.get(urlWhiteUser.toURI()).toFile().getAbsolutePath();
            pathBlueUser = Paths.get(urlBlueUser.toURI()).toFile().getAbsolutePath();


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        homeWhiteImage = new Image("file:///"+pathWhiteHome);
        homeBlueImage = new Image("file:///"+pathBlueHome);
        basketWhiteImage = new Image("file:///"+pathWhiteBasket);
        basketBlueImage = new Image("file:///"+pathBlueBasket);
        userWhiteImage = new Image("file:///"+pathWhiteUser);
        userBlueImage = new Image("file:///"+pathBlueUser);

        ConText.getInstance().setHelloController(this);
        createPage();
        LogInController logInController = new LogInController();
        System.out.println( logInController.getTest());

        FilteredList<String> filteredItems = new FilteredList<String>(comboBoxList, t -> true);

        comboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = comboBox.getEditor();
            if (editor.getText() != null) {
                comboBox.show();
            }
            try {
                final String selected = (String) comboBox.getSelectionModel().getSelectedItem();
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
                comboBox.setItems(filteredItems);
            } catch (NullPointerException nullPointerException) {
                nullPointerException.printStackTrace();
            }
        });
        try {
            URL u1 = getClass().getResource("/org/example/slstoreprog/search.txt");
            String path = Paths.get(u1.toURI()).toFile().getAbsolutePath();
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                comboBoxList.add(line);
            }
            bufferedReader.close();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        comboBox.setItems(comboBoxList);

    }

    void createPage() {
        try {
            home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scrollPaneWindow.fxml")));
            setNode(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNode(Node node) {
        HelloController helloController = ConText.getInstance().getHelloController();
        AnchorPane holderPane = helloController.holderPane;

        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0));
        fadeTransition.setNode(node);
       fadeTransition.play();
    }
    public void btnAboutUser(MouseEvent mouseEvent) {
        imageHome.setImage(homeWhiteImage);
        imageBasket.setImage(basketWhiteImage);
        imageUser.setImage(userBlueImage);
        try {
            home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("aboutUser.fxml")));
            setNode(home);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnBasket(MouseEvent mouseEvent) {
        imageHome.setImage(homeWhiteImage);
        imageBasket.setImage(basketBlueImage);
        imageUser.setImage(userWhiteImage);
        if (!LogInController.userAccountName.equals("")) {
            try {
                home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scrollBasket.fxml")));
                setNode(home);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("basketError.fxml")));
                setNode(home);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnHome(MouseEvent mouseEvent) {
        imageHome.setImage(homeBlueImage);
        imageBasket.setImage(basketWhiteImage);
        imageUser.setImage(userWhiteImage);

        createPage();
    }

    public void btnSearchGadgets(MouseEvent mouseEvent) {
        try {
            comboBoxTex = comboBox.getValue().toString();
        }catch (NullPointerException e){
            System.out.println(" ");
        }
            try {
                home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SearchGadgetsWindow.fxml")));
                setNode(home);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void btnTelegram(MouseEvent mouseEvent) {
        String url="https://t.me/SLStoreTelegramBot";
        openWebPage(url);
    }
    public void btnWebBrowse(MouseEvent mouseEvent) throws IOException {
         String url="https://slstoreweb.tilda.ws/";
         openWebPage(url);
    }

    private void openWebPage(String url) {
        try {
            URI uri=new URI(url);
            Desktop.getDesktop().browse(uri);
        }catch (IOException | URISyntaxException e){
            e.printStackTrace();
        }
    }

    public void btnAboutSlStore(MouseEvent mouseEvent) throws IOException {
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("aboutSlStore.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        stage.setTitle("Sl Store");
        stage.setScene(scene);
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
        stage.getIcons().add(image);

        stage.show();

    }

    public void newSearchImage(MouseDragEvent mouseDragEvent) {
        System.out.println("image");
    }

}
