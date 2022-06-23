package fr.isika.cda18.projet1.groupe3.entites;

public class Noeud {

	private Stagiaire stagiaire;
	private Noeud filsGauche;
	private Noeud filsDroit;

	public Noeud(Stagiaire stagiaire) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = null;
		this.filsDroit = null;

	}

	// Getters and setters
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}

	// méthodes spécifiques

	public String toString() {

		String result = "";

		if (this.filsGauche != null) {
			result += this.filsGauche.toString();
		}
		result += " " + stagiaire;
		if (this.filsDroit != null) {
			result += this.filsDroit.toString();
		}
		return result;

	}

	public void AjouterStagiaire(Noeud noeudAAjouter) {
		if (stagiaire.getNom().compareTo(noeudAAjouter.getStagiaire().getNom()) > 0) {
			if (this.filsGauche == null) {
				this.filsGauche = noeudAAjouter;
			} else {
				this.filsGauche.AjouterStagiaire(noeudAAjouter);
			}
		} else {

			if (this.filsDroit == null) {
				this.filsDroit = noeudAAjouter;
			} else {
				this.filsDroit.AjouterStagiaire(noeudAAjouter);
			}
		}
	}
}
