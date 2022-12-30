module com.frontend.book_store_front {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.frontend.book_store_front to javafx.fxml;
    exports com.frontend.book_store_front;
}