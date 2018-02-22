package com.pixelduke.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by pedro_000 on 1/20/14.
 */
public class RibbonWithEmptyTabsTest extends Application{

    @Override
    public void start(Stage primaryStage) {
        BorderPane rootNode = new BorderPane();
        Ribbon ribbon = new Ribbon();
        ribbon.getTabTitles().addAll("Home", "Scrape", "Filter", "Options", "Help");
        rootNode.setCenter(ribbon);

        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}