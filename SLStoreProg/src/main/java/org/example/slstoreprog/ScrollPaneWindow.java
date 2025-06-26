package org.example.slstoreprog;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScrollPaneWindow  implements Initializable {
    private boolean isPlayed;
    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnStartMedia;
    @FXML
    private ImageView mediaImage;
    @FXML
    private ImageView imageChoiceColor;
    @FXML
    private ImageView mainImage;
    @FXML
    private Label lbAboutUs;


    private MediaPlayer mediaPlayer;
    URL urlPause;
    String pathPauseImage = null;
    Image pauseImage = null;
    int mainImageId = 0;
    ArrayList<String> mainImagesList = new ArrayList<>();
    ArrayList<String> mainTextsList = new ArrayList<>();

    URL urlPlay;
    String pathPlayImage = null;
    String pathMainImage = null;
    Image playImage = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainImagesList.add("firstWallpaper.jpeg");
        mainImagesList.add("secondWallpaper.jpg");
        mainImagesList.add("thirdWallpaper.jpeg");
        mainImagesList.add("fourthWallpaper.jpeg");

        mainTextsList.add("Самый иновационный iPhone у нас — почувствуй будущее уже сегодня! Приходи за новейшими моделями," +
                " протестируй технологии, которые меняют мир. Настоящая инновация ждёт тебя здесь!");
        mainTextsList.add("Здесь начинается путь к совершенству с iPhone! Премиум-технологии и экспертная консультация." +
                " Приходи за качеством, которым приятно пользоваться.");
        mainTextsList.add("iPhone — не просто телефон, а стиль жизни! Погрузись в мир, где технологии становятся частью твоей индивидуальности. " +
                "Купи у нас с выгодой и радостью.");
        mainTextsList.add("Здесь ваши мечты превращаются в реальность! Самые популярные модели iPhone готовы к твоим рукам. " +
                "Надёжность, профессионализм и качественный сервис — всё это у нас.");
    }
    public void btnDetails(MouseEvent mouseEvent) {
    }

    public void btnStartMedia(MouseEvent mouseEvent) {
        if (mediaPlayer == null){
            isPlayed = true;
            String path = "file:/D:/Java/Projects/VideoDemo/src/main/resources/org/example/videodemo/Introducing16.mp4";
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            mediaPlayer.setOnReady(mediaPlayer::play);
            btnStartMedia.setText("Play");
            urlPause = getClass().getResource("/org/example/slstoreprog/pause.png");
            String absolutePausePath;
            try {
                absolutePausePath = Paths.get(urlPause.toURI()).toFile().getAbsolutePath();
                pathPauseImage  = "file:///"+absolutePausePath;

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            assert pathPauseImage != null;
            pauseImage = new Image(pathPauseImage);

            urlPlay = getClass().getResource("/org/example/slstoreprog/play.png");
            String absolutePlayPath;
            try {
                absolutePlayPath = Paths.get(urlPlay.toURI()).toFile().getAbsolutePath();
                pathPlayImage  = "file:///"+absolutePlayPath;

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            assert pathPlayImage != null;
            playImage = new Image(pathPlayImage);
            // fadeOutImage(mediaImage);
            mediaImage.setImage(playImage);
            fadeOutImage(mediaImage);

        }
        else {
            if (isPlayed) {
                fadeInImage(mediaImage);
                btnStartMedia.setText("Pause");
                mediaPlayer.pause();
                isPlayed = false;
                mediaImage.setImage(pauseImage);


            } else  {
                btnStartMedia.setText("Play");
                mediaPlayer.play();
                isPlayed = true;
                fadeOutImage(mediaImage);
                mediaImage.setImage(playImage);

            }
        }
    }

    private void fadeOutImage(ImageView imageView){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), imageView);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();
    }
    private void fadeInImage(ImageView imageView){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), imageView);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    private void fadeColorImage(ImageView imageView){
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.2), imageView);
        fadeTransition.setFromValue(0.8);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    public void btnWhiteImage(MouseEvent mouseEvent) {
        URL urlWhiteIphone = getClass().getResource("/org/example/slstoreprog/iphone16_pro_white.png");
        String absoluteColorIphone;
        try {
            absoluteColorIphone = Paths.get(urlWhiteIphone.toURI()).toFile().getAbsolutePath();
            pathPlayImage  = "file:///"+absoluteColorIphone;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert pathPlayImage != null;
        Image iphoneImage = new Image(pathPlayImage);
        imageChoiceColor.setImage(iphoneImage);
        fadeColorImage(imageChoiceColor);
    }

    public void btnGreyImage(MouseEvent mouseEvent) {
        URL urlGreyIphone = getClass().getResource("/org/example/slstoreprog/iphone16_pro_natural.png");
        String absoluteColorIphone;
        try {
            absoluteColorIphone = Paths.get(urlGreyIphone.toURI()).toFile().getAbsolutePath();
            pathPlayImage  = "file:///"+absoluteColorIphone;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert pathPlayImage != null;
        Image iphoneImage = new Image(pathPlayImage);
        imageChoiceColor.setImage(iphoneImage);
        fadeColorImage(imageChoiceColor);
    }

    public void btnBlackImage(MouseEvent mouseEvent) {
        URL urlBlackIphone = getClass().getResource("/org/example/slstoreprog/iphone16_pro_black.png");
        String absoluteColorIphone;
        try {
            absoluteColorIphone = Paths.get(urlBlackIphone.toURI()).toFile().getAbsolutePath();
            pathPlayImage  = "file:///"+absoluteColorIphone;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert pathPlayImage != null;
        Image iphoneImage = new Image(pathPlayImage);
        imageChoiceColor.setImage(iphoneImage);
        fadeColorImage(imageChoiceColor);
    }

    public void btnTitaniumImage(MouseEvent mouseEvent) {
        URL urlTitaniumIphone = getClass().getResource("/org/example/slstoreprog/iphone16_pro_titanium.png");
        String absoluteColorIphone;
        try {
            absoluteColorIphone = Paths.get(urlTitaniumIphone.toURI()).toFile().getAbsolutePath();
            pathPlayImage  = "file:///"+absoluteColorIphone;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert pathPlayImage != null;
        Image iphoneImage = new Image(pathPlayImage);
        imageChoiceColor.setImage(iphoneImage);
        fadeColorImage(imageChoiceColor);
    }

    public void btnNext(MouseEvent mouseEvent) {
        if (mainImageId<mainImagesList.size()-1) {
            mainImageId++;
            String image = mainImagesList.get(mainImageId);
            setMainImage(image);
        }

    }
    public void btnBack(MouseEvent mouseEvent) {
        if (mainImageId>0){
            mainImageId--;
            String image = mainImagesList.get(mainImageId);
            setMainImage(image);
        }
    }
    public void setMainImage(String image){
        URL urlImage = getClass().getResource("/org/example/slstoreprog/"+image);
        String absoluteImage;
        try {
            absoluteImage = Paths.get(urlImage.toURI()).toFile().getAbsolutePath();
            pathMainImage = "file:///"+absoluteImage;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert pathMainImage != null;
        Image mainImg = new Image(pathMainImage);
        mainImage.setImage(mainImg);
        lbAboutUs.setText(mainTextsList.get(mainImageId));
    }

}
