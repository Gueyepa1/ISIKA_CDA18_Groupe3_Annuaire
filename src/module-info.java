module ISIKA_CDA18_Groupe3_Annuaire {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens fr.isika.cda18.projet1.groupe3.fx to javafx.graphics, javafx.fxml, javafx.base;
	opens fr.isika.cda18.projet1.groupe3.entites to javafx.graphics, javafx.fxml, javafx.base;
}
