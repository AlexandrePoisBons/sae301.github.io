package metier;

import java.util.ArrayList;
import java.util.List;

import metier.db.Requetes;

/**
 * Classe Heure : représente une heure de cours avec ses intervenant et son module lié
 * @author Alexandre Pois--Bons - Florian Janot
 * @version 1.0
 */
public class Heure implements Comparable<Heure> {
	private static int  nbHeures = Requetes.getNbHeures();

	private int         idHeure;
	private Module      module;
	private TypeHeure   typeHeure;
	private int         nbSemaines;
	private int         nbGpnbH;
	private float       duree;
	private String      commentaire;

	private List<Intervenant> intervenants;

	/**
	 * Factory pour la classe Heure
	 * @param module      Un module préalablement crée
	 * @param typeHeure   Un type d'heure préalablement crée
	 * @param duree       La durée de l'heure (en heure)
	 * @param commentaire Un commentaire sur l'heure saisie par l'utilisateur
	 * @return une heure ou null
	 */
	public static Heure creerHeure( Module module, TypeHeure typeHeure, int nbSemaines, int nbGpnbH, float duree, String commentaire)
	{
		if ( module == null || typeHeure == null || duree < 0 ){
			System.out.println("il est nulllllllllllllllllllll");
			return null;
		}

		return new Heure( module, typeHeure, nbSemaines, nbGpnbH, duree, commentaire);
	}

	public static Heure initHeure( int idHeure, Module module, TypeHeure typeHeure, int nbSemaines, int nbGpnbH, float duree, String commentaire)
	{
		if ( idHeure < 0 || module == null || typeHeure == null || duree <= 0 )
			return null;

		return new Heure( idHeure, module, typeHeure, nbSemaines, nbGpnbH, duree, commentaire);
	}

	/**
	 * Constructeur privé de la classe Heure uniquement appellé par la factory
	 * @param module      Un module préalablement crée
	 * @param typeHeure   Un type d'heure préalablement crée
	 * @param duree       La durée de l'heure (en heure)
	 * @param commentaire Un commentaire sur l'heure saisie par l'utilisateur
	 * @return            Une heure validé par la factory
	 */
	private Heure( Module module, TypeHeure typeHeure, int nbSemaines, int nbGpnbH, float duree, String commentaire) {
		this.idHeure     = ++nbHeures;
		this.module      = module;
		this.typeHeure   = typeHeure;
		this.nbSemaines  = nbSemaines;
		this.nbGpnbH     = nbGpnbH;
		this.duree       = duree;
		this.commentaire = commentaire;

		this.intervenants = new ArrayList<Intervenant>();
	}

	private Heure (int idHeure, Module module, TypeHeure typeHeure, int nbSemaines, int nbGpnbH, float duree, String commentaire) {
		this.idHeure     = idHeure;
		this.module      = module;
		this.typeHeure   = typeHeure;
		this.nbSemaines  = nbSemaines;
		this.nbGpnbH     = nbGpnbH;
		this.duree       = duree;
		this.commentaire = commentaire;

		this.intervenants = new ArrayList<Intervenant>();
	}

	// Getters
	public int               getIdHeure()      { return this.idHeure;      }
	public Module            getModule()       { return this.module;       }
	public TypeHeure         getTypeHeure()    { return this.typeHeure;    }
	public int               getNbSemaines()   { return this.nbSemaines;   }
	public int               getNbGpNbH()      { return this.nbGpnbH;      }
	public float             getDuree()        { return this.duree;        }
	public String            getCommentaire()  { return this.commentaire;  }
	public List<Intervenant> getIntervenants() { return this.intervenants; }

	// Setters
	public void setIdHeure( int idHeure )                       { this.idHeure      = idHeure;            }
	public void setModule(Module module)                        { this.module       = module;             }
	public void setTypeHeure(TypeHeure typeHeure)               { this.typeHeure    = typeHeure;          }
	public void setNbSemaines(int nbSemaines)                   { this.nbSemaines   = nbSemaines;         }
	public void setNbGpNbH(int nbGpnbH)                         { this.nbGpnbH      = nbGpnbH;            }
	public void setDuree(float duree)                           { this.duree        = duree;              }
	public void setCommentaire(String commentaire)              { this.commentaire  = commentaire;        }
	public void setIntervenants(List<Intervenant> intervenants) { this.intervenants = intervenants;       }
	public void ajouterIntervenant(Intervenant intervenant)     { this.intervenants.add(intervenant);     }
	public void supprimerIntervenant(Intervenant intervenant)   { this.intervenants.remove(intervenant);  }


	/**
	 * Méthode toString de la classe Heure
	 * @return une chaine de caractère représentant l'heure
	 */
	public String toString() {
		return "Heure [idHeure="+this.idHeure + ", idModule=" + this.module.getIdModule() + ", idTypeHeure=" + this.typeHeure.getIdTypeHeure() + ", duree=" + this.duree + ", commentaire=" + this.commentaire+", intervenants="+this.intervenants+"]";
	}


	public int compareTo(Heure h) {
		return ((Integer)this.idHeure).compareTo((Integer)h.getIdHeure());
	}

	
}