package cm.compte.banquaire.modele;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty ;
import javafx.beans.property.ObjectProperty ;
import javafx.beans.property.SimpleObjectProperty ;

@XmlRootElement(name = "Operation")
public class Operation {

	private final StringProperty numeroCompteOperation ;
	private final ObjectProperty<Double> montant ;
	private final ObjectProperty<Motif> motifOperation ;
	private final ObjectProperty<MoyenPaiement> moyenDePaiement ;
	private final StringProperty typeOperation ;
	private final StringProperty date ;

	public Operation(){
		this(null, 0.0, null, null, null, null) ;
	}

	public Operation(String numeroCompte, double montant, Motif motifOperation, MoyenPaiement moyenDePaiement, String typeOperation, String date){
		this.numeroCompteOperation = new SimpleStringProperty(numeroCompte) ;
		this.montant = new SimpleObjectProperty<Double>(montant) ;
		this.motifOperation = new SimpleObjectProperty<Motif>(motifOperation) ;
		this.moyenDePaiement = new SimpleObjectProperty<MoyenPaiement>(moyenDePaiement) ;
		this.typeOperation = new SimpleStringProperty(typeOperation) ;
		this.date = new SimpleStringProperty(date) ;
	}

	@XmlElement(name = "NumeroCompteOperation")
	public String getNumeroCompte(){
		return this.numeroCompteOperation.get() ;
	}

	public StringProperty getNumeroCompteProperty(){
		return this.numeroCompteOperation ;
	}

	public void setNumeroCompte(String numeroCompte){
		this.numeroCompteOperation.set(numeroCompte) ;
	}

	@XmlElement(name = "Montant")
	public double getMontant(){
		return this.montant.get() ;
	}

	public ObjectProperty<Double> getMontantProperty(){
		return this.montant ;
	}

	public void setMontant(double montant){
		this.montant.set(montant) ;
	}

	@XmlElement(name = "Motif")
	public Motif getMotifOperation(){
		return this.motifOperation.get() ;
	}

	public ObjectProperty<Motif> getMotifOperationProperty(){
		return this.motifOperation ;
	}

	public void setMotifOperation(Motif motifOperation){
		this.motifOperation.set(motifOperation) ;
	}

	@XmlElement(name = "MoyenPaiement")
	public MoyenPaiement getMoyenDePaiement(){
		return this.moyenDePaiement.get() ;
	}

	public ObjectProperty<MoyenPaiement> getMoyenDePaiementProperty(){
		return this.moyenDePaiement ;
	}

	public void setMoyenDePaiement(MoyenPaiement moyenDePaiement){
		this.moyenDePaiement.set(moyenDePaiement) ;
	}

	public void setTypeOperation(String typeOperation){
		this.typeOperation.set(typeOperation) ;
	}

	@XmlElement(name = "TypeOperation")
	public String getTypeOperation(){
		return this.typeOperation.get() ;
	}

	public StringProperty getTypeOperationProperty(){
		return this.typeOperation ;
	}

	@XmlElement(name = "Date")
	public String getDateOperation(){
		return this.date.get() ;
	}

	public void setDateOperation(String date){
		this.date.set(date);
	}

	public StringProperty getDateOperationProperty(){
		return this.date ;
	}
}
