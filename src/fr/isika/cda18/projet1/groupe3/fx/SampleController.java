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
					ArbreBinaire stagiaires = new ArbreBinaire();
					Stagiaire stagiaire = new Stagiaire(nom, prenom, lieu, promotion, annee);
					// Ajouter cet objet Stagiaire dans la liste
//					Main.stagiaires.add(stagiaire);
//					Main.stagiaires.add(stagiaire);
//					Stagiaire stage = new Stagiaire("Jean", "Lionel", "99", "ATOS 23", "2008");
//					Stagiaire stage1 = new Stagiaire("Omar", "Bal", "98", "ATOS 22", "2010");
//					stagiaires.ajouterNoeud(new Noeud(stage));
//					stagiaires.ajouterNoeud(new Noeud(stage1));

					/// début partie alert
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Stagiaire dans la liste");
					alert.setHeaderText("Bienvenue à ISIKA");
					alert.setContentText("Vous ètes bien dans la liste des stagiaires d'ISIKA");
					alert.showAndWait();
					/// fin partie alert*/

					System.out.println("******* Contenu de la liste *********");
					System.out.println(Main.stagiaires); // L'affichage du contenu de la liste
					
					reinitialisationFormulaire();

				}
				
				public void reinitialisationFormulaire() {
					/// Ré-initialiser le formulaire
							//TextField
							txtNom.clear();
							txtPrenom.clear();
							txtLieu.clear();
							txtPromotion.clear();
							txtAnnee.clear();
					
				}
	
	
	@FXML
	private void ListeHandler(Event e) throws IOException {
		//System.out.println("Vers l'interface Liste des stagiaires");
		
		// 1) On cherche le grand père du bouton[le bouton a comme pere la scène et la scène a comme père le Stage]
		Stage primaryStage = (Stage) Liste.getScene().getWindow();
		// 2) Chargement de layout (design) depuis le fichier ListeStagiaires.fxml
		AnchorPane layoutListe = (AnchorPane)FXMLLoader.load(getClass().getResource("ListeStagiaires.fxml"));
		
		// 3) On créer une scène
		Scene sceneList = new Scene(layoutListe,850,500);
		sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		// 4) On demande à notre stage(théatre) d'affiche la nouvelle scène : sceneList
		primaryStage.setScene(sceneList);
		
		
	}
	
}
