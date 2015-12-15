/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.Queue;

/**
 *
 * @author swallak
 */
public interface PublisherInterface {
    
    /**
     * Create a topic 
     * @param topicName
     * @return the topics destination
     */
    public Destination createTopic (String topicName);
    /**
     * Publish to a specific queue
     * @param queueName
     * @param msg 
     */
    public void publishToQueue (String queueName, Message msg);
    /**
     * Publish to a specific topic
     * @param topicName
     * @param msg 
     */
    public void publishToTopic (String  topicName, Message msg);
    /**
     * Publish to own queue
     * @param msg 
     */
    public void publish (Message msg);
}
