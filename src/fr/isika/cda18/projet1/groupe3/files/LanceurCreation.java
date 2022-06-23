package fr.isika.cda18.projet1.groupe3.files;

import java.io.File;
import java.io.FileReader;

public class LanceurCreation {

	public static void main(String[] args) {
		// java.io => package pour la manipulation de fichier
		
		// chemin relatif : depuis la racine du projet : "src/Projet1"
		
		//File n'existe qu'en mémoire java, il n'est pas encore créé physiquement
		
		File repertoire = new File ("src/Projet1");
		
		// créé physiquement le répertoire sur l'ordianateur
		repertoire.mkdir();

	}

}
