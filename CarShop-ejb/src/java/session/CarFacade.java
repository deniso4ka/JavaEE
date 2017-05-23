/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Car;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author den
 */
@Stateless
public class CarFacade extends AbstractFacade<Car> {
    
 

    @PersistenceContext(unitName = "CarShop-ejbPU")
    private EntityManager em;
    
    private Car car;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarFacade() {
        super(Car.class);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    
    
    
    public List<entity.Car> findByMake(String make) {
        if(make.isEmpty()){
            Query query = em.createNamedQuery("Car.findAll");
                    return query.getResultList();
        }
        else{
            Query query = em.createNamedQuery("Car.findByMake");
            query.setParameter("make", make);
            return query.getResultList();
        }
    }
    
      public List<String> getAllMakes(){
         
        Query query = em.createNamedQuery("Car.listMakes");
     return query.getResultList();
         
    }
      
      
      
      
      public List<entity.Car> findById(Integer id) {
        
     
        Query query = em.createNamedQuery("Car.findByCarid");
            query.setParameter("carid", id);
            return query.getResultList();
     
      }
      
          public boolean checkIfExistCar(Integer id) {
        
     
        Query query = em.createNamedQuery("Car.findByCarid");
            query.setParameter("carid", id);
            
            if(!query.getResultList().isEmpty()){

                    return true;
            } 
            return false;
     
      }

  
      public void insertNewCar(int carid,String make, String model, String yearmade,Double price,String color,String engine,String description,String image){
          
           car = new Car();
        
                 setCar(car);
                 car.setCarid(carid);
                 car.setMake(make);
                 car.setModel(model);
                 car.setYearmade(yearmade);
                 car.setPrice(price);
                 car.setColor(color);
                 car.setEngine(engine);
                 car.setDescription(description);
                 car.setImage(image);
                 create(getCar());
        
      
      }

    public void editCar(Integer carid, String make, String model, String yearmade, Double price, String color, String engine, String description, String image) {
       
          car = new Car();
        
                 setCar(car);
                 car.setCarid(carid);
                 car.setMake(make);
                 car.setModel(model);
                 car.setYearmade(yearmade);
                 car.setPrice(price);
                 car.setColor(color);
                 car.setEngine(engine);
                 car.setDescription(description);
                 car.setImage(image);
                 edit(getCar());
        
    }

    public void deleteCar(int carid) {
        
        //String queryString = "DELETE FROM Car c where c.model like "caridfordelete"";
       // String queryString = "DELETE FROM Car c WHERE c.carid = :carid";
        
//        Query query = em.createNamedQuery("Car.findByCarid");
//        query.setParameter("carid",carid);
//        Object result = query.getSingleResult();
        
       
        remove(find(carid));
       
        
        
    }

   
      
      
    

      
   
    
      
       
    
    
}
