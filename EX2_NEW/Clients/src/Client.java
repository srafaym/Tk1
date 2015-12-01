import java.util.HashMap;

public interface Client {
	

	public HashMap<String, Integer> shoppingCart = new HashMap<String, Integer>();
			
	public String[] getProductsFromServer();
	
	public double[] getPricesFromServer();
	
	public int[] getAvailableAmountFromServer();
	
	public void addToCart(String product, int amount);
		
	public void notifyServerWithBuy(int client_id);
	
	public boolean checkAvailable(String product,int quanity);

	boolean checkAvailable(String product);
	
}
