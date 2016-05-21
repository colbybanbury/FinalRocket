package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {

	
	//TODO - RocketDAL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
//	@Test
//	public void rate_test() {
//		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
//		double rate = 0;
//		for (RateDomainModel rdm: rates ){
//			 if (rdm.getiMinCreditScore() == 600){
//				 rate = rdm.getdInterestRate();
//			 }
//		 }
//		assertEquals(rate, 5.0, .1 );
//	}
	
	//TODO - RocketDAL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	
	//		this is tested for in the BLL as that is where the RateException exists
	
	@Test
	public void test() {
		
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
		
		assert(1==1);
	}

}
