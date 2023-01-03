module com.frontend.book_store_front {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;
    requires jasperreports;


    opens com.frontend.Views to javafx.fxml;
    exports com.frontend.Views;
}