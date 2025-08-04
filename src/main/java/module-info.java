module com.nhnacademy.cannongame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.nhnacademy.cannongame to javafx.fxml;
    exports com.nhnacademy.cannongame;
    exports com.nhnacademy.cannongame.ballWorld;
    opens com.nhnacademy.cannongame.ballWorld to javafx.fxml;
}