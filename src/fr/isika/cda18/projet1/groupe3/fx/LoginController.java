package fr.isika.cda18.projet1.groupe3.fx;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML // @FXML = C'est une annotation, c'est une méta-donnée sur un objet
	private Button valider;

	@FXML
	private Button liste;

	private String login = "isika";

	private String password = "cda18";

	@FXML
	private TextField txtLogin;

	@FXML
	private TextField txtPassword;

	@FXML
	private void validerHandler(Event e) throws IOException {
		if (login.equals(txtLogin.getText()) && (password.equals(txtPassword.getText()))) {
			Main.estAdmin = true;
			fenetreListe();
		} else {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Connexion");
			alert.setHeaderText("Echec connexion");
			alert.setContentText("Veuillez saisir les identifiants.");
			alert.showAndWait();

		}

		reinitialisationFormulaire();
	}

	public void reinitialisationFormulaire() {
		/// Ré-initialiser le formulaire
		// TextField
		txtLogin.clear();
		txtPassword.clear();

	}
	
	private void fenetreListe() throws IOException {
		Stage primaryStage = (Stage) liste.getScene().getWindow();
		AnchorPane layoutListe = (AnchorPane) FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));
		Scene sceneList = new Scene(layoutListe, 700, 800);
		sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(sceneList);
	}

	@FXML
	private void listeHandler(Event e) throws IOException {
		fenetreListe();
	}
}
