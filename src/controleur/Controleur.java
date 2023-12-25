package controleur;

public class Controleur {

	private ControleurMetier metier;
	private ControleurIHM    ihm;
	private Infos            infos;

	public static void main(String[] args) { new Controleur(); }

	public Controleur() {

		this.infos = new Infos();

		System.out.println(infos.getDatabase()+"="+infos.getLogin()+":"+infos.getPassword());

		this.metier = new ControleurMetier(this);
		this.ihm    = new ControleurIHM(this);

	}

	public ControleurMetier metier() { return this.metier;}

}
