/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.SessionManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.apache.activemq.ActiveMQConnection;

/**
 *
 * @author swallak
 */
public class Publisher implements PublisherInterface {
    
    private Queue myQueue;
    private Session mySession;
    private HashSet<String> myTopics;

    public Publisher (String myName,Session session)
    {
        this.mySession = session;
        this.myTopics = new HashSet<>();
        
        try {
            //Every publisher has its own queue
            
            System.out.println("Creating queue for "+myName);
            this.myQueue = session.createQueue(myName); //TODO
        } catch (JMSException ex) {
            System.err.println("Exception creating queue for ");
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Destination createTopic(String topicName) {
        
        Destination topic = null;
        try {
            System.out.println("Creating topic " +topicName);
            topic = mySession.createTopic(topicName);
        } catch (JMSException ex) {
            System.err.println("Exception creating topic "+topicName);
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        myTopics.add(topicName);
        return topic;
       
    }

    @Override
    public void publishToQueue(String queueName, Message msg) {
        try {
            System.out.println("Publishing to a queue: "+queueName);
            Destination dest = mySession.createQueue(queueName);
            MessageProducer producer = mySession.createProducer(dest);
            //Publishing to queues is persitant
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.send(msg);
        } catch (JMSException ex) {
            System.err.println("Exception publishing to a queue");
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void publishToTopic(String topicName, Message msg) {
        try {
            System.out.println("Publishing to a topic: "+topicName);
            Destination dest = mySession.createTopic(topicName);
            MessageProducer producer = mySession.createProducer(dest);
            producer.send(msg);
        } catch (JMSException ex) {
            System.err.println("exception publishing to a topic");
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void publish(Message msg) {
        
        try {
            System.out.println("Publishing msg to own queue");
            MessageProducer producer = mySession.createProducer(myQueue);
            producer.send(msg);
        } catch (JMSException ex) {
            System.err.println("Exception publishing msg");
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @return myTopics
     */
    public String[] getTopics ()
    {
        String[] topics = (String[]) this.myTopics.toArray();
        return topics;
    }
    
//    public static void main (String[] argv) throws JMSException
//    {
//        String brokerUrl = ActiveMQConnection.DEFAULT_BROKER_URL;
//        SessionManager sessionManager = new SessionManager("saad",brokerUrl);
//        sessionManager.start();
//        Publisher pub = new Publisher("saad", sessionManager.getSession());
//        Session session = sessionManager.getSession();
//        TextMessage msg = session.createTextMessage("WOWWOWWOW");
//        pub.publish(msg);
//        System.out.println(msg.getJMSMessageID());
//        
//        
//    }
}
