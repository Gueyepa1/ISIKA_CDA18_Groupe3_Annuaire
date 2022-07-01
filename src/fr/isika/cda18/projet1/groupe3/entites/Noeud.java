package fr.isika.cda18.projet1.groupe3.entites;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

	// m√©thodes sp√©cifiques

	public static void ajouterStagiaire(RandomAccessFile raf, Noeud noeudAAjouter) {
		try {
			if (raf.length() == 0) {
				ecrireNoeud(raf, noeudAAjouter);
				return;
			}
			Noeud noeud = lireNoeud(raf);
			if(noeud.getStagiaire().getNom().equals(noeudAAjouter.getStagiaire().getNom()) &&
					noeud.getStagiaire().getPromotion().equals(noeudAAjouter.getStagiaire().getPromotion()) &&
					noeud.getStagiaire().getPrenom().equals(noeudAAjouter.getStagiaire().getPrenom())) {
					//Doublon dÈtectÈ, on s'arrÍte
					return;
				}
			if (noeud.getStagiaire().getNom().compareTo(noeudAAjouter.getStagiaire().getNom()) > 0) {
				if (noeud.filsGauche < 0) {
					raf.seek(raf.getFilePointer() - 8);
					raf.writeInt((int) (raf.length() / TAILLE_NOEUD));
					raf.seek(raf.length());
					ecrireNoeud(raf, noeudAAjouter);
				} else {
					raf.seek(TAILLE_NOEUD * noeud.filsGauche);
					ajouterStagiaire(raf, noeudAAjouter);
				}
			} else {
				if (noeud.filsDroit < 0) {
					raf.seek(raf.getFilePointer() - 4);
					raf.writeInt((int) (raf.length() / TAILLE_NOEUD));
					raf.seek(raf.length());
					ecrireNoeud(raf, noeudAAjouter);
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
			Noeud noeud = lireNoeud(raf);
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
			Noeud noeud = lireNoeud(raf);
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
			Noeud noeud = lireNoeud(raf);
			raf.seek(TAILLE_NOEUD * noeud.filsDroit);
			noeud = lireNoeud(raf);
			while (noeud.filsGauche > 0) {
				raf.seek(TAILLE_NOEUD * noeud.filsGauche);
				noeud = lireNoeud(raf);
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
			Noeud noeud = lireNoeud(raf);

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
				ecrireNoeud(raf, noeud);
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
			Noeud noeud = lireNoeud(raf);
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
					ecrireNoeud(raf, noeud);
				} else {
					return (int) (position / TAILLE_NOEUD);
				}
			} else {
				if (noeud.filsDroit > 0) {
					raf.seek(TAILLE_NOEUD * noeud.filsDroit);
					noeud.filsDroit = supprimerNoeud(raf, stagiaireASupprimer);
					raf.seek(position);
					ecrireNoeud(raf, noeud);
				} else {
					return (int) (position / TAILLE_NOEUD);
				}
			}
		} catch (Exception e) {
		}
		return -1;

	}

	public static ObservableList<Stagiaire> rechercheMulticritere(RandomAccessFile raf, Stagiaire criteres) {
		try {
			ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();
			Noeud noeud = lireNoeud(raf);
			if (noeud.filsGauche > 0) {
				raf.seek(TAILLE_NOEUD * noeud.filsGauche);
				stagiaires.addAll(rechercheMulticritere(raf, criteres));
			}

			if ((criteres.getNom().isBlank() || noeud.getStagiaire().getNom().trim().equals(criteres.getNom().trim()))
					&& (criteres.getPrenom().isBlank()
							|| noeud.getStagiaire().getPrenom().trim().equals(criteres.getPrenom().trim()))
					&& (criteres.getLieu().isBlank()
							|| noeud.getStagiaire().getLieu().trim().equals(criteres.getLieu().trim()))
					&& (criteres.getPromotion().isBlank()
							|| noeud.getStagiaire().getPromotion().trim().equals(criteres.getPromotion().trim()))
					&& (criteres.getAnnee().isBlank()
							|| noeud.getStagiaire().getAnnee().trim().equals(criteres.getAnnee().trim()))) {
				stagiaires.add(noeud.getStagiaire());
			}
			if (noeud.filsDroit > 0) {
				raf.seek(TAILLE_NOEUD * noeud.filsDroit);
				stagiaires.addAll(rechercheMulticritere(raf, criteres));
			}
			return stagiaires;
		} catch (IOException e) {

		}
		return null;
	}

	public static Noeud lireNoeud(RandomAccessFile raf) {

		try {
			String nom = "";
			String prenom = "";
			String lieu = "";
			String promotion = "";
			String annee = "";
			int filsGauche = -1;
			int filsDroit = -1;
			// je lis les x caract√®res de l'attribut String
			for (int i = 0; i < Stagiaire.TAILLE_NOM; i++) {
				nom += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_PRENOM; i++) {
				prenom += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_LIEU; i++) {
				lieu += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_PROMOTION; i++) {
				promotion += raf.readChar();
			}

			for (int i = 0; i < Stagiaire.TAILLE_ANNEE; i++) {
				annee += raf.readChar();
			}
			filsGauche = raf.readInt();
			filsDroit = raf.readInt();
			Noeud noeud = new Noeud(new Stagiaire(nom, prenom, lieu, promotion, annee), filsGauche, filsDroit);
			return noeud;
		} catch (IOException e) {
		}
		return null;
	}

	public static void ecrireNoeud(RandomAccessFile raf, Noeud noeudAAjouter) {
		try {
			raf.writeChars(noeudAAjouter.getStagiaire().getNom());
			raf.writeChars(noeudAAjouter.getStagiaire().getPrenom());
			raf.writeChars(noeudAAjouter.getStagiaire().getLieu());
			raf.writeChars(noeudAAjouter.getStagiaire().getPromotion());
			raf.writeChars(noeudAAjouter.getStagiaire().getAnnee());
			raf.writeInt(noeudAAjouter.getFilsGauche());
			raf.writeInt(noeudAAjouter.getFilsDroit());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void donneesVersRaf() {
		File file = new File("src/Donnees/STAGIAIRES.DON");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			RandomAccessFile raf = new RandomAccessFile("src/Donnees/ListeStagiaires.bin", "rw");
			Stagiaire stagiaire;

			String nom = "";
			String prenom = "";
			String lieu = "";
			String promotion = "";
			String annee = "";

			// tant qu'il y a quelque chose √† lire, lit la ligne
			while (br.ready()) {
				nom = br.readLine();
				prenom = br.readLine();
				lieu = br.readLine();
				promotion = br.readLine();
				annee = br.readLine();
				br.readLine();
				stagiaire = new Stagiaire(nom, prenom, lieu, promotion, annee);
				Noeud noeud = new Noeud(stagiaire);
				raf.seek(0);
				Noeud.ajouterStagiaire(raf, noeud);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ObservableList<Stagiaire> lireListeDepuisFichier(RandomAccessFile raf){
		try {
			ObservableList<Stagiaire> stagiaires = FXCollections.observableArrayList();
			Noeud noeud = lireNoeud(raf);
			if (noeud.filsGauche > 0) {
				raf.seek(noeud.filsGauche * TAILLE_NOEUD);
				stagiaires.addAll(lireListeDepuisFichier(raf));
			}
			stagiaires.add(noeud.getStagiaire());
			if (noeud.filsDroit > 0) {
				raf.seek(noeud.filsDroit * TAILLE_NOEUD);
				stagiaires.addAll(lireListeDepuisFichier(raf));
			}
			return stagiaires;
		} catch (IOException e) {

		}
		return null;
	}
}
