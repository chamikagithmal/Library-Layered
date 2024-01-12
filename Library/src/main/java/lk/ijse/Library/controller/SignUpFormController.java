package lk.ijse.Library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.dto.SignUpDto;
import lk.ijse.Library.bo.impl.SignUpBOImpl;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void SignInOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"login_form.fxml","salary");
    }


    @FXML
    void SignUpOnAction(ActionEvent event) {

        String name = txtName.getText();
        String email = txtEmail.getText();
        String user_name = txtUserName.getText();
        String password = txtPassword.getText();


        var dto = new SignUpDto(name, email, user_name, password);

        var model = new SignUpBOImpl();

        try {
            boolean isSaved = model.saveSingUp(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    void clearFields() {
        txtName.setText("");
        txtEmail.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
    }


}