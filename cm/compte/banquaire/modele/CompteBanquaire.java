package cm.compte.banquaire.modele;

import javafx.beans.property.StringProperty ;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@XmlRootElement(name = "Compte")
public class CompteBanquaire {

	private final StringProperty numeroCompte ;

	private final StringProperty motDePasse ;

	private final ObjectProperty<TypeCompte> typeDeCompte ;

	private final ObjectProperty<ArrayList<Operation>> ligneComptable ;

	private final FloatProperty taux ;

	public CompteBanquaire(){
		this(null, null, null, 0, null) ;
	}

	public CompteBanquaire(String numeroCompte, TypeCompte typeDeCompte, ArrayList<Operation> ligneComptable, float taux, String passe){
		this.numeroCompte = new SimpleStringProperty(numeroCompte) ;
		this.typeDeCompte = new SimpleObjectProperty<TypeCompte>(typeDeCompte) ;
		this.ligneComptable = new SimpleObjectProperty<ArrayList<Operation>>(ligneComptable) ;
		this.taux = new SimpleFloatProperty(taux) ;
		this.motDePasse = new SimpleStringProperty(passe) ;
	}

	public CompteBanquaire(CompteBanquaire compte){
		this.numeroCompte = compte.numeroCompte ;
		this.typeDeCompte = compte.typeDeCompte ;
		this.ligneComptable = compte.ligneComptable ;
		this.taux = compte.taux ;
		this.motDePasse = compte.motDePasse ;
	}

	@XmlElement(name = "MotDePasse")
	public String getMotDePasse(){
		return motDePasse.get();
	}

	public void setMotDePasse(String motDePasse){
		this.motDePasse.set(motDePasse) ;
	}

	public StringProperty motDePasseProperty(){
		return  motDePasse ;
	}

	@XmlElement(name = "Numero")
	public String getNumeroCompte(){
		return numeroCompte.get();
	}

	public void setNumeroCompte(String numeroCompte){
		this.numeroCompte.set(numeroCompte) ;
	}

	public StringProperty numeroCompteProperty(){
		return  numeroCompte ;
	}

	@XmlElement(name = "Type")
	public TypeCompte getTypeDeCompte(){
		return typeDeCompte.get();
	}

	public void setTypeDeCompte(TypeCompte typeDeCompte){
		this.typeDeCompte.set(typeDeCompte) ;
	}

	public ObjectProperty<TypeCompte> getTypeDeCompteProperty(){
		return typeDeCompte ;
	}

	@XmlElement(name = "Operation")
	public ArrayList<Operation> getLigneComptable(){
		return ligneComptable.get();
	}

	public void setLigneComptable(ArrayList<Operation> ligneComptable){
		this.ligneComptable.set(ligneComptable);
	}

	public ObjectProperty<ArrayList<Operation>> getLigneComptableProperty(){
		return ligneComptable ;
	}

	@XmlElement(name = "Taux")
	public float getTaux(){
		return taux.get() ;
	}

	public void setTaux(float taux){
		this.taux.set(taux);
	}

	public FloatProperty getTauxProperty(){
		return taux ;
	}

	public boolean enregistrerCompte(){
		File fichierCompte = new File(this.getNumeroCompte()+".dat") ;
		System.out.println(fichierCompte.getAbsolutePath()) ;
		try{
			JAXBContext context = JAXBContext.newInstance(CompteBanquaire.class) ;
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


			m.marshal(this, fichierCompte) ;

			return true ;
		}catch(Exception e){
			return false ;
		}
	}

	public boolean ouvrireCompteBanquaire(String numCompte){
		File fichierCompte = new File(numCompte+".dat") ;

		CompteBanquaire compte = new CompteBanquaire() ;

		try{
			JAXBContext context = JAXBContext.newInstance(CompteBanquaire.class) ;
			Unmarshaller um = context.createUnmarshaller() ;

			compte = (CompteBanquaire)um.unmarshal(fichierCompte) ;

			this.setNumeroCompte(compte.getNumeroCompte()) ;
			this.setTypeDeCompte(compte.getTypeDeCompte()) ;
			this.setLigneComptable(compte.getLigneComptable()) ;
			this.setTaux(compte.getTaux()) ;
			this.setMotDePasse(compte.getMotDePasse());

			return true ;
		}catch(Exception e){
			e.printStackTrace();
			return false ;
		}

	}

}
