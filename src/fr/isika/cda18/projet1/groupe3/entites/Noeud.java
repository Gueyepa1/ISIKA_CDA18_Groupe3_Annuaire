package fr.isika.cda18.projet1.groupe3.entites;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Noeud {

	private static final long TAILLE_NOEUD = 124;
	private Stagiaire stagiaire;
	private int filsGauche;
	private int filsDroit;

	public Noeud(Stagiaire stagiaire) {
		super();
		this.stagiaire = stagiaire;
		filsGauche = -1;
		filsDroit = -1;

	}

	public Noeud(Stagiaire stagiaire, int filsGauche, int filsDroit) {
		super();
		this.stagiaire = stagiaire;
		this.filsGauche = filsGauche;
		this.filsDroit = filsDroit;
	}

	// Getters and setters
	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	// méthodes spécifiques
	

	public void ajouterStagiaire(RandomAccessFile raf, Noeud noeudAAjouter) {
		try {
			if (raf.length() == 0) {
				ArbreBinaire.ecrire(raf, noeudAAjouter);
				return;
			}
			Noeud noeud = ArbreBinaire.lireNoeud(raf);
			if (noeud.getStagiaire().getNom().compareTo(noeudAAjouter.getStagiaire().getNom()) > 0) {
				if (noeud.filsGauche < 0) {
					raf.seek(raf.getFilePointer() - 8);
					raf.writeInt((int) (raf.length() / TAILLE_NOEUD));
					raf.seek(raf.length());
					ArbreBinaire.ecrire(raf, noeudAAjouter);
				} else {
					raf.seek(TAILLE_NOEUD * noeud.filsGauche);
					ajouterStagiaire(raf, noeudAAjouter);
				}
			} else {
				if (noeud.filsDroit < 0) {
					raf.seek(raf.getFilePointer() - 4);
					raf.writeInt((int) (raf.length() / TAILLE_NOEUD));
					raf.seek(raf.length());
					ArbreBinaire.ecrire(raf, noeudAAjouter);
				} else {
					raf.seek(TAILLE_NOEUD * noeud.filsDroit);
					ajouterStagiaire(raf, noeudAAjouter);
				}
			}
		} catch (IOException e) {
		}
	}

	public static String toString(RandomAccessFile raf) {
		try {
			String result = "";
			Noeud noeud = ArbreBinaire.lireNoeud(raf);
			if (noeud.filsGauche > 0) {
				raf.seek(noeud.filsGauche * TAILLE_NOEUD);
				result += toString(raf);
			}
			result += " " + noeud.getStagiaire();
			if (noeud.filsDroit > 0) {
				raf.seek(noeud.filsDroit * TAILLE_NOEUD);
				result += toString(raf);
			}
			return result;
		} catch (IOException e) {

		}
		return null;
	}

	public static boolean rechercheNoeud(RandomAccessFile raf, Noeud noeudARechercher) {
		try {
			if (raf.length()==0) {
			return false;
			}
			 Noeud noeud = ArbreBinaire.lireNoeud(raf);
			 if (noeudARechercher.getStagiaire().getNom().equals(noeud.getStagiaire().getNom())) {
					return true;
				}
			if (noeud.getStagiaire().getNom().compareTo(noeudARechercher.getStagiaire().getNom()) > 0) {
				if (noeud.filsGauche < 0) {
					return false;
				}else {
					raf.seek(TAILLE_NOEUD * noeud.filsGauche);
					return rechercheNoeud(raf, noeudARechercher);
				}
			}else {
				if(noeud.filsDroit < 0) {
					return false;
				}else {
					raf.seek(TAILLE_NOEUD * noeud.filsDroit);
					return rechercheNoeud(raf, noeudARechercher);
				}
			}
		} catch (IOException e) {
		}
		return false;
	}
}
