package com.pixelduke.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import org.scenicview.ScenicView;

import java.net.URL;

/**
 * Created by pedro_000 on 1/6/2015.
 */
public class AdvancedRibbonTest extends Application {
    private static final String RESOURCE = "AdvancedRibbonFXML.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        URL resource = AdvancedRibbonTest.class.getResource(RESOURCE);
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);

//        ScenicView.show(scene);

        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}