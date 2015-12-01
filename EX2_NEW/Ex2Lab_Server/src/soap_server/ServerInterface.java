package soap_server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


import java.*;
import java.util.ArrayList;


@WebService
@SOAPBinding(style = Style.RPC)

public interface ServerInterface {

	@WebMethod Products alldetails();	

	@WebMethod String addtocart(int client_id,Product pCart);
	
	@WebMethod String Buy(int client_id);
}
