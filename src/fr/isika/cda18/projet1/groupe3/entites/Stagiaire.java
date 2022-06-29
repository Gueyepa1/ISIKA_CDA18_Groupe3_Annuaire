package fr.isika.cda18.projet1.groupe3.entites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Stagiaire {

	// attributs


	private String nom;
	private String prenom;
	private String lieu;
	private String promotion;
	private String annee;

	public static final int TAILLE_NOM = 21;
	public static final int TAILLE_PRENOM = 20;
	public static final int TAILLE_LIEU = 2;
	public static final int TAILLE_PROMOTION = 11;
	public static final int TAILLE_ANNEE = 4;
	public static final int TAILLE_OBJET_OCTET = 116;

	public Stagiaire(String nom, String prenom, String lieu, String promotion, String annee) {
		super();
		this.nom = nom;
		this.nom = augmenterNom();
		this.prenom = prenom;
		this.prenom = augmenterPrenom();
		this.lieu = lieu;
		this.lieu = augmenterLieu();
		this.promotion = promotion;
		this.promotion = augmenterPromotion();
		this.annee = annee;
		this.annee = augmenterAnnee();

	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getLieu() {
		return lieu;
	}

	public String getPromotion() {
		return promotion;
	}

	public String getAnnee() {
		return annee;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}
	
	public String augmenterNom() {
		String nomLong = "";
		if (nom.length() <= TAILLE_NOM) {
			nomLong = this.nom;
			for (int i = nom.length(); i < TAILLE_NOM; i++) {
				nomLong += " ";
			}
		} else {
			// substring méthode de la classe string qui permet
			// de ne prendre que les lettre situées entre un index début et un index fin
			nomLong = this.nom.substring(0, TAILLE_NOM);
		}
		return nomLong;
	}
	public String augmenterPrenom() {
		String prenomLong = "";
		if (prenom.length() <= TAILLE_PRENOM) {
			prenomLong = this.prenom;
			for (int i = prenom.length(); i < TAILLE_PRENOM; i++) {
				prenomLong += " ";
			}
		} else {
			// substring méthode de la classe string qui permet
			// de ne prendre que les lettre situées entre un index début et un index fin
			prenomLong = this.prenom.substring(0, TAILLE_PRENOM);
		}
		return prenomLong;
	}
	public String augmenterLieu() {
		String lieuLong = "";
		if (lieu.length() <= TAILLE_LIEU) {
			lieuLong = this.lieu;
			for (int i = lieu.length(); i < TAILLE_LIEU; i++) {
				lieuLong += " ";
			}
		} else {
			// substring méthode de la classe string qui permet
			// de ne prendre que les lettre situées entre un index début et un index fin
			lieuLong = this.lieu.substring(0, TAILLE_LIEU);
		}
		return lieuLong;
	}
	public String augmenterPromotion() {
		String promotionLong = "";
		if (promotion.length() <= TAILLE_PROMOTION) {
			promotionLong = this.promotion;
			for (int i = promotion.length(); i < TAILLE_PROMOTION; i++) {
				promotionLong += " ";
			}
		} else {
			// substring méthode de la classe string qui permet
			// de ne prendre que les lettre situées entre un index début et un index fin
			promotionLong = this.promotion.substring(0, TAILLE_PROMOTION);
		}
		return promotionLong;
	}
	public String augmenterAnnee() {
		String anneeLong = "";
		if (annee.length() <= TAILLE_ANNEE) {
			anneeLong = this.annee;
			for (int i = annee.length(); i < TAILLE_ANNEE; i++) {
				anneeLong += " ";
			}
		} else {
			// substring méthode de la classe string qui permet
			// de ne prendre que les lettre situées entre un index début et un index fin
			anneeLong = this.annee.substring(0, TAILLE_ANNEE);
		}
		return anneeLong;
	}

		@Override
	public String toString() {
		return nom + " " + prenom + " " + lieu + " " + promotion+ " " + annee + " ";
	}
	private ArrayList<Stagiaire> stagiaires = new ArrayList<>();

	public ArrayList<Stagiaire> getStagiaires(){
		return stagiaires;
	}

	public static List<Stagiaire> initialiser() {
		List<Stagiaire> stagiaires = new ArrayList<>();
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

				stagiaires.add(stagiaire);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stagiaires;
	}

}
