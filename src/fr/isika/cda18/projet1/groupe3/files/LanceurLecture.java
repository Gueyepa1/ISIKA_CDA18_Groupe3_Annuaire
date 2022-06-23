package fr.isika.cda18.projet1.groupe3.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import fr.isika.cda18.projet1.groupe3.entites.ArbreBinaire;
import fr.isika.cda18.projet1.groupe3.entites.Noeud;
import fr.isika.cda18.projet1.groupe3.entites.Stagiaire;

public class LanceurLecture {

	public static void main(String[] args) {
try {
			
			//ouvre un flux du fichier vers le programme
			FileReader fr = new FileReader("src/Projet1/STAGIAIRES.DON");
			BufferedReader br = new BufferedReader(fr);
			
			
			ArbreBinaire stagiaires = new ArbreBinaire();
			Stagiaire stagiaire; 
			
			String nom = "";
			String prenom = "";
			String lieu = "";
			String promotion = "";
			String annee = "";
			
			
			//tant qu'il y a quelque chose à lire, lit la ligne
			while(br.ready()) {
				nom = br.readLine();
				prenom = br.readLine();
				lieu = br.readLine();
				promotion = br.readLine();
				annee = br.readLine();
				br.readLine();
				
				stagiaire= new Stagiaire(nom, prenom, lieu, promotion, annee);
				stagiaires.ajouterNoeud(new Noeud(stagiaire));
				
//				System.out.println(nom + " " +prenom + " " +lieu+ " " + promotion+ " " + annee);
			}
			System.out.println(stagiaires);
					
			//fermeture des flux
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
