package rocketServer;

import java.io.IOException;

import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			//	TODO - RocketHub.messageReceived

			//	You will have to:
			//	Determine the rate with the given credit score (call RateBLL.getRate)
			//		If exception, show error message, stop processing
			//		If no exception, continue
			//	Determine if payment, call RateBLL.getPayment
			//	
			//	you should update lq, and then send lq back to the caller(s)
			try{
			double rate = RateBLL.getRate(lq.getiCreditScore());
			double payment = RateBLL.getPayment(lq.getiCreditScore(), lq.getiTerm(), lq.getdAmount(), 0,false);
			//passing in term instead of payments. Will change RateBLL to accommodate. 
			//I'm guessing amount is the present value
			lq.setdRate(rate);
			lq.setdPayment(payment);
			sendToAll(lq);
			}
			catch (Exception e){
			sendToAll("Credit score did not return a interest rate");
			}

		}
	}
}
