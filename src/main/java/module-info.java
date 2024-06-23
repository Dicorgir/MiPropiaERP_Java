module com.example.mipropiaerp {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires java.desktop;
    requires jasperreports;
    requires com.zaxxer.hikari;


    opens com.example.mipropiaerp to javafx.fxml;
    exports com.example.mipropiaerp;
}