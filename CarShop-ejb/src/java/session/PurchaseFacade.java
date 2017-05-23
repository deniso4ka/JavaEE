/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Car;
import entity.Cust;
import entity.Purchase;
import entity.PurchasePK;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author den
 */
@Stateless
public class PurchaseFacade extends AbstractFacade<Purchase> {

    @PersistenceContext(unitName = "CarShop-ejbPU")
    private EntityManager em;
    private Purchase purchase;
    private Car car;
    private Cust cust;

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseFacade() {
        super(Purchase.class);
    }
    
   
    public void addPurchase(int carid, int custid){
        
        
    Purchase purchaseadd= new Purchase(carid,custid);
 
   
   car = em.find(Car.class, carid);
   cust = em.find(Cust.class, custid);
   
   
   purchaseadd.setPurchasedate(new Date());
   
     int id = getNewId();
   
   PurchasePK pk = new PurchasePK(id,custid);
   
   purchaseadd.setPurchasePK(pk);
   purchaseadd.setCarid(car);
   purchaseadd.setCust(cust);
 
    create(purchaseadd);
   
    
    
    }     
    
  public int getNewId(){
        int id = 1;
        
        
        while(true){
            Query query = em.createNamedQuery("Purchase.findByCustid");
            query.setParameter("custid", id);
            if(query.getResultList().isEmpty()){

                    return id;
            }   else     

                id++;
            }
        }
    public void deleteTransaction(int transactionId, int customerId){
      
       PurchasePK pk = new PurchasePK(transactionId,customerId);
      
    
       
        remove(find(pk));
        
    }
    
    
}
