package cm.compte.banquaire.modele;

public enum MoyenPaiement {
	CB("Carte Banquaire"),
	CHEQUE("Cheque"),
	VIREMENT("Virement") ;

	private String val = "" ;

	MoyenPaiement(String val){
		this.val = val ;
	}

	public String toString(){
		return this.val ;
	}
}
