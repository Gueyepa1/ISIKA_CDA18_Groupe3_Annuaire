package fr.isika.cda18.projet1.groupe3.fx;
	
import java.io.File;
import java.io.RandomAccessFile;

import fr.isika.cda18.projet1.groupe3.entites.Noeud;
import fr.isika.cda18.projet1.groupe3.entites.Stagiaire;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	public static ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) { 
		try {
			if(!new File("src/Donnees/ListeStagiaires.bin").exists()) {
				Noeud.donneesVersRaf();
			}
			RandomAccessFile raf = new RandomAccessFile("src/Donnees/ListeStagiaires.bin", "rw");
			stagiaires = Noeud.lireListeDepuisFichier(raf);
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
