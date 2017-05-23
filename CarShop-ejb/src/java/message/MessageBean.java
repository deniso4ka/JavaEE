/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author den
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/MyQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageBean implements MessageListener {
    
    public MessageBean() {
    }
    
    //  ObjectMessage om = (ObjectMessage) message;
      //  Serializable objectData = null;
      //  try {
       //     objectData = om.getObject();
        //    String objectName = om.getStringProperty("ObjectName");
        //    System.out.println("objectName:" + objectName);
    
    @Override
    public void onMessage(Message message)  {
         
        ObjectMessage msg = (ObjectMessage) message;
        Serializable objectData = null;
        //String objectName = null;
        
        try {
           
            objectData = msg.getObject();
             System.out.println(" "+ objectData.toString());
            //objectName = msg.getStringProperty("message");
            //System.out.println("message:" + objectName);
        }
           
         catch (JMSException ex) {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
