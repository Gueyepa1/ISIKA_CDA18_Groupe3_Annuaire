package fr.isika.cda18.projet1.groupe3.entites;

public class ArbreBinaire {

	private Noeud racine;

	public ArbreBinaire() {
	}

	public void ajouterNoeud(Noeud stagiaire) {
		if (racine == null) {
			racine = stagiaire;
			return ;
		}
		racine.AjouterStagiaire(stagiaire);
	}
	
	public String toString() {
//
//		String result = "";
//
//		if (this.racine.getFilsGauche() != null) {
//			result += this.racine.getFilsGauche().toString();
//		}
//		result += " " + racine;
//		if (this.racine.getFilsDroit() != null) {
//			result += this.racine.getFilsDroit().toString();
//		}
		return racine.toString();

	}

 
}
