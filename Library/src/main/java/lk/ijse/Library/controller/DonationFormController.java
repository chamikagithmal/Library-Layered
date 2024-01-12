package lk.ijse.Library.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.bo.BOFactory;
import lk.ijse.Library.bo.custom.DonationBO;
import lk.ijse.Library.dto.DonationDto;
import lk.ijse.Library.bo.impl.DonationBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonationFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colMonetaryAmount;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> cold_id;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtConNum;

    @FXML
    private TextField txtMonetaryAmount;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtd_id;

    @FXML
    public javafx.scene.control.TableView tblDonation;

    DonationBO donationBO = (DonationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DONATION);

    @FXML
    void ADDOnAction(ActionEvent event) {

        boolean isCustomerValid = validateDonation();
        if (isCustomerValid) {
        String d_id = txtd_id.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtConNum.getText());
        int monetary_amount= Integer.parseInt(txtMonetaryAmount.getText());



        var dto = new DonationDto(d_id, name, address,tel ,monetary_amount);

        var model = new DonationBOImpl();

        try {
            boolean isSaved = model.saveDonation(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Donation saved!").show();
                clearFields();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Details", ButtonType.OK).show();
        }

    }

    private boolean validateDonation() {

        String customerIdText = txtd_id.getText();
        Pattern compile = Pattern.compile("[D][0-9]{3,}");
        Matcher matcher = compile.matcher(customerIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID").show();
            return false;
        }

        String numberText = txtConNum.getText();
        boolean isNumberValid = Pattern.compile("[(07)]\\d{9}|[+]\\d{11}").matcher(numberText).matches();
        if (!isNumberValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Patient Number").show();
            return false;
        }
        return true;
    }


    @FXML
    void BookOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"book_form.fxml","book");
    }

    @FXML
    void BookOrdersOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"book_orders_form.fxml","book_order");
    }

    @FXML
    void BookSeachsOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "book_search_form.fxml", "income");

    }

    @FXML
    void CLEAREOnAction(ActionEvent event) {

        clearFields();
    }

    @FXML
    void DELETEOnAction(ActionEvent event) {

        String d_id = txtd_id.getText();

        var model = new DonationBOImpl();

        try {
            boolean isSaved = model.deleteDonation(d_id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Donation Delete!").show();
                clearFields();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void DonationOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"donation_form.fxml","donation");
    }

    @FXML
    void EmployeOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"employe_form.fxml","employe");
    }

    @FXML
    void IncomeOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"income_form.fxml","income");
    }

    @FXML
    void LoginHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void LogoutOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"login_form.fxml","login");
    }

    @FXML
    void MemberOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"member_form.fxml","member");
    }

    @FXML
    void SalaryOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"salary_form.fxml","salary");
    }

    @FXML
    void SupplierOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"supplier_form.fxml","supplier");
    }

    @FXML
    void UPDATEOnAction(ActionEvent event) {

        String d_id = txtd_id.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtConNum.getText());
        int monetary_amount= Integer.parseInt(txtMonetaryAmount.getText());



        var dto = new DonationDto(d_id, name, address,tel ,monetary_amount);

        var model = new DonationBOImpl();

        try {
            boolean isSaved = model.updateDonation(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Donation Update!").show();
                clearFields();
                loadData();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    void clearFields() {
        txtd_id.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtConNum.setText("");
        txtMonetaryAmount.setText("");
    }

    public void initialize(){
        cold_id.setCellValueFactory(new PropertyValueFactory<>("d_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("d_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colMonetaryAmount.setCellValueFactory(new PropertyValueFactory<>("monetary_amount"));

        loadData();
    }

    private void loadData() {
        DonationBOImpl customerModel = new DonationBOImpl();
        try {
            List<DonationDto> all = customerModel.getAll();

            tblDonation.getItems().setAll(FXCollections.observableArrayList(all));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void SearchOnAction(ActionEvent event) {

        String code = txtd_id.getText();

        try {
            DonationDto dto = donationBO.SEarchDonation(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Docter not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    private void setFields(DonationDto dto) {
        txtd_id.setText(dto.getD_id());
        txtName.setText(dto.getD_name());
        txtConNum.setText(String.valueOf(dto.getTel()));
        txtAddress.setText(dto.getAddress());
        txtMonetaryAmount.setText(String.valueOf(dto.getMonetary_amount()));



    }
}
