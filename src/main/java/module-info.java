module com.main.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;

    opens com.main.snakegame to javafx.fxml;
    exports com.main.snakegame;
    exports com.main.snakegame.Controller;
    opens com.main.snakegame.Controller to javafx.fxml;
}