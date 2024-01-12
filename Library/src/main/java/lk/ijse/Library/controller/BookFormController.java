package lk.ijse.Library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.bo.BOFactory;
import lk.ijse.Library.bo.custom.BookBO;
import lk.ijse.Library.dto.BookDto;
import lk.ijse.Library.dto.tm.BookTm;
import lk.ijse.Library.bo.impl.BookBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookFormController {

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBook_id;

    @FXML
    private TableColumn<?, ?> colCatagory;

    @FXML
    private TableColumn<?, ?> colLanguege;

    @FXML
    private TableColumn<?, ?> cold_id;

    @FXML
    private TableColumn<?, ?> cols_id;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtBook_id;

    @FXML
    private TextField txtCatagory;

    @FXML
    private TextField txtLanguege;

    @FXML
    private TextField txtd_id;

    @FXML
    private TextField txts_id1;

    @FXML
    private TableView<BookTm> tblBook;

    @FXML
    private TableColumn<?, ?> colQty;
    @FXML
    private TextField txtQty;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

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
    void ADDOnAction(ActionEvent event) {

        boolean isCustomerValid = validateBook();
        if (isCustomerValid) {
            String id = txtBook_id.getText();
            String name = txtBookName.getText();
            int qty = Integer.parseInt(txtQty.getText());
            String author = txtAuthor.getText();
            String catagory = txtCatagory.getText();
            String languege = txtLanguege.getText();
            String d_id = txtd_id.getText();
            String s_id = txts_id1.getText();

            var dto = new BookDto(id, name,qty, author, catagory, languege, d_id, s_id);

            var model = new BookBOImpl();

            try {
                boolean isSaved = model.saveBook(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
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

    /**
     * HKJHFJKHEFKJKEF
     * @return booleN
     */
    private boolean validateBook() {

        String veterinaryIdText = txtBook_id.getText();
        Pattern compile = Pattern.compile("[B][0-9]{3,}");
        Matcher matcher = compile.matcher(veterinaryIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid  ID").show();
            return false;
        }
        String addressText = txtAuthor.getText();
        boolean isAddressValid = Pattern.compile("[A-Za-z]{2,}").matcher(addressText).matches();
        if (!isAddressValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid  Author").show();
            return false;
        }

        String nameText = txtBookName.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{2,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid  Name").show();
            return false;
        }
        return true;

    }


        @FXML
    void CLEAROnAction(ActionEvent event) {clearFields();}

    @FXML
    void DELETEOnAction(ActionEvent event) {

        String id = txtBook_id.getText();

        var model = new BookBOImpl();

        try {
            boolean isSaved = model.deleteBook(id);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Book Delete!").show();
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

        String id = txtBook_id.getText();
        String name = txtBookName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String author = txtAuthor.getText();
        String catagory = txtCatagory.getText();
        String languege = txtLanguege.getText();
        String d_id = txtd_id.getText();
        String s_id = txts_id1.getText();

        var dto = new BookDto(id, name, qty, author, catagory,languege,d_id,s_id);

        var model = new BookBOImpl();

        try {
            boolean isSaved = model.updateBook(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Book Update!").show();
                clearFields();
                loadAllCustomers();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void clearFields() {
        txtBook_id.setText("");
        txtBookName.setText("");
        txtQty.setText("");
        txtAuthor.setText("");
        txtCatagory.setText("");
        txtLanguege.setText("");
        txtd_id.setText("");
        txts_id1.setText("");
    }

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colBook_id.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("book_name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCatagory.setCellValueFactory(new PropertyValueFactory<>("catagory"));
        colLanguege.setCellValueFactory(new PropertyValueFactory<>("languege"));
        cold_id.setCellValueFactory(new PropertyValueFactory<>("d_id"));
        cols_id.setCellValueFactory(new PropertyValueFactory<>("s_id"));


    }

    private void loadAllCustomers() {
        var model = new BookBOImpl();

        ObservableList<BookTm> obList = FXCollections.observableArrayList();

        try {
            List<BookDto> dtoList = model. getAllBook();


            for(BookDto dto : dtoList) {
                obList.add(
                        new BookTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getQty(),
                                dto.getAuthor(),
                                dto.getCatagory(),
                                dto.getLanguege(),
                                dto.getD_id(),
                                dto.getS_id()
                        )
                );
            }

            tblBook.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void searchOnAction(ActionEvent event) {

        String code = txtBook_id.getText();

        try {
            BookDto dto = bookBO.SEarchBook(code);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Book not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(BookDto dto) {
        txtBook_id.setText(dto.getId());
        txtBookName.setText(dto.getName());
        txtQty.setText(String.valueOf(dto.getQty()));
        txtAuthor.setText(dto.getAuthor());
        txtLanguege.setText(dto.getLanguege());
        txtCatagory.setText(dto.getCatagory());
        txtd_id.setText(dto.getD_id());
        txts_id1.setText(dto.getS_id());


    }
}
