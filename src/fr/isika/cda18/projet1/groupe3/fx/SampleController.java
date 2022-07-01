package fr.isika.cda18.projet1.groupe3.fx;

import java.io.IOException;

import fr.isika.cda18.projet1.groupe3.entites.*;
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

public class SampleController {

	@FXML // @FXML = C'est une annotation, c'est une méta-donnée sur un objet
	private Button Ajouter;

	@FXML // @FXML = C'est une annotation, c'est une méta-donnée sur un objet
	private Button Annuler;

	@FXML // @FXML = C'est une annotation, c'est une méta-donnée sur un objet
	private Button Liste;

	@FXML
	private TextField txtNom;

	@FXML
	private TextField txtPrenom;

	@FXML
	private TextField txtLieu;

	@FXML
	private TextField txtPromotion;

	@FXML
	private TextField txtAnnee;

	@FXML
	private void AjouterHandler(Event e) {
		String nom = txtNom.getText(); // récupérer le nom
		String prenom = txtPrenom.getText(); // récupérer l'email
		String lieu = txtLieu.getText(); // récupérer le phone
		String promotion = txtPromotion.getText(); // récupérer le nom
		String annee = txtAnnee.getText(); // récupérer l'email
		// Construction d'un Objet de type Stagiaire avec le constructeur avec
		// paramètres
		Stagiaire stagiaire = new Stagiaire(nom, prenom, lieu, promotion, annee);

		/// début partie alert
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Nouveau stagiaire");
		alert.setHeaderText("Stagiaire ajout� avec succ�s");
		alert.setContentText(nom+" "+prenom+" a bien �t� ajout� � la liste de stagiaires.");
		alert.showAndWait();
		/// fin partie alert*/

		reinitialisationFormulaire();
	}

	public void reinitialisationFormulaire() {
		/// Ré-initialiser le formulaire
		// TextField
		txtNom.clear();
		txtPrenom.clear();
		txtLieu.clear();
		txtPromotion.clear();
		txtAnnee.clear();

	}

	@FXML
	private void ListeHandler(Event e) throws IOException {
		// System.out.println("Vers l'interface Liste des stagiaires");

		// 1) On cherche le grand père du bouton[le bouton a comme pere la scène et la
		// scène a comme père le Stage]
		Stage primaryStage = (Stage) Liste.getScene().getWindow();
		// 2) Chargement de layout (design) depuis le fichier ListeStagiaires.fxml
		AnchorPane layoutListe = (AnchorPane) FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));

		// 3) On créer une scène
		Scene sceneList = new Scene(layoutListe, 700, 800);
		sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// 4) On demande à notre stage(théatre) d'affiche la nouvelle scène :
		// sceneList
		primaryStage.setScene(sceneList);

	}

}
