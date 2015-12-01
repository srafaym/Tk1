import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import soap_server.Product;
import soap_server.Products;
import soap_server.ServerInterface;


public class SoapClient implements Client {

	public View view;
	private String [] clientproducts; 
	private double [] prices;
	private int[] available;
	public int id;
	public Service service ;
	
	
	
	public SoapClient(int id) {
		this.id = id; 
		String[] arrayproduct = new String[4];
		URL url = null;
		try {
			url = new URL("http://localhost:8090/soap_server?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://soap_server/",
				"ServerImplementationService");

		service = Service.create(url, qname);

		
		ServerInterface product_object = (ServerInterface) service.getPort(ServerInterface.class);
		Products products;
		products = product_object.alldetails();
	
		List<Product>serverProductList = products.getProductList();
		
		String[] clientproducts = new String[ serverProductList.size()];
		double[] prices = new double[serverProductList.size()];
		int[] available =  new int[serverProductList.size()];
		
		int i = 0;
		for(Product p : serverProductList)   
		{	
			System.out.println(p.toString());
			clientproducts[i] = p.getItemname();	
			prices[i] = p.getPrice();
			available[i] = p.getAvailableInStore();
			i++;
		}

		
		
		
//		this.clientproducts = getProductsFromServer();
//		this.prices = getPricesFromServer();
//		this.available = getAvailableAmountFromServer();
//		
		View view = new View("SOAP", clientproducts, prices, available, this);
		
		view.createAndShowGUI();
	}

	@Override
	public String[] getProductsFromServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getPricesFromServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getAvailableAmountFromServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToCart(String product, int amount) {
		
		
		ServerInterface product_object = (ServerInterface) service.getPort(ServerInterface.class);
		Products products;
				
		Product prod = new Product();
		prod.setItemname(product);
		prod.setAvailableInStore(amount);
		String msg = product_object.addtocart(1, prod);
		System.out.println(msg);

	}

	@Override
	public void notifyServerWithBuy(int client_id) {
		
		ServerInterface product_object = (ServerInterface) service.getPort(ServerInterface.class);
		Products products;
		products = product_object.alldetails();
		
		String msg = product_object.buy(1);
		System.out.println(msg);
	}

	@Override
	public boolean checkAvailable(String product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkAvailable(String product, int quanity) {
		// TODO Auto-generated method stub
		return false;
	}

}
