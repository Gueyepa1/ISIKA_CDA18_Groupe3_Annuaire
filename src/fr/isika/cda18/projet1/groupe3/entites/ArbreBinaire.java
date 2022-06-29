package fr.isika.cda18.projet1.groupe3.entites;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ArbreBinaire {
	
	public ArbreBinaire() {
	
	}
		
	public static Noeud lireNoeud(RandomAccessFile raf) {
	
		try {
			String nom ="";
			String prenom ="";
			String lieu ="";
			String promotion ="";
			String annee ="";
			int filsGauche = -1;
			int filsDroit = -1;
		//je lis les x caractères de l'attribut String
		for(int i =0 ; i < Stagiaire.TAILLE_NOM; i++) {
			nom += raf.readChar();
		}
		
		for(int i =0 ; i < Stagiaire.TAILLE_PRENOM; i++) {
			prenom += raf.readChar();
		}
		
		for(int i =0 ; i < Stagiaire.TAILLE_LIEU; i++) {
			lieu += raf.readChar();
		}
		
		for(int i =0 ; i < Stagiaire.TAILLE_PROMOTION; i++) {
			promotion += raf.readChar();
		}
		
		for(int i =0 ; i < Stagiaire.TAILLE_ANNEE; i++) {
			annee += raf.readChar();
		}
		filsGauche = raf.readInt();
		filsDroit = raf.readInt();
		Noeud noeud = new Noeud(new Stagiaire(nom, prenom, lieu, promotion, annee), filsGauche, filsDroit);
		return noeud;
		}catch (IOException e) {
		}
		return null;
	}
	
	
	public static void ecrire(RandomAccessFile raf, Noeud noeudAAjouter) {

		try {
				raf.writeChars(noeudAAjouter.getStagiaire().getNom());
				raf.writeChars(noeudAAjouter.getStagiaire().getPrenom());
				raf.writeChars(noeudAAjouter.getStagiaire().getLieu());
				raf.writeChars(noeudAAjouter.getStagiaire().getPromotion());
				raf.writeChars(noeudAAjouter.getStagiaire().getAnnee());
				raf.writeInt(noeudAAjouter.getFilsGauche());
				raf.writeInt(noeudAAjouter.getFilsDroit());
//			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
