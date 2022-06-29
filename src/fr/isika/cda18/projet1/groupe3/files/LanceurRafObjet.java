package fr.isika.cda18.projet1.groupe3.files;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import fr.isika.cda18.projet1.groupe3.entites.*;


public class LanceurRafObjet {

	public static void main(String[] args) {
		ArbreBinaire arbre = new ArbreBinaire(); 
		List<Stagiaire> stagiaires = new ArrayList<>();

//		List<Noeud> noeuds = new ArrayList<>();
		
		try {
			stagiaires = Stagiaire.initialiser();
			RandomAccessFile raf = new RandomAccessFile("src/Donnees/ListeStagiaires.bin", "rw");
			raf.setLength(0);
			//pour chaque stagiaire contenue dans la liste stagiaires
			for(Stagiaire stage : stagiaires) {
				//puis à la suite j'écris le nomLong du stagiaire
//				raf.writeChars(stage.AugmenterNom());
//				raf.writeChars(stage.AugmenterPrenom());
//				raf.writeChars(stage.AugmenterLieu());
//				raf.writeChars(stage.AugmenterPromotion());
//				raf.writeChars(stage.AugmenterAnnee());
				Noeud noeud = new Noeud(stage);
				noeud.ajouterStagiaire(raf, noeud);
				raf.seek(0);
			}
		
			raf.seek(0);
			for(int j = 0; j < raf.length()/Stagiaire.TAILLE_OBJET_OCTET; j++) {
				Noeud noeud = ArbreBinaire.lireNoeud(raf);
//				System.out.println(noeud);
		
			}
			raf.seek(0);
//			Noeud.toString(raf);
			System.out.println(Noeud.toString(raf));
			Stagiaire stagiaire = new Stagiaire("AUGEREAU", "", "", "", "");
//			stagiaire.setNom(stagiaire.AugmenterNom());
			Noeud noeud = new Noeud(stagiaire);
			
			//se placer à la position 0:
			raf.seek(0);
//			Noeud.rechercheNoeud(raf, noeud);
			System.out.println(Noeud.rechercheNoeud(raf, noeud));
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
