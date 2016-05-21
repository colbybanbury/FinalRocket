package exceptions;

import java.util.ArrayList;
import java.util.List;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException -
	//RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	
	RateDomainModel ratedomainmodel = new RateDomainModel();
	
	public  RateException(RateDomainModel rdm){
		ratedomainmodel = rdm;
	}
	public RateDomainModel getratedomainmodel(){
		return(ratedomainmodel);
	}
}
