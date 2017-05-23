/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Cust;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Schedule;

import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;                                                                                                                                                                                                                                                                                                                                                                                                                                                    
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author den
 */
@Stateless
public class CustFacade extends AbstractFacade<Cust> {

    @PersistenceContext(unitName = "CarShop-ejbPU")
    
    private EntityManager em;
    
    private Cust customers;

 @Resource(mappedName="jms/ConnectionFactory")
private ConnectionFactory connectionFactory;
@Resource(mappedName="jms/MyQueue")
private Queue queue;
 private Connection connection;
 private Session session;
 MessageProducer messageProducer;
 ObjectMessage message;
    
 
     @Schedule(hour="*", minute="*",second = "*/59")
        public void logTheMessage(){
            System.out.println("Method invokes at"+ new Date(System.currentTimeMillis()));
        }
        

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    

    public CustFacade(){
        
        super(Cust.class);    
        
        
       
    }

 

    public Cust getCustomers() {
        return customers;
    }

    public void setCustomers(Cust customers) {
        this.customers = customers;
    }
    public int insertCustomer(String name,String surname, String street, String city, String county, String country,Date date,String email,String phone){
       // List<Cust> customerList = new ArrayList<>();
       
        int id = getNewId();
        
       
        //Cust tempCustomer;
        
        //tempCustomer = new Cust();
        
        customers = new Cust();
        
        //customers.setCustid(id++);
        customers.setCustid(id);
        customers.setCustname(name);
         customers.setSurname(surname);
         customers.setStreet(street);
         customers.setCity(city);
         customers.setCounty(county);
         customers.setCountry(country);
         customers.setDob(date);
         customers.setPhone(phone);
         customers.setEmail(email);
        
        
                 setCustomers(customers);
                 
                 
                 create(getCustomers());
               
                
                   // }
        return id;
    }
    public void updateCustomer(){
        
    }
    
    //just to get apropriate id for customer
    public int getNewId(){
        int id = 1;
        
        
        while(true){
            Query query = em.createNamedQuery("Cust.findByCustid");
            query.setParameter("custid", id);
            if(query.getResultList().isEmpty()){

                    return id;
            }   else     

                id++;
            }
        }
    
      public void sendMessage(String name, String email) throws JMSException{
          
       
        
   connection = connectionFactory.createConnection();
   session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
   messageProducer = session.createProducer(queue);
   message = session.createObjectMessage();
      
String myMessage ="Thanks for the purchase "+name+" confirmation has been send to "+ email+" thanks for the purchase!!!";

message.setObject(myMessage);                
messageProducer.send(message);
messageProducer.close();
connection.close();
        
    }
    
    
     public void deleteCustomer(int custId){
         
         remove(find(custId));
         
     }
     
     public List<entity.Cust> searchCustomer(String custname,String city){
         
         
         String queryString = "select c from Cust c where c.custname = :custname and c.city = :city";
           Query query = em.createQuery(queryString);
           query.setParameter("custname", custname);
           query.setParameter("city", city);
           
           List<entity.Cust> resultList = query.getResultList();
                    return resultList;
         
         
         
     }
    
    
}
