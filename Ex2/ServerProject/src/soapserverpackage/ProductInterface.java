package soapserverpackage;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)

public interface ProductInterface {

	@WebMethod Products alldetails();	

	@WebMethod String addtocart(int client_id,Product pCart);
	
	@WebMethod String Buy(int client_id);
	
	@WebMethod String getClientData(int client_id,String itemname,double price,int available_in_store,int item_id,int item_unique_id);
}
