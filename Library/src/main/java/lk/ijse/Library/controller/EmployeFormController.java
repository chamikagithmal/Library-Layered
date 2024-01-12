package lk.ijse.Library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.bo.BOFactory;
import lk.ijse.Library.bo.custom.EmployeBO;
import lk.ijse.Library.dto.EmployeDto;
import lk.ijse.Library.dto.tm.EmployeTm;
import lk.ijse.Library.bo.impl.EmployeBOImpl;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.sql.Date.valueOf;

public class EmployeFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCantact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colEmp_id;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<EmployeTm> tblemploye;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNum;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtempName;

    @FXML
    private TextField txtemp_id;

    @FXML
    private TableColumn<?, ?> colUser_id;

    @FXML
    private TextField txtuser_name;

    EmployeBO employeBO = (EmployeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYE);

    @FXML
    void ADDOnAction(ActionEvent event) {

        boolean isCustomerValid = validateEmploye();
        if (isCustomerValid) {
        String emp_id = txtemp_id.getText();
        String name = txtempName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtContactNum.getText());
        Date DOB = valueOf(txtDOB.getText());
        String user_name = txtuser_name.getText();


        var dto = new EmployeDto(emp_id, name, address, tel, DOB, user_name);

        var model = new EmployeBOImpl();

        try {
            boolean isSaved = model.saveEmploye(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employe saved!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Customer Details", ButtonType.OK).show();
        }

    }

    private boolean validateEmploye() {

        String veterinaryIdText = txtemp_id.getText();
        Pattern compile = Pattern.compile("[E][0-9]{3,}");
        Matcher matcher = compile.matcher(veterinaryIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid veterinary ID").show();
            return false;
        }
        String addressText = txtempName.getText();
        boolean isAddressValid = Pattern.compile("[A-Za-z]{2,}").matcher(addressText).matches();
        if (!isAddressValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid veterinary Address").show();
            return false;
        }
        return true;

    }

    @FXML
    void BookOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "book_form.fxml", "book");
    }

    @FXML
    void BookOrdersOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "book_orders_form.fxml", "book_order");
    }

    @FXML
    void BookSeachsOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "book_search_form.fxml", "income");

    }

    @FXML
    void CLEAROnAction(ActionEvent event) {
        clearFields();
    }

    void clearFields() {
        txtemp_id.setText("");
        txtempName.setText("");
        txtAddress.setText("");
        txtContactNum.setText("");
        txtDOB.setText("");
        txtuser_name.setText("");
    }

    @FXML
    void UPDATEOnAction(ActionEvent event) {

        String emp_id = txtemp_id.getText();
        String name = txtempName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtContactNum.getText());
        Date DOB = valueOf(txtDOB.getText());
        String user_name = txtuser_name.getText();


        var dto = new EmployeDto(emp_id, name, address, tel, DOB, user_name);

        var model = new EmployeBOImpl();

        try {
            boolean isSaved = model.updateEmploye(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employe Update!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void DELETEOnAction(ActionEvent event) {

        String emp_id = txtemp_id.getText();

        var model = new EmployeBOImpl();

        try {
            boolean isSaved = model.deleteEmploye(emp_id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employe Delete!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void DonationOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "donation_form.fxml", "donation");
    }

    @FXML
    void EmployeOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "employe_form.fxml", "employe");
    }

    @FXML
    void IncomeOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "income_form.fxml", "income");
    }

    @FXML
    void LoginHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void LogoutOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "login_form.fxml", "login");
    }

    @FXML
    void MemberOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "member_form.fxml", "member");
    }

    @FXML
    void ReportOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane,"report_Form.fxml","report");

    }

    @FXML
    void SalaryOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "salary_form.fxml", "salary");
    }

    @FXML
    void SupplierOnAction(ActionEvent event) throws IOException {
        navigate.navigate(pane, "supplier_form.fxml", "supplier");
    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colEmp_id.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCantact.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colUser_id.setCellValueFactory(new PropertyValueFactory<>("user_name"));
    }

    private void loadAllCustomers() {
        var model = new EmployeBOImpl();

        ObservableList<EmployeTm> obList = FXCollections.observableArrayList();

        try {
            List<EmployeDto> dtoList = model.getAll();

            for (EmployeDto dto : dtoList) {
                obList.add(
                        new EmployeTm(
                                dto.getEmp_id(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getTel(),
                                dto.getDOB(),
                                dto.getUser_name()

                        )
                );
            }

            tblemploye.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void SearchOnAction(ActionEvent event) {

        String code = txtemp_id.getText();

        try {
            EmployeDto dto = employeBO.SEarchEmploye(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Docter not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(EmployeDto dto) {
        txtemp_id.setText(dto.getEmp_id());
        txtempName.setText(dto.getName());
        txtContactNum.setText(String.valueOf(dto.getTel()));
        txtAddress.setText(dto.getAddress());
        txtDOB.setText(String.valueOf(dto.getDOB()));
        txtuser_name.setText(dto.getUser_name());

    }
}
