package controleur;



public class Controleur {

	private ControleurMetier metier;
	private ControleurIHM ihm;

	public static void main(String[] args) { new Controleur(); }

	public Controleur() {
		this.metier = new ControleurMetier(this);
		this.ihm    = new ControleurIHM(this);
	}

	public ControleurMetier metier() { return this.metier; }

}
