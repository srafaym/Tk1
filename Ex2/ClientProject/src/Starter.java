import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;



import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import soapserverpackage.Product;
import soapserverpackage.ProductInterface;
import soapserverpackage.Products;


public class Starter{

	public Starter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
	
		String[] arrayproduct = new String[4];
		URL url = null;
		try {
			url = new URL("http://localhost:5058/hello?wsdl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 1st argument service URI, refer to wsdl document above
		// 2nd argument is service name, refer to wsdl document above
		QName qname = new QName("http://soapserverpackage/",
				"ProductImplementationService");

		Service service = Service.create(url, qname);

		ProductInterface product_object = service.getPort(ProductInterface.class);
		Products products;
		products = product_object.alldetails();

		String[] prod = new String[3];
		double[] prices = new double[3];
		int[] available =  new int[3];
		
		int i = 0;
		for(Product p : products.productList)   
		{	
			prod[i] = p.name;	
			prices[i] = p.price;
			available[i] = p.quantity;
			i++;
		}
		View view = new View("Test", prod, prices, available);
		view.createAndShowGUI();
		
	}

	

}
