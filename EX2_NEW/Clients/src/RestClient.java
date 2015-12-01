import java.util.HashMap;

public class RestClient implements Client{

	public View view;
	private String [] products; 
	private double [] prices;
	private int[] available;
	public int id;
	
	public RestClient(int id) {
		this.id = id;
		this.products = getProductsFromServer();
		this.prices = getPricesFromServer();
		this.available = getAvailableAmountFromServer();
		View view = new View("Rest", products, prices, available, this);
		
		view.createAndShowGUI();
	}

	@Override
	public String[] getProductsFromServer() {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToCart(String product, int amount) {
		// TODO Auto-generated method stub
		this.shoppingCart.put(product, amount);
	}

	@Override
	public void notifyServerWithBuy(int client_id) {
		// TODO Auto-generated method stub
		
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
