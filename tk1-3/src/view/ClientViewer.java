/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author swallak
 */
public class ClientViewer {
	
	static Login loginUi = null;

	public static void main (String[] argv)
    {
		loginUi = new Login();
		loginUi.setVisible(true);
		
		
    }
}
