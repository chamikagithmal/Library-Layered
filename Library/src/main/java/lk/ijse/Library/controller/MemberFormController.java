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
import lk.ijse.Library.bo.custom.MemberBO;
import lk.ijse.Library.dto.MemberDto;
import lk.ijse.Library.bo.impl.MemberBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MemberFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCantact;

    @FXML
    private TableColumn<?, ?> colMem_id;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colUser_id;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtMem_id;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUser_id;

    @FXML
    private TableView<MemberDto> tblmember;

    MemberBO memberBO = (MemberBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEMBER);

    @FXML
    void ADDOnAction(ActionEvent event) {

        String mem_id = txtMem_id.getText();
        String mem_name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtContact.getText());
        String user_id = txtUser_id.getText();


        var dto = new MemberDto(mem_id, mem_name, address,tel,user_id );

        try {
            boolean isSaved = memberBO.saveMember(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Memeber saved!").show();
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
        navigate.navigate(pane,"book_orders_form.fxml","book_order");

    }

    @FXML
    void CLEAROnAction(ActionEvent event) {clearFields();}

    @FXML
    void DELETEOnAction(ActionEvent event) {

        String Mem_id = txtMem_id.getText();

        var model = new MemberBOImpl();

        try {
            boolean isSaved = model.deleteMember(Mem_id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Member Delete!").show();
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

        String mem_id = txtMem_id.getText();
        String mem_name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtContact.getText());
        String user_id = txtUser_id.getText();


        var dto = new MemberDto(mem_id, mem_name, address,tel,user_id );

        var model = new MemberBOImpl();

        try {
            boolean isSaved = model.updateMember(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Member Update !").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void clearFields() {
        txtMem_id.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtUser_id.setText("");
    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colMem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCantact.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colUser_id.setCellValueFactory(new PropertyValueFactory<>("user_name"));
    }

    private void loadAllCustomers() {
        var model = new MemberBOImpl();

        ObservableList<MemberDto> obList = FXCollections.observableArrayList();

        try {
            List<MemberDto> dtoList = model. getAllMember();


            for(MemberDto dto : dtoList) {
                obList.add(
                        new MemberDto(
                                dto.getMem_id(),
                                dto.getMem_name(),
                                dto.getAddress(),
                                dto.getTel(),
                                dto.getUser_name()
                        )
                );
            }

            tblmember.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchOnAction(ActionEvent event) {

        String code = txtMem_id.getText();

        try {
            MemberDto dto = memberBO.SEarchMember(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Book not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(MemberDto dto) {
        txtMem_id.setText(dto.getMem_id());
        txtName.setText(dto.getMem_name());
        txtAddress.setText(dto.getAddress());
        txtUser_id.setText(dto.getUser_name());
        txtContact.setText(String.valueOf(dto.getTel()));


    }
}
