package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class rate_test {

	//TODO - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	@Test
	public void rate_test() {
		double rate =0;
		try{
		rate = RateBLL.getRate(600);}
		catch (Exception e){
			//nothing
		}
		assertEquals(rate, 5.0, .1 );
	}
	
	
	//TODO - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test(expected = RateException.class)
	public void rate_exeption_test() throws RateException {
		double rate = RateBLL.getRate(0);
	}
	
	@Test
	public void pay_test() {
		double pmt= 0;
		try{
		pmt = RateBLL.getPayment(600, 15, 30000, 0, false);
		}
		catch (Exception e){
			//nothing
		}
		
		assertEquals(pmt, 1500.23, .1 );
	}

}
