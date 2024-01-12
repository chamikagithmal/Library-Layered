package lk.ijse.Library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.dto.BookDto;
import lk.ijse.Library.dto.tm.BookSearchTm;
import lk.ijse.Library.bo.impl.BookBOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BookSearchFormController implements Initializable {
    @FXML
    public TextField txtAuthorName;
    @FXML
    public TableView tableBook;
    @FXML
    public TableColumn colName;
    @FXML
    public TableColumn colQty;
    @FXML
    public TableColumn colId;
    @FXML
    private AnchorPane pane;
    BookBOImpl bookModel = new BookBOImpl();

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
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

        navigate.navigate(pane,"book_search_form.fxml","income");

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
    void bSearchOnAction(ActionEvent event) {
        try {
            List<BookDto> bookDtos = bookModel.SearchBookByAuthor( txtAuthorName.getText() );

            ObservableList<Object> list = FXCollections.observableArrayList();
            for (BookDto dto : bookDtos) {

                list.add(
                        new BookSearchTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getQty()
                        )
                );
            }

            tableBook.setItems( list );
        } catch (SQLException e) {
            throw new RuntimeException( e );
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
    }
}
