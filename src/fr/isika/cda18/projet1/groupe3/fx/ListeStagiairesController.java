package fr.isika.cda18.projet1.groupe3.fx;

import java.net.URL;
import java.util.ResourceBundle;

import fr.isika.cda18.projet1.groupe3.entites.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
