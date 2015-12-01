import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import soapserverpackage.Product;
import soapserverpackage.ProductInterface;



public class View implements ActionListener{

	public JFrame frame;
	private String serviceName;
	private String [] products; 
	private double [] prices;
	private int[] available;
	public JButton buyButton;
	public JButton [] addToCart;
	public JTextField[] amount;
	public int client_id = 1;
	public ProductInterface product_object;
	public View(String serviceName, String [] products, double[] prices, int[] available,ProductInterface product_object ) {
		// TODO Auto-generated constructor stub
		this.serviceName = serviceName;
		this.products = products;
		this.prices = prices;
		this.available = available;
		this.product_object = product_object;
		this.buyButton = new JButton ("Buy");
		this.addToCart = new JButton [products.length];
		
		for (int i = 0; i < products.length; i++) {
		 this.addToCart[i] = new JButton ("Add to cart");
		 this.addToCart[i].addActionListener(this);				// Action listener add
		}
		
		this.amount = new JTextField [products.length];
		for (int i = 0; i < products.length; i++) {
		 this.amount[i] = new JTextField("0");
		}
	}

	public void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame(serviceName);

		frame.setLocationByPlatform(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Container contentPane = frame.getContentPane();
//		contentPane.setLayout(new BorderLayout());

		JPanel panel = new JPanel(new SpringLayout());
		String[] columnNames = {"Amount", "Title", "Price", "Available Amount"};
		
		
		
        int rows = products.length + 1;
        int cols = 5;
       
        //Add column names
        for (int i = 0; i < 4; i++) {    
          JLabel l = new JLabel(columnNames[i], JLabel.TRAILING);
           panel.add(l);
        }
        panel.add(buyButton);
        
        for (int i = 0; i < products.length; i++) {
            // amount
           
            panel.add(amount[i]);
            
            //product name
            JLabel name = new JLabel(products[i], JLabel.TRAILING);
            panel.add(name);
            
            //price
            JLabel p = new JLabel(Double.toString(prices[i]), JLabel.TRAILING);
            panel.add(p);
            
          //available
            JLabel a = new JLabel(Integer.toString(available[i]), JLabel.TRAILING);
            panel.add(a);
            
           
            panel.add(addToCart[i]);
        }
        

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(panel, //parent
                                        rows, cols,
                                        3, 3,  //initX, initY
                                        3, 3); //xPad, yPad
 
         
        //Set up the content pane.
        panel.setOpaque(true); //content panes must be opaque
        frame.setContentPane(panel);
 
        //Display the window.
        frame.pack();

		frame.setVisible(true);
		
		
		//buyButton.addActionListener(this);					// Action listener add
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(buyButton)){
			System.out.println("buy clicked");
		}
		else{
			for (int i = 0; i < products.length; i++) {
				if(e.getSource().equals(addToCart[i])){
					System.out.println("add to cart " + i+ " clicked with value "+ amount[i].getText());
					int a;
					try {
						a = Integer.parseInt(amount[i].getText());
						if (a >available[i]) notifyNotAvailableAmount(); 
						//TODO: check with server if the available amount is still accurate
													
						else 
							System.out.println("add to cart " + i+ " clicked with value "+ amount[i].getText());
						//TODO: Notify server
					} catch (Exception e2) {
						notifyInvalidAmount();
					}
					
					Product pro = new Product(1, "Mango", 1.2, 2);
					String re_msg = product_object.addtocart(client_id, pro);
					System.out.println(re_msg+"  ,add to cart " + i+ " clicked with value "+ amount[i].getText());
				}
			}
		}
	}
	
	public void notifyInvalidAmount(){
		JOptionPane.showMessageDialog(frame,
			    "The product amount needs to be a number.");
	}
	
	public void notifyNotAvailableAmount(){
		JOptionPane.showMessageDialog(frame,
			    "The product amount needs to be in the available range.");
	}
	
	public void notifyAlreadySold(){
		JOptionPane.showMessageDialog(frame,
			    "The product is no longer available.");
	}

}
