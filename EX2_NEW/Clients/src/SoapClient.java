
public class SoapClient implements Client {

	public View view;
	private String [] products; 
	private double [] prices;
	private int[] available;
	public int id;
	
	public SoapClient(int id) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyServerWithBuy() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkAvailable(String product) {
		// TODO Auto-generated method stub
		return false;
	}

}
