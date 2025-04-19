#!/bin/bash

echo "Running Flappy Bird JavaFX game..."

java \
--module-path "/Users/brynja.kristin/Downloads/javafx-sdk-24.0.1/lib" \
--add-modules javafx.controls,javafx.fxml \
-jar target/flappybird-1.0-SNAPSHOT-jar-with-dependencies.jar

