package org.example.slstoreprog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class BasketError {
    static LogIn logFromBasket;
    public void btnBasketErrorLogIn(MouseEvent mouseEvent) {
        logFromBasket = new LogIn();
        logFromBasket.LoginRootCall();
    }

}
