package metier;

public class TypeModule {
	private int id;
	private String libelle;
	
	public TypeModule(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public int    getId()      { return this.id;      }
	public String getLibelle() { return this.libelle; }
	
	public void setId(int id)              { this.id = id;           }
	public void setLibelle(String libelle) { this.libelle = libelle; }
	

	public String toString() {
		return "TypeModule [id=" + id + ", libelle=" + libelle + "]";
	}
}
