/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package JavaPackage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Utilisateur
 */

public class InterfaceGraphique extends Application {
    
    //@Override
    //public void start(Stage primaryStage) {
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        @Override
        public void start(Stage primaryStage) throws Exception {
        // Charge le fichier FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InterfaceGraphique.class.getResource("FXML.fxml"));
            Parent root = loader.load();

        // Crée la scène avec le fichier FXML chargé
            Scene scene = new Scene(FXMLLoader.load(),320,240);

            primaryStage.setTitle("CogPursuit");
            primaryStage.setScene(scene);
            primaryStage.show();
        
        
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
