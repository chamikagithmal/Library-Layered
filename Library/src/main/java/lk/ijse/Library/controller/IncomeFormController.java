package lk.ijse.Library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.dto.IncomeDto;
import lk.ijse.Library.bo.impl.IncomeBOimpl;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IncomeFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colMem_id;

    @FXML
    private TableColumn<?, ?> coli_id;

    @FXML
    private TableView<IncomeDto> tblIncome;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtMem_id;

    @FXML
    private TextField txtempAmount;

    @FXML
    private TextField txti_id;

    @FXML
    void ADDOnAction(ActionEvent event) {

        boolean isCustomerValid = validateIncome();
        if (isCustomerValid) {
        String ic_id = txti_id.getText();
        String amount = txtempAmount.getText();
        Date date = Date.valueOf(txtDate.getText());
        String mem_id = txtMem_id.getText();


        var dto = new IncomeDto(ic_id, amount, date,mem_id );

        var model = new IncomeBOimpl();

        try {
            boolean isSaved = model.saveIcome(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Income saved!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid  Details", ButtonType.OK).show();
        }

    }

    private boolean validateIncome() {

        String veterinaryIdText = txti_id.getText();
        Pattern compile = Pattern.compile("[I][0-9]{3,}");
        Matcher matcher = compile.matcher(veterinaryIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid  ID").show();
            return false;
        }
        return true;

    }


    @FXML
    void BookOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "book_form.fxml", "book");


    }

    @FXML
    private AnchorPane pane;

    @FXML
    void BookOrdersOnAction(ActionEvent event) throws IOException {

        navigate.navigate(pane,"book_form.fxml","book");
    }

    @FXML
    void BookSeachsOnAction(ActionEvent event) throws IOException {

        navigate.navigate(pane,"book_orders_form.fxml","book_order");
    }

    @FXML
    void CLEAROnAction(ActionEvent event) {clearFields();}

    @FXML
    void DELETEOnAction(ActionEvent event) {

        String ic_id = txti_id.getText();

        var model = new IncomeBOimpl();

        try {
            boolean isSaved = model.deleteIncome(ic_id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Income Delete!").show();
                clearFields();
                loadAllCustomers();
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
    void ReportOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"report_Form.fxml","report");

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

        String ic_id = txti_id.getText();
        String amount = txtempAmount.getText();
        Date date = Date.valueOf(txtDate.getText());
        String mem_id = txtMem_id.getText();


        var dto = new IncomeDto(ic_id, amount, date,mem_id );

        var model = new IncomeBOimpl();

        try {
            boolean isSaved = model.saveIcome(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Income Update!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    void clearFields() {
        txti_id.setText("");
        txtempAmount.setText("");
        txtDate.setText("");
        txtMem_id.setText("");
    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        coli_id.setCellValueFactory(new PropertyValueFactory<>("ic_id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colMem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
    }

    private void loadAllCustomers() {
        var model = new IncomeBOimpl();

        ObservableList<IncomeDto> obList = FXCollections.observableArrayList();

        try {
            List<IncomeDto> dtoList = model.getAllIncome();

            for(IncomeDto dto : dtoList) {
                obList.add(
                        new IncomeDto(
                                dto.getIc_id(),
                                dto.getAmount(),
                                dto.getDate(),
                                dto.getMem_id()
                        )
                );
            }

            tblIncome.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
