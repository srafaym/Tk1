/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

/**
 *
 * @author swallak
 */
public interface ConsumerInterface {
   
    
    /**
     * Subscribe to destination by adding it to followed topics
     * @param topicName
     */
    public void subscribe(String topicName);
    /**
     * remove from followed topics
     * @param topicName 
     */
    public void unsubscribe(String topicName);
    /**
     * Follow another user by adding it to followed users
     * subscription to users is durable
     * @param clientId clienId of target publisher
     */
    public void follow (String clientId);
    /**
     * unfollow client
     * @param clientId 
     */
    public void unfollow (String clientId);
    /**
     * 
     * @return array of names of the followed topics 
     */
    public String[] getFollowedTopics();
    /**
     * 
     * @return array of names of the followed users/clients 
     */
    public String[] getFollowedClients();
    /**
     * Setting the message listener for this consumer
     * @param listener 
     */
    public void setMessageListener(MessageListener listener);
}
