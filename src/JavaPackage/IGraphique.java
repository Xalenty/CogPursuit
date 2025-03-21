/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package JavaPackage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author ztsuk
 */
public class IGraphique extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Fxml.fxml"));
            primaryStage.setTitle("CogPursuit");
            primaryStage.setScene(new Scene(root, 1200, 900));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace(); // Affiche l'erreur dans la console
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
