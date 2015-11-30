package soapserverpackage;

import java.util.ArrayList;

import javax.jws.WebService;



@WebService(endpointInterface = "soapserverpackage.ProductInterface")
public class ProductImplementation implements ProductInterface {

	public static ArrayList<Product> listProduct;
	public static Products products  ;
	

	public ProductImplementation (){
		
		// TODO Auto-generated method stub
//		String[] products = {"Apple", "Orange", "Lemon"};
//		double[] prices = {4.00, 5.00, 6.00};
//		int[] available = {1, 2, 3};
	
		
		
			listProduct = new ArrayList<Product>();
			
			Product p1 = new Product("Apple",5.0,3);
			Product p2 = new Product("Orange",2.0,3);
			Product p3 = new Product("Lemon",3.0,3);
			
			listProduct.add(p1);
			listProduct.add(p2);
			listProduct.add(p3);
			
			products = new Products(listProduct);
	}
	
	@Override
	public Products alldetails() {
		return products;
	}

	@Override
	public String addtocart(Integer cust_id,Products productsAddedToCart) {
		int i = 0;
		String message = "";
		int currentClientID=0;
		for (Product pCart : productsAddedToCart.productList) {

			for (Product pMain : products.productList) {
				if (pMain.name == pCart.name) {
					if (pMain.quantity < pCart.quantity) {
						i++;
						message = "Product "
								+ pMain.name
								+ " 's required quantity is not available anymore";
					}
				}
			}
		}

		if (i > 0)
			return message;
		else {
			
		}
			for (Product pCart : productsAddedToCart.productList) {
				for (Product pMain : products.productList) {
					pMain.quantity = pMain.quantity - pCart.quantity;
				}
			}
			
			return (currentClientID++) + "";
	}

}
