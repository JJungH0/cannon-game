module com.nhnacademy.cannongame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nhnacademy.cannongame to javafx.fxml;
    exports com.nhnacademy.cannongame;
}