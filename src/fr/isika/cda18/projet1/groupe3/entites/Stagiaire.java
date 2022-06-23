package fr.isika.cda18.projet1.groupe3.entites;

public class Stagiaire {

	// attributs

	private String nom;
	private String prenom;
	private String lieu;
	private String promotion;
	private String annee;

	public static final int TAILLE_NOM = 30;
	public static final int TAILLE_PRENOM = 30;
	public static final int TAILLE_LIEU = 3;
	public static final int TAILLE_PROMOTION = 15;
	public static final int TAILLE_ANNEE = 4;
	public static final int TAILLE_OBJET_OCTET = 162;

	public Stagiaire(String nom, String prenom, String lieu, String promotion, String annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.lieu = lieu;
		this.promotion = promotion;
		this.annee = annee;

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

	public String AugmenterNom() {
		String nomLong = "", prenomLong = "", lieuLong = "", promotionLong = "", anneeLong = "";
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
	public String AugmenterPrenom() {
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
	public String AugmenterLieu() {
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
	public String AugmenterPromotion() {
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
	public String AugmenterAnnee() {
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
		return nom + " " + prenom + " " + lieu + " " + promotion+ " " + annee+ " ";
	}
	

}
