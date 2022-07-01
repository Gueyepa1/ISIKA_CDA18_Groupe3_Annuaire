package fr.isika.cda18.projet1.groupe3.fx;

import java.net.URL;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.groupe3.entites.*;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.Printer.MarginType;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListeStagiairesController implements Initializable {

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
		Stage primaryStage = (Stage) imprimer.getScene().getWindow();
		Printer myPrinter = Printer.getDefaultPrinter();
		myPrinter.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, MarginType.HARDWARE_MINIMUM);
		PrinterJob printerJob = PrinterJob.createPrinterJob(myPrinter);
		ObservableList<Stagiaire> stagiaires = tableauStagiaires.getItems();
		Label printed = new Label();
		for(Stagiaire stagiaire : stagiaires) {
			printed.setText(printed.getText() + stagiaire.toString() + "\n");
		}
		while(printerJob.printPage(printed)) {
			if(printed.getText().length() > 2752) {
				printed.setText(printed.getText().substring(2752));
			} else {break;}
		}
		printerJob.endJob();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Impression");
		alert.setHeaderText("");
		alert.setContentText("Impression terminée");
		alert.showAndWait();
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
	}
}
