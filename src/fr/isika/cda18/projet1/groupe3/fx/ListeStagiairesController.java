package fr.isika.cda18.projet1.groupe3.fx;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.groupe3.entites.*;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ListeStagiairesController implements Initializable {
	@FXML
	private Button supprimer;

	@FXML
	private Button modifier;
	
	@FXML
	private Button chercher;
	
	@FXML
	private Button ajouterStagiaire;

	@FXML
	private TextField nomRecherche;

	@FXML
	private TextField prenomRecherche;

	@FXML
	private TextField lieuRecherche;

	@FXML
	private TextField promotionRecherche;

	@FXML
	private TextField anneeRecherche;

	@FXML
	private TableColumn<Stagiaire, String> nomC;

	@FXML
	private TableColumn<Stagiaire, String> prenomC;

	@FXML
	private TableColumn<Stagiaire, String> lieuC;

	@FXML
	private TableColumn<Stagiaire, String> promotionC;

	@FXML
	private TableColumn<Stagiaire, String> anneeC;

	@FXML
	private TableView<Stagiaire> tableauStagiaires;

	@FXML
	private Button imprimer;

	@FXML
	private void imprimerHandler(Event e) {
		Printer myPrinter = Printer.getDefaultPrinter();
		myPrinter.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, MarginType.HARDWARE_MINIMUM);
		PrinterJob printerJob = PrinterJob.createPrinterJob(myPrinter);

		ObservableList<Stagiaire> stagiaires = tableauStagiaires.getItems();
		Label printed = new Label();

		for (Stagiaire stagiaire : stagiaires) {
			printed.setText(printed.getText() + stagiaire.toString() + "\n");
		}

		while (printerJob.getJobStatus() != PrinterJob.JobStatus.CANCELED && printerJob.printPage(printed)) {
			if (printed.getText().length() > 2752) {
				printed.setText(printed.getText().substring(2752));
			} else {
				break;
			}
		}

		if (printerJob.getJobStatus() == PrinterJob.JobStatus.PRINTING) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Impression");
			alert.setHeaderText("");
			alert.setContentText("Impression terminée");
			alert.showAndWait();
		}

		printerJob.endJob();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		prenomC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		lieuC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("lieu"));
		promotionC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("promotion"));
		anneeC.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));

		// Charger le TableView par Observable List qui contient nos objets Produits
		tableauStagiaires.setItems(Main.stagiaires);
		
		if (Main.estAdmin) {
			supprimer.setDisable(false);
			modifier.setDisable(false);
		}
	}

	@FXML
	private void rechercherHandler(Event e) {
		String nom = nomRecherche.getText(); // récupérer le nom
		String prenom = prenomRecherche.getText(); // récupérer le prénom
		String lieu = lieuRecherche.getText(); // récupérer le lieu
		String promotion = promotionRecherche.getText(); // récupérer la promotion
		String annee = anneeRecherche.getText(); // récupérer l'année

		if(nom.isBlank() && prenom.isBlank() && lieu.isBlank() && promotion.isBlank() && annee.isBlank()) {
			tableauStagiaires.setItems(Main.stagiaires);
		} else {
			Stagiaire stagiaire = new Stagiaire(nom, prenom, lieu, promotion, annee);
			try {
				RandomAccessFile raf = new RandomAccessFile("src/Donnees/ListeStagiaires.bin", "rw");
				ObservableList<Stagiaire> recherche = Noeud.rechercheMulticritere(raf, stagiaire);
				tableauStagiaires.setItems(recherche);
			} catch (Exception e1) {
			} 
	
			reinitialisationFormulaire();
			}
	}

	public void reinitialisationFormulaire() {
		/// Ré-initialiser le formulaire
		// TextField
		nomRecherche.clear();
		prenomRecherche.clear();
		lieuRecherche.clear();
		promotionRecherche.clear();
		anneeRecherche.clear();
	}

	@FXML
	private void ajouterStagiaireHandler(Event e) throws IOException {
		Stage primaryStage = (Stage) ajouterStagiaire.getScene().getWindow();
		BorderPane layoutAjouterStagiaire = (BorderPane) FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene sceneList = new Scene(layoutAjouterStagiaire, 500, 500);
		sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(sceneList);

	}

	@FXML
	private void supprimerHandler(Event e) {
		Stagiaire stagiaire = tableauStagiaires.getSelectionModel().getSelectedItem();
		if (stagiaire != null) {
			try {
				RandomAccessFile raf = new RandomAccessFile("src/Donnees/ListeStagiaires.bin", "rw");
				Noeud.supprimerNoeud(raf, stagiaire);
			} catch (Exception e1) {
			}
			Main.stagiaires.remove(tableauStagiaires.getSelectionModel().getSelectedIndex());
			
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur suppression");
			alert.setContentText("Veuillez sélectionner un stagiaire à supprimer.");
			alert.showAndWait();
		}
	}

	@FXML
	private void modifierHandler(Event e) throws IOException {
		Stagiaire stagiaire = tableauStagiaires.getSelectionModel().getSelectedItem();
		if (stagiaire != null) {
			ModifierStagiaireController.stagiaireAModifier = new Stagiaire(stagiaire.getNom(), stagiaire.getPrenom(), stagiaire.getLieu(), stagiaire.getPromotion(), stagiaire.getAnnee());
			Stage primaryStage = (Stage) modifier.getScene().getWindow();
			BorderPane layoutmodifierStagiaire = (BorderPane) FXMLLoader.load(getClass().getResource("ModifierStagiaire.fxml"));
			Scene sceneList = new Scene(layoutmodifierStagiaire, 500, 500);
			sceneList.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(sceneList);
			
			
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur modification");
			alert.setContentText("Veuillez sélectionner un stagiaire à modifier.");
			alert.showAndWait();
		}
	}
}
