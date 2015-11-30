package soapserverpackage;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)

public interface ProductInterface {

	@WebMethod Products alldetails();	

	@WebMethod String addtocart(Integer cust_id,Products pr);
}
