module com.example.orcamento {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.orcamento to javafx.fxml;
    exports com.example.orcamento;
}