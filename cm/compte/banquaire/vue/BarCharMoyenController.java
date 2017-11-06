package cm.compte.banquaire.vue;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import cm.compte.banquaire.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class BarCharMoyenController {

	@FXML
	private BarChart<String, Integer> barChart;

	@FXML
	private CategoryAxis xAxis ;

	private ObservableList<String> typeName = FXCollections.observableArrayList() ;

	private MainApp mainApp ;

	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp ;

		XYChart.Series<String, Integer> series = new XYChart.Series<>() ;

		series.getData().add(new XYChart.Data<>(typeName.get(0), this.mainApp.getNbOppMoyenCb())) ;
		series.getData().add(new XYChart.Data<>(typeName.get(1), this.mainApp.getNbOppMoyenCheque())) ;
		series.getData().add(new XYChart.Data<>(typeName.get(2), this.mainApp.getNbOppMoyenVirement())) ;

		barChart.getData().add(series) ;
	}

	@FXML
	private void initialize(){
		String[] type = {"Carte Banquaire", "Cheque", "Virement"} ;
		this.typeName.addAll(type) ;
		xAxis.setCategories(typeName);
	}

}
