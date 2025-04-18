module hi.flappybird {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens hi.flappybird.vinnsla to javafx.fxml;
    opens hi.flappybird.vidmot to javafx.fxml;

    exports hi.flappybird.vinnsla;
    exports hi.flappybird.vidmot;
}
