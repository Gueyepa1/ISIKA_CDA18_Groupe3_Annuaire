package fr.isika.cda18.projet1.groupe3.files;
import fr.isika.cda18.projet1.groupe3.entites.Stagiaire;

public class LanceurLireEcriture {

	public static void main(String[] args) {
		Stagiaire stage = new Stagiaire(null, null, null, null, null);
		stage.initialiser();
		System.out.println(stage.getStagiaires());

	}

}

