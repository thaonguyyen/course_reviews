module hw7.coursereview.vdk4dy.ews9rk.rhq6nu.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example to javafx.fxml;
    exports org.example;
}