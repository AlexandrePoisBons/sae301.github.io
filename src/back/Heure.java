package back;

import java.util.ArrayList;
import java.util.List;

public class Heure {
	private int         idHeure;
	private Module      module;
	private TypeHeure   typeHeure;
	private int         duree;
	private String      commentaire;

	private List<Intervenant> intervenants;
    
    public static Heure creerHeure( int idHeure,  Module module, TypeHeure typeHeure, int duree, String commentaire)
    {
		if ( module == null || typeHeure == null || duree <= 0 )
			return null;

        return new Heure(idHeure, module, typeHeure, duree, commentaire);
    }
    
    private Heure(int idHeure, Module module, TypeHeure typeHeure, int duree, String commentaire) {
		this.idHeure     = idHeure;
		this.module      = module;
		this.typeHeure   = typeHeure;
		this.duree       = duree;
		this.commentaire = commentaire;

		this.intervenants = new ArrayList<Intervenant>();
	}



	public int               getIdHeure()      { return this.idHeure;      }
	public Module            getModule()       { return this.module;       }
	public TypeHeure         getTypeHeure()    { return this.typeHeure;    }
	public int               getDuree()        { return this.duree;        }
	public String            getCommentaire()  { return this.commentaire;  }
	public List<Intervenant> getIntervenants() { return this.intervenants; }


	public void setIdHeure( int idHeure )                       { this.idHeure      = idHeure;            }
	public void setModule(Module module)                        { this.module       = module;             }
	public void setTypeHeure(TypeHeure typeHeure)               { this.typeHeure    = typeHeure;          }
	public void setDuree(int duree)                             { this.duree        = duree;              }
	public void setCommentaire(String commentaire)              { this.commentaire  = commentaire;        }
	public void setIntervenants(List<Intervenant> intervenants) { this.intervenants = intervenants;       }
	public void ajouterIntervenant(Intervenant intervenant)     { this.intervenants.add(intervenant);     }
	public void supprimerIntervenant(Intervenant intervenant)   { this.intervenants.remove(intervenant);  }

	public String toString() {
		return "Heure [idHeure="+this.idHeure + ", idModule=" + this.module.getIdModule() + ", idTypeHeure=" + this.typeHeure.getIdTypeHeure() + ", duree=" + this.duree + ", commentaire=" + this.commentaire+"]";
	}
}
