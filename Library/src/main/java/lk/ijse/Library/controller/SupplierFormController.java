package lk.ijse.Library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.bo.BOFactory;
import lk.ijse.Library.bo.custom.SupplierBO;
import lk.ijse.Library.dto.SupplierDto;
import lk.ijse.Library.dto.tm.SupplierTm;
import lk.ijse.Library.bo.impl.SupplierBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCantact;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUser_id;

    @FXML
    private TableColumn<?, ?> cols_id;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txt_Memid;


    @FXML
    private TextField txtName;

    @FXML
    private TableView<SupplierTm> tblSupplier;

 SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);

    @FXML
    void ADDOnAction(ActionEvent event) {

        String s_id = txt_Memid.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtContact.getText());



        var dto = new SupplierDto(s_id, name, address,tel );

        var model = new SupplierBOImpl();

        try {
            boolean isSaved = model.saveSupplier(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

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
    void CLEAROnAction(ActionEvent event) {clearFields();}

    @FXML
    void DELETEOnAction(ActionEvent event) {

        String s_id = txt_Memid.getText();

        var model = new SupplierBOImpl();

        try {
            boolean isSaved = model.deleteSupplier(s_id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Delete!").show();
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

        String s_id = txt_Memid.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtContact.getText());



        var dto = new SupplierDto(s_id, name, address,tel );

        var model = new SupplierBOImpl();

        try {
            boolean isSaved = model.updateSupplier(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier Update!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        cols_id.setCellValueFactory(new PropertyValueFactory<>("s_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCantact.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllCustomers() {
        var model = new SupplierBOImpl();

        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDto> dtoList = model.getAllSupplier();

            for(SupplierDto dto : dtoList) {
                obList.add(
                        new SupplierTm(
                                dto.getS_id(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getTel()
                        )
                );
            }

            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void clearFields() {
        txt_Memid.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }

    @FXML
    void searchOnAction(ActionEvent event) throws SQLException {

        String code = txt_Memid.getText();

        SupplierDto dto = supplierBO.SearchSuppiler(code);
        if (dto != null) {
            setFields(dto);
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Supplier not found!").show();
        }
    }

    private void setFields(SupplierDto dto) {
        txt_Memid.setText(dto.getS_id());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtContact.setText(String.valueOf(dto.getTel()));
    }

}
