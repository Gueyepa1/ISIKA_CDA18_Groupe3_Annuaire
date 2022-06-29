package fr.isika.cda18.projet1.groupe3.fx;
	
import fr.isika.cda18.projet1.groupe3.entites.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	
	public static ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();
//	public static ArbreBinaire stagiaires = new ArbreBinaire();
//	String nom, prenom, lieu, promotion, annee;
//	Stagiaire stagiaire = new Stagiaire(nom, prenom, lieu, promotion, annee);
	@Override
	public void start(Stage primaryStage) { 
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,500,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
