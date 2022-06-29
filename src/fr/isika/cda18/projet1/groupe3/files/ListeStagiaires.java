package fr.isika.cda18.projet1.groupe3.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import fr.isika.cda18.projet1.groupe3.entites.Stagiaire;

public class ListeStagiaires {
	private ArrayList<Stagiaire> stagiaires = new ArrayList<>();

	public ArrayList<Stagiaire> getStagiaires(){
		return stagiaires;
	}

	public void initialiser() {
		File file = new File("src/Donnees/STAGIAIRES.DON");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			Stagiaire stagiaire;

			String nom = "";
			String prenom = "";
			String lieu = "";
			String promotion = "";
			String annee = "";

			// tant qu'il y a quelque chose à lire, lit la ligne
			while (br.ready()) {
				nom = br.readLine();
				prenom = br.readLine();
				lieu = br.readLine();
				promotion = br.readLine();
				annee = br.readLine();
				br.readLine();
				stagiaire = new Stagiaire(nom, prenom, lieu, promotion, annee);

				
				System.out.println(nom + " " + prenom + " " + lieu + " " + promotion + " " + annee);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
