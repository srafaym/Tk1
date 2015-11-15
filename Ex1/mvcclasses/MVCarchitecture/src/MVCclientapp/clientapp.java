package MVCclientapp;
//main program

import MVCClientController.ClientController;
import MVCModel.ClientModel;
import MVCView.ClientView;

public class clientapp {
	
	
	
	public static void main(string[] args)
	
	{
		ClientView theview = new ClientView();
		ClientModel themodel= new ClientModel();
		ClientController thecontroller = new ClientController();
		
		
		theview.setVisible(true);
		
		
	}
	

}
