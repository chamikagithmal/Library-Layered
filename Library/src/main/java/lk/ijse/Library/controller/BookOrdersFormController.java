package lk.ijse.Library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Library.bo.BOFactory;
import lk.ijse.Library.bo.custom.BookOrderBO;
import lk.ijse.Library.dto.BookDto;
import lk.ijse.Library.dto.BookOrdersDto;
import lk.ijse.Library.dto.MemberDto;
import lk.ijse.Library.dto.tm.CartTm;
import lk.ijse.Library.bo.impl.BookBOImpl;
import lk.ijse.Library.bo.impl.BookOrderBOImpl;
import lk.ijse.Library.bo.impl.MemberBOImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

public class BookOrdersFormController {

    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> ColQty;

    @FXML
    private ComboBox<String> cmbBook;

    @FXML
    private ComboBox<String> cmbMem;



    @FXML
    private TableColumn<?, ?> colBook_id;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colMem_id;

    @FXML
    private TableColumn<?, ?> colReternDate;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<CartTm> tblBookOrder;

    @FXML
    private TextField txtQty;
    @FXML
    private DatePicker txtRdate;

    @FXML
    private DatePicker txtdate;

    BookOrderBO bookOrderBO = (BookOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOKORDER);

    @FXML
    public void AddToCartOnAction(ActionEvent event) {

        int price =100;
        String mem_id = cmbMem.getValue();
        String Book_id = cmbBook.getValue();

        int Qty = Integer.parseInt(txtQty.getText());

        String date = String.valueOf(txtdate.getValue());
        String reterndate = String.valueOf(txtRdate.getValue());

        double total= price*Qty;

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblBookOrder.getItems().size(); i++) {


                    if (colBook_id.getCellData(i).equals(Book_id)) {


                        Qty += (int) ColQty.getCellData(i);
                        total = Qty * price;

                        obList.get(i).setQty(Qty);
                        tblBookOrder.refresh();
                        return;
                    }

                }


        }

        CartTm cartTm = new CartTm(mem_id, Book_id, Qty, date, reterndate);

        obList.add(cartTm);
        tblBookOrder.setItems(obList);


    }

    private void setCellValueFactory() {
        colMem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
        colBook_id.setCellValueFactory(new PropertyValueFactory<>("Book_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colReternDate.setCellValueFactory(new PropertyValueFactory<>("reterndate"));
        ColQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));

    }



    @FXML
    void PlaceOrderOnAction(ActionEvent event) {

        String mem_id = cmbMem.getValue();
        String Book_id = cmbBook.getValue();
        int Qty = Integer.parseInt(txtQty.getText());

        CartTm cartTM=null;

        List<BookOrdersDto> orderDetailDTOS = new ArrayList<>();

        for (int i = 0; i < tblBookOrder.getItems().size(); i++) {
            cartTM = obList.get(i);

            BookOrdersDto bookOrdersDto = new BookOrdersDto(
                    cartTM.getMem_id(),
                    cartTM.getBook_id(),
                    cartTM.getQty(),
                    cartTM.getDate(),
                    cartTM.getReterndate()
            );


            orderDetailDTOS.add(bookOrdersDto);
        }

        try {

            boolean isPlaced = bookOrderBO.pleaceOrder(orderDetailDTOS);

                } catch (Exception e) {
                    e.printStackTrace();
                }
    }

    @FXML
    void ViweOrderOnAction(ActionEvent event) {

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

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        navigate.navigate(pane,"login_form.fxml","login");
    }

    private void loadMem() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<MemberDto> list = new MemberBOImpl().getAllMem();

            for (MemberDto dto : list) {
                obList.add(dto.getMem_id());
            }
            cmbMem.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void loadBoo() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<BookDto> list = new BookBOImpl().getAllBook();

            for (BookDto dto : list) {
                obList.add(dto.getId());
            }
            cmbBook.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initialize() {
        loadMem();
        loadBoo();
        setCellValueFactory();
    }
}
