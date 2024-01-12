package lk.ijse.Library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.bo.BOFactory;
import lk.ijse.Library.bo.custom.SignUpBO;
import lk.ijse.Library.bo.impl.SignUpBOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane Pane;
    SignUpBO signUpBO = (SignUpBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SIGNUP);


    @FXML
    void CreateNewOneOnAction(ActionEvent event) throws IOException {
        navigate.navigate(Pane,"signup_form.fxml","signup");
    }

    @FXML
    void LoginOnAction(ActionEvent event) throws IOException {
        try {
            if (signUpBO.checkUser(txtPassword.getText(),txtUserName.getText())) {
                navigate.navigate(Pane, "dashbord_form.fxml", "dashbord");
            }else {
                new Alert(Alert.AlertType.ERROR,"error").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
