package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable{

	private MainApp mainApp;
	
	
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	@FXML
	private TextField txtIncome;
	//		TextBox  - 	txtExpenses
	@FXML
	private TextField txtExpenses;
	//		TextBox  - 	txtCreditScore
	@FXML
	private TextField txtCreditScore;
	//		TextBox  - 	txtHouseCost
	@FXML
	private TextField txtHouseCost;
	
	@FXML
	private TextField txtDownPayment;
	//		ComboBox -	loan term... 15 year or 30 year
	
	@FXML
	private ComboBox loanTermBox = new ComboBox();	//can't get this to work :(	
	//I did what it said on Piazza and connected it in scene builder
	
	
	//		Labels   -  various labels for the controls
	@FXML
	private Label Income;
	@FXML
	private Label Expenses;
	@FXML
	private Label CreditScore;
	@FXML
	private Label HosueCost;
	@FXML
	private Label LoanTerm;
	//		Button   -  button to calculate the loan payment
	@FXML
	private Button CalcPayment;
	//		Label    -  to show error messages (exception throw, payment exception)
	@FXML
	private Label Payment;

	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			don't know how to get down payment or if it's even used for anything
		//			I've created you an instance of lq...  execute the setters in lq
		lq.setdAmount((Double.parseDouble(txtHouseCost.getText()))-(Double.parseDouble(txtDownPayment.getText())));
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setiCreditScore(Integer.valueOf(txtCreditScore.getText()));
		try{
		lq.setdRate(RateBLL.getRate(Integer.valueOf(txtCreditScore.getText())));
		}
		catch (Exception e){
			Payment.setText("Your Credit Score is too low");
		}
		
		//if statement for term
			//my ComboBox doen't work but this will return a value of 30.
			//that will later be multiplied by 12 to get number of monthly payments
		if (loanTermBox.getSelectionModel().getSelectedItem().toString() == "15 year loan"){
			lq.setiTerm(15);
		}
		else{
			lq.setiTerm(30);
		}
		
		
		
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		double PITI;
		double piti1 = (lRequest.getIncome()* 0.28);
		double piti2 = ((lRequest.getIncome()* 0.36)-lRequest.getExpenses());
		if (piti1>piti2){
			PITI = piti2;
		}
		else{
			PITI = piti1;
		}
		
		
		
		double pmt = lRequest.getdPayment();
		
		if (pmt>PITI){
			Payment.setText("This loan exceeds your Maximum");
		}
		else{
		Payment.setText("Payment:" + String.valueOf((double)Math.round(pmt * 100d) / 100d)+ " Rate:" + String.valueOf(lRequest.getdRate())+"%");
		}
	}

	ObservableList<String> list = FXCollections.observableArrayList("15 year loan", "30 year loan");
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loanTermBox.setItems(list);
		
	}
}
