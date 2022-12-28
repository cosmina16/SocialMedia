module lab.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    exports lab.lab;
    opens lab.lab to javafx.fxml;
    opens lab.lab.controller to javafx.fxml;
    opens lab.lab.domain to javafx.base;
    opens lab.lab.controller.tableviews to javafx.base;
}