package cm.compte.banquaire.vue;

import javafx.scene.chart.PieChart ;
import javafx.scene.layout.AnchorPane;

import java.text.DecimalFormat;

import cm.compte.banquaire.MainApp;
import javafx.collections.FXCollections ;
import javafx.collections.ObservableList ;
import javafx.fxml.FXML;

public class OperationCamemberController {

	@FXML
	private PieChart pieChart ;

	private ObservableList<PieChart.Data> pieChartData ;

	private MainApp mainApp ;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp ;

		DecimalFormat format = new DecimalFormat("#.0") ;

		float n1 = this.mainApp.getNbOperationCredit() ;
		float n2 = this.mainApp.getNbOperationDebit() ;
		float n3 = n1 + n2 ;

		this.pieChartData = FXCollections.observableArrayList() ;
		pieChartData.add(new PieChart.Data("Debit "+format.format((n2/n3) * 100)+"%", n2)) ;
		pieChartData.add(new PieChart.Data("Credit "+format.format((n1/n3) * 100)+"%", n1)) ;

		this.pieChart = (new PieChart(pieChartData)) ;
		this.pieChart.setTitle("Debit - Credit - SI");
		((AnchorPane)this.mainApp.getDialogStage().getScene().getRoot()).getChildren().add(this.pieChart);

	}

	@FXML
	public void initialize(){

	}
}
