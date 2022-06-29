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
			if (raf.length() == 0) {
				return false;
			}
			Noeud noeud = ArbreBinaire.lireNoeud(raf);
			if (noeudARechercher.getStagiaire().getNom().equals(noeud.getStagiaire().getNom())) {
				return true;
			}
			if (noeud.getStagiaire().getNom().compareTo(noeudARechercher.getStagiaire().getNom()) > 0) {
				if (noeud.filsGauche < 0) {
					return false;
				} else {
					raf.seek(TAILLE_NOEUD * noeud.filsGauche);
					return rechercheNoeud(raf, noeudARechercher);
				}
			} else {
				if (noeud.filsDroit < 0) {
					return false;
				} else {
					raf.seek(TAILLE_NOEUD * noeud.filsDroit);
					return rechercheNoeud(raf, noeudARechercher);
				}
			}
		} catch (IOException e) {
		}
		return false;
	}

	public static Noeud noeudSuccesseur(RandomAccessFile raf) {
		try {
			long position = raf.getFilePointer();
			Noeud noeud = ArbreBinaire.lireNoeud(raf);
			raf.seek(TAILLE_NOEUD * noeud.filsDroit);
			noeud = ArbreBinaire.lireNoeud(raf);
			while (noeud.filsGauche > 0) {
				raf.seek(TAILLE_NOEUD * noeud.filsGauche);
				noeud = ArbreBinaire.lireNoeud(raf);
			}
			raf.seek(position);
			return noeud;
		} catch (Exception e) {
		}
		return null;
	}

	public static int supprimerRacine(RandomAccessFile raf) {
		try {
			long position = raf.getFilePointer();
			Noeud noeud = ArbreBinaire.lireNoeud(raf);

			if (noeud.filsGauche < 0 && noeud.filsDroit < 0) {
				return -1;
			} else if (noeud.filsDroit < 0) {
				return noeud.filsGauche;
			} else if (noeud.filsGauche < 0) {
				return noeud.filsDroit;
			} else {
				raf.seek(position);
				Noeud successeur = noeudSuccesseur(raf);
				raf.seek(position);
				noeud.stagiaire = successeur.stagiaire;
				ArbreBinaire.ecrire(raf, noeud);
				raf.seek(TAILLE_NOEUD * noeud.filsDroit);
				supprimerNoeud(raf, successeur.stagiaire);
				return (int) (position / TAILLE_NOEUD);
			}
		} catch (Exception e) {
		}
		return -1;

	}

	public static int supprimerNoeud(RandomAccessFile raf, Stagiaire stagiaireASupprimer) {
		try {
			long position = raf.getFilePointer();
			Noeud noeud = ArbreBinaire.lireNoeud(raf);
			if (noeud.getStagiaire().getNom().equals(stagiaireASupprimer.getNom())
					&& noeud.getStagiaire().getPrenom().equals(stagiaireASupprimer.getPrenom())
					&& noeud.getStagiaire().getPromotion().equals(stagiaireASupprimer.getPromotion())) {
				raf.seek(position);
				return Noeud.supprimerRacine(raf);
			} else if (noeud.getStagiaire().getNom().compareTo(stagiaireASupprimer.getNom()) > 0) {
				if (noeud.filsGauche > 0) {
					raf.seek(TAILLE_NOEUD * noeud.filsGauche);
					noeud.filsGauche = supprimerNoeud(raf, stagiaireASupprimer);
					raf.seek(position);
					ArbreBinaire.ecrire(raf, noeud);
				} else {
					return (int) (position / TAILLE_NOEUD);
				}
			} else {
				if (noeud.filsDroit > 0) {
					raf.seek(TAILLE_NOEUD * noeud.filsDroit);
					noeud.filsDroit = supprimerNoeud(raf, stagiaireASupprimer);
					raf.seek(position);
					ArbreBinaire.ecrire(raf, noeud);
				} else {
					return (int) (position / TAILLE_NOEUD);
				}
			}
		} catch (Exception e) {
		}
		return -1;

	}
}
