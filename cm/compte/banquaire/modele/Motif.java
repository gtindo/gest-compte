package cm.compte.banquaire.modele;

public enum Motif {
	SALAIRE("Salaire"),
	LOYER("Loyer"),
	ALIMENTATION("Alimentation"),
	DIVERS("Divers"),
	SI("Situation initiale") ;

	private String val = "" ;

	Motif(String val){
		this.val = val ;
	}

	public String toString(){
		return this.val ;
	}
}
