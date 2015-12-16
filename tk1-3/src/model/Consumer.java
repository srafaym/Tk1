/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Topic;


/**
 *
 * @author swallak
 */
public class Consumer implements ConsumerInterface{
    private Map<String,MessageConsumer> followedTopics=null;
    private Map<String,MessageConsumer> followedClients=null;
    private Session mySession;
    private String myClientId;
    private MessageListener msgListener=null;
    
    private Lock topicsLock = new ReentrantLock();
    private Lock clientsLock = new ReentrantLock();
    
    private void init()
    {
        msgListener=new MessageListener() {
            @Override
            public void onMessage(Message msg) {
                System.out.println("(Default listener)Received message from:"+myClientId);
                msg.toString();
            }
        };
        
    }
    
    public Consumer (String clientId, Session session)
    {
        this.myClientId=clientId;
        this.mySession=session;
        
        //Setting default message listener
        init();
        
        //creating map of followed clients
        //a client always follow himself
        followedClients= new HashMap<>();
        //this.follow(myClientId);
    }
    
    @Override
    public void subscribe(String topicName) {
        
        topicsLock.lock();
        if(followedTopics==null)
        {
            followedTopics = new HashMap<>();
        }
        
        if(followedTopics.containsKey(topicName))
        {
            //Topic already followed 
            System.out.println(myClientId+" is already subscribed to topic "+topicName);
        }
        else
        {
            try {
                System.out.println(myClientId+": Subscribing to topic: "+topicName);
                Topic topic = mySession.createTopic(topicName);
                MessageConsumer consumer = mySession.createConsumer(topic);
                consumer.setMessageListener(msgListener);
                followedTopics.put(topicName, consumer);
            } catch (JMSException ex) {
                System.err.println("exception subscribing to topic");
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                topicsLock.unlock();
                return;
            }
        }
        topicsLock.unlock();
        
    }

    @Override
    public void follow(String clientId) {
        //following a client is durable.
        //Client will always receive its followedclients messages
        clientsLock.lock();
        if(followedClients.containsKey(clientId))
        {
            System.out.println(myClientId+" already follows: "+clientId);
        }
        else
        {
            try {
                System.out.println(myClientId+": Following client:" + clientId);
                Queue dest = mySession.createQueue(clientId);
                MessageConsumer consumer = mySession.createConsumer(dest);
                consumer.setMessageListener(msgListener);
                followedClients.put(clientId, consumer);
            } catch (JMSException ex) {
                System.err.println("Exception following a client");
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                clientsLock.lock();
                return;
            }
        }
        clientsLock.unlock();
    }

    @Override
    public   String[] getFollowedTopics() {
        topicsLock.lock();
        Object[] keys = followedTopics.keySet().toArray();
        topicsLock.unlock();
        return (String[]) keys;
    }

    @Override
    public  String[] getFollowedClients() {
        clientsLock.lock();
        Object[] keys = followedClients.keySet().toArray();
        clientsLock.unlock();
        return (String[])keys;
    }

    @Override
    public  void unsubscribe(String topicName) {
        topicsLock.lock();
        if(followedTopics.containsKey(topicName)==false)
        {
            topicsLock.unlock();
            return;
        }
        MessageConsumer consumer = followedTopics.remove(topicName);
        try {
            System.out.println("closing consumer of topic: "+topicName);
            consumer.close();
        } catch (JMSException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            topicsLock.unlock();
        }
        topicsLock.unlock();
    }

    @Override
    public  void unfollow(String clientId) {
        clientsLock.lock();
        if(followedClients.containsKey(clientId)==false)
        {
            clientsLock.unlock();
            return;
        }
        MessageConsumer consumer = followedClients.remove(clientId);
        try {
            System.out.println("Unfollowing client:" +clientId);
            consumer.close();
        } catch (JMSException ex) {
            System.err.println("Exception Unfollowing client");
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            clientsLock.unlock();
        }
        clientsLock.unlock();
    }
    @Override
    public void setMessageListener(MessageListener listener)
    {
        this.msgListener = listener;
    }
    
}
