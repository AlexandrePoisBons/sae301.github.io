package back;

public class Heure {
    private Module module;
    private Intervenant intervenant;
    private TypeHeure typeHeure;
    private int nbHeures;

    public Heure(Module module, Intervenant intervenant, TypeHeure typeHeure, int nbHeures) {
        this.module = module;
        this.intervenant = intervenant;
        this.typeHeure = typeHeure;
        this.nbHeures = nbHeures;
    }
    

    public Module getModule()           { return module;      }
    public Intervenant getIntervenant() { return intervenant; }
    public TypeHeure getTypeHeure()     { return typeHeure;   }
    public int getNbHeures()            { return nbHeures;    }


    public void setModule(Module module)                { this.module = module;           }
    public void setIntervenant(Intervenant intervenant) { this.intervenant = intervenant; }
    public void setTypeHeure(TypeHeure typeHeure)       { this.typeHeure = typeHeure;     }
    public void setNbHeures(int nbHeures)               { this.nbHeures = nbHeures;       }

}
