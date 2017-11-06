package cm.compte.banquaire.modele;

public enum TypeCompte {
	EPARGNE("Compte epargne"),
	COURANT("Compte courant"),
	JOINT("Compte joint") ;

	private String name = "";

	TypeCompte(String name){
		this.name = name ;
	}

	public String toString(){
		return this.name ;
	}
};
