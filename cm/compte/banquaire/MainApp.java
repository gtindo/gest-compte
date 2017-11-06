package cm.compte.banquaire;

import cm.compte.banquaire.modele.*;
import cm.compte.banquaire.vue.BarCharMoyenController;
import cm.compte.banquaire.vue.BarcharMotifController;
import cm.compte.banquaire.vue.ChangerMotDePasseController;
import cm.compte.banquaire.vue.ConnexionEtInscriptionController;
import cm.compte.banquaire.vue.OperationsEtEtat;
import cm.compte.banquaire.vue.PreferencesController;
import cm.compte.banquaire.vue.SupprimerCompteController;
import cm.compte.banquaire.vue.ConteneurPrincipalController;
import cm.compte.banquaire.vue.OperationAutreCompteController;
import cm.compte.banquaire.vue.OperationCamemberController;
import cm.compte.banquaire.vue.OperationMonCompteController ;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane ;
import javafx.scene.layout.BorderPane ;
import javafx.stage.Stage;
import javafx.stage.Modality ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList ;
import javafx.beans.property.SimpleDoubleProperty ;
import javafx.beans.property.DoubleProperty;

public class MainApp extends Application{
	private Stage firstStage ;
	private Stage dialogStage ;
	private AnchorPane connexionPan ;
	private AnchorPane operationEtat ;
	private BorderPane conteneurPrincipal ;
	private ObservableList<Operation> obList = FXCollections.observableArrayList() ;
	private CompteBanquaire compte = new CompteBanquaire() ;
	private DoubleProperty valeurCouranteCompte = new SimpleDoubleProperty(0.0) ;
	private int numbOperation = 10 ;

	@Override
	public void start(Stage firstStage){
		this.firstStage = firstStage ;
		this.firstStage.setTitle("Gestion des comptes bancaires ") ;
		this.firstStage.getIcons().add(new Image("file:vue/logo.png")) ;

		initFirstStageLayout() ;

	}

	public static void main(String[] args){
		launch(args) ;
	}

	public void appRefresh(){
		int nbIter = this.numbOperation < this.compte.getLigneComptable().size() ? this.numbOperation  : this.compte.getLigneComptable().size() ; ;
		int nbOperation = this.compte.getLigneComptable().size() ;

		for(int i = (nbOperation - 1) ; i >= (nbOperation - nbIter) ; i--){
			this.obList.add(this.compte.getLigneComptable().get(i)) ;
		}

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.compte.getLigneComptable().get(i).getTypeOperation().equals("Credit")){
				valeurCouranteCompte.set(valeurCouranteCompte.get() + this.compte.getLigneComptable().get(i).getMontant()) ;
			}else{
				valeurCouranteCompte.set(valeurCouranteCompte.get() - this.compte.getLigneComptable().get(i).getMontant()) ;
			}
		}
	}

	public void initFirstStageLayout(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/ConnexionEtInscription.fxml")) ;

			this.connexionPan = (AnchorPane)loader.load() ;

			ConnexionEtInscriptionController controleur = loader.getController();
			controleur.setMainApp(this);

			Scene scene = new Scene(this.connexionPan) ;
			this.firstStage.setScene(scene) ;
			this.firstStage.show();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void changeFirstStageLayout(){
		this.closeFirstStage();
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/conteneurPrincipal.fxml")) ;
			this.conteneurPrincipal = (BorderPane)loader.load() ;

			ConteneurPrincipalController controleur = loader.getController() ;
			controleur.setMainApp(this);

			Scene scene = new Scene(this.conteneurPrincipal) ;
			this.firstStage.setScene(scene);
			this.firstStage.show();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void showOperationEtEtat(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/OperationsEtEtat.fxml")) ;

			this.operationEtat = (AnchorPane)loader.load() ;

			conteneurPrincipal.setCenter(this.operationEtat);

			OperationsEtEtat controleur = loader.getController();
			controleur.setMainApp(this) ;

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void showOperationMonCompte(String title){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/OperationMonCompte.fxml"));

			AnchorPane conteneur = (AnchorPane)loader.load();

			OperationMonCompteController controleur = loader.getController() ;
			this.dialogStage = new Stage() ;
			this.dialogStage.setTitle(title);
			this.dialogStage.initOwner(this.firstStage) ;
			this.dialogStage.initModality(Modality.WINDOW_MODAL) ;

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);

			controleur.setMainApp(this) ;
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}

	public void showOperationAutreCompte(String title){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/OperationAutreCompte.fxml")); ;

			AnchorPane conteneur = (AnchorPane)loader.load();

			OperationAutreCompteController controleur = loader.getController() ;
			this.dialogStage= new Stage() ;
			this.dialogStage.setTitle(title);
			this.dialogStage.initOwner(this.firstStage);
			this.dialogStage.initModality(Modality.WINDOW_MODAL);

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);

			controleur.setMainApp(this);
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}

	public void showDiagrammeMoyen(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/BarCharMoyen.fxml")) ;

			AnchorPane conteneur = (AnchorPane)loader.load() ;

			BarCharMoyenController controleur = loader.getController() ;
			this.dialogStage = new Stage() ;
			this.dialogStage.setTitle("Nombres d'operations par moyen de paiement");
			this.dialogStage.initOwner(this.firstStage) ;
			this.dialogStage.initModality(Modality.WINDOW_MODAL);

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);

			controleur.setMainApp(this);
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void showDiagrammeMotif(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/BarcharMotif.fxml")) ;

			AnchorPane conteneur = (AnchorPane)loader.load() ;

			BarcharMotifController controleur = loader.getController() ;
			this.dialogStage = new Stage() ;
			this.dialogStage.setTitle("Nombres d'operations par motif de paiement");
			this.dialogStage.initOwner(this.firstStage) ;
			this.dialogStage.initModality(Modality.WINDOW_MODAL);

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);

			controleur.setMainApp(this);
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void showOperationCamember(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/OperationCamember.fxml"));

			AnchorPane conteneur = (AnchorPane)loader.load();

			OperationCamemberController controleur = loader.getController() ;
			this.dialogStage = new Stage() ;
			this.dialogStage.setTitle("Pourcentages - Debit - Credit") ;
			this.dialogStage.initOwner(this.firstStage);
			this.dialogStage.initModality(Modality.WINDOW_MODAL) ;

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);

			controleur.setMainApp(this);
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void showChangerMotDePasse(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/ChangerMotDePasse.fxml")) ;

			AnchorPane conteneur = (AnchorPane)loader.load();

			ChangerMotDePasseController controleur = loader.getController() ;

			this.dialogStage = new Stage() ;
			this.dialogStage.setTitle("Changer de mot de passe");
			this.dialogStage.initOwner(this.firstStage);
			this.dialogStage.initModality(Modality.WINDOW_MODAL) ;

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);
			controleur.setMainApp(this) ;
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}

	public void showSupprimerCompte(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/SupprimerCompte.fxml")) ;

			AnchorPane conteneur = (AnchorPane)loader.load();

			SupprimerCompteController controleur = loader.getController() ;

			this.dialogStage = new Stage() ;
			this.dialogStage.setTitle("Suppresion de mn compte");
			this.dialogStage.initOwner(this.firstStage);
			this.dialogStage.initModality(Modality.WINDOW_MODAL) ;

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);
			controleur.setMainApp(this) ;
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}

	public void showPreference(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/Preferences.fxml")) ;

			AnchorPane conteneur = (AnchorPane)loader.load();

			PreferencesController controleur = loader.getController() ;

			this.dialogStage = new Stage() ;
			this.dialogStage.setTitle("Preferences");
			this.dialogStage.initOwner(this.firstStage);
			this.dialogStage.initModality(Modality.WINDOW_MODAL) ;

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);
			controleur.setMainApp(this) ;
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}

	public void showAide(){
		try{
			FXMLLoader loader = new FXMLLoader() ;
			loader.setLocation(MainApp.class.getResource("vue/Aide.fxml")) ;

			AnchorPane conteneur = (AnchorPane)loader.load() ;

			this.dialogStage = new Stage() ;
			this.dialogStage.setTitle("Aide");
			this.dialogStage.initOwner(this.firstStage);
			this.dialogStage.initModality(Modality.WINDOW_MODAL) ;

			Scene scene = new Scene(conteneur) ;
			dialogStage.setScene(scene);
			dialogStage.showAndWait();
		}catch(Exception e){
			e.printStackTrace() ;
		}
	}

	public void closeFirstStage(){
		firstStage.close() ;
	}

	public Stage getDialogStage(){
		return this.dialogStage ;
	}

	public CompteBanquaire getCompteBanquaire(){
		return compte ;
	}

	public void setCompteBanquaire(CompteBanquaire compte){
		this.compte = compte ;
	}

	public void closeDialog(){
		this.dialogStage.close();
	}

	public ObservableList<Operation> getObList(){
		return this.obList ;
	}

	public void setObList(ObservableList<Operation> obList){
		this.obList = obList ;
	}

	public double getValeurCourante(){
		return this.valeurCouranteCompte.get() ;
	}

	public void setValeurCourante(double valeurCourante){
		this.valeurCouranteCompte.set(valeurCourante);
	}

	public DoubleProperty getValeurCouranteProperty(){
		return valeurCouranteCompte ;
	}

	public int getNbOppMotifDivers(){
		int nbOperation = this.getCompteBanquaire().getLigneComptable().size() ;
		int nbOperationMotif = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.getCompteBanquaire().getLigneComptable().get(i).getMotifOperation() == Motif.DIVERS){
				nbOperationMotif += 1 ;
			}
		}

		return nbOperationMotif ;
	}

	public int getNbOppMotifAlimentation(){
		int nbOperation = this.getCompteBanquaire().getLigneComptable().size() ;
		int nbOperationMotif = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.getCompteBanquaire().getLigneComptable().get(i).getMotifOperation() == Motif.ALIMENTATION){
				nbOperationMotif += 1 ;
			}
		}

		return nbOperationMotif ;
	}

	public int getNbOppMotifSalaire(){
		int nbOperation = this.getCompteBanquaire().getLigneComptable().size() ;
		int nbOperationMotif = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.getCompteBanquaire().getLigneComptable().get(i).getMotifOperation() == Motif.SALAIRE){
				nbOperationMotif += 1 ;
			}
		}

		return nbOperationMotif ;
	}

	public int getNbOppMotifLoyer(){
		int nbOperation = this.getCompteBanquaire().getLigneComptable().size() ;
		int nbOperationMotif = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.getCompteBanquaire().getLigneComptable().get(i).getMotifOperation() == Motif.LOYER){
				nbOperationMotif += 1 ;
			}
		}

		return nbOperationMotif ;
	}

	public int getNbOppMotifSi(){
		int nbOperation = this.getCompteBanquaire().getLigneComptable().size() ;
		int nbOperationMotif = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.getCompteBanquaire().getLigneComptable().get(i).getMotifOperation() == Motif.SI){
				nbOperationMotif += 1 ;
			}
		}

		return nbOperationMotif ;
	}

	public int getNbOppMoyenCb(){
		int nbOperation = this.getCompteBanquaire().getLigneComptable().size() ;
		int nbOperationMoyen = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.getCompteBanquaire().getLigneComptable().get(i).getMoyenDePaiement() == MoyenPaiement.CB){
				nbOperationMoyen += 1 ;
			}
		}

		return nbOperationMoyen ;
	}

	public int getNbOppMoyenCheque(){
		int nbOperation = this.getCompteBanquaire().getLigneComptable().size() ;
		int nbOperationMoyen = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.getCompteBanquaire().getLigneComptable().get(i).getMoyenDePaiement() == MoyenPaiement.CHEQUE){
				nbOperationMoyen += 1 ;
			}
		}

		return nbOperationMoyen ;
	}

	public int getNbOppMoyenVirement(){
		int nbOperation = this.getCompteBanquaire().getLigneComptable().size() ;
		int nbOperationMoyen = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.getCompteBanquaire().getLigneComptable().get(i).getMoyenDePaiement() == MoyenPaiement.VIREMENT){
				nbOperationMoyen += 1 ;
			}
		}

		return nbOperationMoyen ;
	}

	public int getNbOperationDebit(){
		int nbOperation = this.compte.getLigneComptable().size() ;
		int nbOperationB = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if(this.compte.getLigneComptable().get(i).getTypeOperation().equals("Debit")){
				nbOperationB += 1 ;
			}
		}

		return nbOperationB;
	}

	public int getNbOperationCredit(){
		int nbOperation = this.compte.getLigneComptable().size() ;
		int nbOperationB = 0 ;

		for(int i = 0 ; i < nbOperation ; i++){
			if((this.compte.getLigneComptable().get(i).getTypeOperation().equals("Credit"))){
				nbOperationB += 1 ;
			}
		}

		return nbOperationB ;
	}

	public int getNumbOperation(){
		return this.numbOperation ;
	}

	public void setNumbOperation(int numbOperation){
		this.numbOperation = numbOperation ;
	}

}
