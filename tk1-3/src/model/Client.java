/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.SessionManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;

/**
 * 
 * @author swallak
 */
public class Client implements LoginInterface{
    
    public PublisherInterface publisher;
    public ConsumerInterface consumer;
    
    private String myId;
    private SessionManager sessionMng;
    private boolean connected = false;
    
    private final String brokerUrl = ActiveMQConnection.DEFAULT_BROKER_URL;


    public Client(String clientId)
    {
        this.myId = clientId;
    }
    
    @Override
    /**
     * Try to connect and initialize fields of client when connection succeeds
     * 
     * @return true if connection succeeded or already connected
     */
    public boolean connect() {
        if(connected)
            return true;
        int status = -1;
        boolean success = false;
        try {
            status = SessionManager.tryConnect(brokerUrl, myId);
            if (status == 0)//ClientId is not used
            {
                //setting all the fields
                sessionMng = new SessionManager(myId, brokerUrl);
                publisher = new Publisher(myId, sessionMng.getSession());
                consumer = new Consumer(myId, sessionMng.getSession());
                
                //starting sessionManager
                sessionMng.start();
                System.out.println("Connection successful");
                
                success = true;
            } else if (status==1){
                System.out.println("Client Id is used, change clientId");
            }
            else
            {
                System.err.println("somme other error occured");
            }
        } catch (JMSException ex) {
            System.err.println("connection failed...");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connected = success;
            return success;
        }


    }

    @Override
    /**
     * Simply closes SessionMng if connected
     * also reset connected if closing of sessionMng is successful
     * @return true if success
     */
    public boolean disconnect() {
        if(!connected)
            return false;
       
        connected = !sessionMng.close();
        return !connected;
               
    }
    /**
     * Changes clientId if not connected
     * @param clientId 
     */
    public void setClientId(String clientId){
        if(connected)
        {
            System.err.println("Cannot change clientid; Already connected");
            return;
        }
        this.myId=clientId;
    }
    
    public TextMessage createTextMessage (String msg)
    {
        TextMessage txtmsg=null;
        try {
            txtmsg = sessionMng.getSession().createTextMessage(msg);
        } catch (JMSException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return txtmsg;
    }
    
    public String getClientId()
    {
        return new String(myId);
    }
    public String getBrokerURL()
    {
        return new String(brokerUrl);
    }
//public static void main (String[] argv)
 //   {
//        if(argv.length<1)
//            System.err.println("Insufficient arguments");
//        else
//        {
//            String id = "Rafay";
//            Client client = new Client(id);
//            
//            System.out.println("trying to connect");
//            client.connect();
            
            //TextMessage msg =  client.createTextMessage("Message from: "+id);
    //client.publisher.publish(msg);
    //       client.publisher.createTopic("myTopic");
    //      client.consumer.subscribe("myTopic");
    //      client.publisher.publishToTopic("myTopic", msg);
//        }
                

    //}
    
}
