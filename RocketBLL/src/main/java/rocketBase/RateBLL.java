package rocketBase;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;



public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException
	{
		//TODO - RocketBLL RateBLL.getRate - make sure you throw any exception
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		
		List<RateDomainModel> rateArray = new ArrayList<>();
		double rate = 0;
		
		 rateArray = RateDAL.getAllRates();
		 
		 RateDomainModel highestInRange = new RateDomainModel();
		 for (RateDomainModel rdm: rateArray ){
			 if (rdm.getiMinCreditScore() <= GivenCreditScore){
				 highestInRange = rdm;
			 }
		 }
		 
		 rate = highestInRange.getdInterestRate();
		
		
		 
		if (rate==0){
			throw new RateException(highestInRange);
		}
		//TODO - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return(rate);
		
		
	}
	
	
	//TODO - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(int cs, double period, double p, double f, boolean t) throws RateException
	{		
		//calculating number of payments assuming monthly payments
		System.out.println("////////////////////////////"+p +"    " +f+"    "+t);
		return (-(FinanceLib.pmt((getRate(cs))/1200, (period*12), p, f, t)));
	}
}
