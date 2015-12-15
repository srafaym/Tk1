/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * Encapsulates connection and session management
 * One must call tryConnect before instancing this class
 * @author swallak
 */
public class SessionManager {
    private Session session;
    private Connection connection;
    
    public SessionManager(String clientId, String brokerUrl)
    {
        try {
            System.out.println("Creating connection...");
            
            //connection factory
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
            
            //creating connection
            connection = factory.createConnection();
            connection.setClientID(clientId);
            //creating session
            session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
        } catch (javax.jms.InvalidClientIDException ex) {
            System.err.println("Client exists: "+clientId);
            //Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            System.err.println("Exception creating connection");
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Check if connection to broker using clientId is possible
     * @param brokerURL
     * @param cliendId
     * @return 0 if possible, 1 if client exists and 2 if some other exception was caught
     */
    public static int tryConnect(String brokerURL, String cliendId) throws JMSException
    {
        int status=0;
        Connection connection=null;
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
            connection = factory.createConnection();
            connection.setClientID(cliendId);
        } catch (javax.jms.InvalidClientIDException ex) {
            status = 1;
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            status = 2;
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex)
        {
            status = 2;
            ex.printStackTrace();
        }
        finally{
            connection.close();
            return status;
        }
    }
        
        
    
    /**
     * Start delivery of messages
     */
    public void start ()
    {
        try {
            System.out.println("Connecting client "+connection.getClientID());
            connection.start();
        } catch (JMSException ex) {
            System.err.println("Exception connection client");
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 
     * @return session instance; 
     */
    public Session getSession()
    {
        return this.session;
    }
    
    /**
     * close the connection and session
     */
    public boolean close()
    {
        boolean success =false;
        try {
            System.out.println("Closing...");
            session.close();
            connection.close();
            success = true;
        } catch (JMSException ex) {
            System.err.println("Exception at close");
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            return success;
        }
    }
    
}
