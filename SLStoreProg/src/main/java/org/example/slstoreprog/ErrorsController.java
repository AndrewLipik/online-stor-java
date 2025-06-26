package org.example.slstoreprog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorsController implements Initializable {
    @FXML
    private Label lbError;

    public void btnCloseError(MouseEvent mouseEvent) {
        lbError.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (AdminRootController.findErr.getMessage() != "") {
            lbError.setText(AdminRootController.findErr.getMessage());
        } else {
            lbError.setText(LogInController.err.getMessage());
        }
    }

}
