import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Starter{

	public Starter() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] products = {"Apple", "Orange", "Lemon"};
		double[] prices = {4.00, 5.00, 6.00};
		int[] available = {1, 2, 3};
		
		View view = new View("Test", products, prices, available);
		view.createAndShowGUI();
	}

	

}
