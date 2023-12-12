package back;

public class Heure {
    private int         idHeure;
    private Module      module;
    private Intervenant intervenant;
    private TypeHeure   typeHeure;
    private int         nbHeures;

    public Heure(int idHeure, Module module, Intervenant intervenant, TypeHeure typeHeure, int nbHeures) {
        this.idHeure     = idHeure;
        this.module      = module;
        this.intervenant = intervenant;
        this.typeHeure   = typeHeure;
        this.nbHeures    = nbHeures;
    }

    public int getIdHeure()             { return this.idHeure;     }
    public Module getModule()           { return this.module;      }
    public Intervenant getIntervenant() { return this.intervenant; }
    public TypeHeure getTypeHeure()     { return this.typeHeure;   }
    public int getNbHeures()            { return this.nbHeures;    }

    public void setIdHeure( int idHeure )               { this.idHeure     = idHeure;     }
    public void setModule(Module module)                { this.module      = module;      }
    public void setIntervenant(Intervenant intervenant) { this.intervenant = intervenant; }
    public void setTypeHeure(TypeHeure typeHeure)       { this.typeHeure   = typeHeure;   }
    public void setNbHeures(int nbHeures)               { this.nbHeures    = nbHeures;    }
}
