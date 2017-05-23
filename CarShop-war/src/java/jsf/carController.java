/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.jms.JMSException;
import session.CarFacade;
import session.CustFacade;
import session.PurchaseFacade;



@ManagedBean
@SessionScoped
//@Named(value = "carController")
//@Dependent
public class carController {

/**
    /**
     * Creates a new instance of carController
     */
    
     private DataModel makes = null;
     private DataModel carpicked = null;
     private DataModel customerlist = null;
     private DataModel transactionList = null;
     private Integer idOfPickedCar = 0;
    private String makeFilter = "all";
   
    //variables to store temperary customer details
     private String custName;
     private String custSurname;
     private String street;
     private String city;
     private String county;
     private String country;
     private Date date;
     private String email;
     private String phone;
     //variables for storing temporaruy details for administrator
     private Integer carid;
     private String make;
     private String model;
     private String yearmade;
     private Double price;
     private String color;
     private String engine;
     private String description;
     private String image;
     private int adminChosenCarId;
      private int adminChosenCustomerId;
      private int adminChosenTransacionId;
      private String adminChosenCustomerName;
      private String adminChosenCustomerCity;
    
     
    @EJB
    private CustFacade ejbCustFacade;
    
    @EJB
    private CarFacade ejbCarFacade;
    
    @EJB
    private PurchaseFacade ejbPurchaseFacade;

    
    public carController() throws JMSException {
        
 
    }

 
    
    public String pageRedirect(){
          
  
    String pageName = "iderror";
     
       boolean exist = ejbCarFacade.checkIfExistCar(idOfPickedCar);
        
        if(exist){
            getPickedCar();
            pageName = "purchase";
        }
        
        return pageName;
        
    }
 public String redirectToTheFormPage(){
        
        String pageName = "form";
        
        if(idOfPickedCar==0){
            pageName = "error";
        }
        
        return pageName;
        
    }
 
  public String redirectToTheThanksPage(){
        
        String pageName = "bill";
        
        if(idOfPickedCar==0){
            pageName = "error";
        }
        
        return pageName;
        
    }
  
  public String editCarPage(){
      getPickedCar();
     
      return "editCar";
  }
    
     public String addEditedCar(){
      
        ejbCarFacade.editCar(carid,make,model,yearmade,price,color,engine,description,image);
     
      return "success";
  }
    
    public void setIdOfPickedCar(Integer idOfPickedCar) {
        this.idOfPickedCar = idOfPickedCar;
    }
        
      public String updateMakes(){
          
        makes = getMakes();
        return "index";
    }
    

    public DataModel getMakes() {
               
        if (getMakeFilter().equals("all")) {
        makes = new ListDataModel(getEjbCarFacade().findAll()); 
        } else {
        makes = new ListDataModel(getEjbCarFacade().findByMake(getMakeFilter()));
        }


        return makes;
    }
    
    public String displayCustomers(){
        
        getListCustomers();
        
        return "displaycustomers";
    }
    
     public String displayTransactions(){
         
         getListTransactions();
        
        return "displaytransactions";
    }
    
    // to get picked car call carFacade method findById and pass id of picked car
    
    public DataModel getListCustomers(){
        
        customerlist = new ListDataModel(getEjbCustFacade().findAll());
                
                return customerlist;
    }
    
    public DataModel getListTransactions(){
        
        transactionList = new ListDataModel(getEjbPurchaseFacade().findAll());
                
                return transactionList;
    }
    
    
     public DataModel getPickedCar() {
        
        carpicked = new ListDataModel(getEjbCarFacade().findById(idOfPickedCar));
      
        return carpicked;
    }
      
     
    
     
    public List<SelectItem> getAllMakes(){

//create an array list for the HTML form
  List<SelectItem> makes = new ArrayList<SelectItem>();
//Call a session bean method to return a collection of Strings / State codes
  Collection<String> makeList = getEjbCarFacade().getAllMakes();
   for (Iterator<String> iter = makeList.iterator(); iter.hasNext(); ) {
String carMake = iter.next();
       makes.add(new SelectItem(carMake, carMake));   //label and value
   }
   return makes;
}
    
    public void insertTemporaryCustomers() throws JMSException{
       
     String custname = getCustName();
     String custsurname = getCustSurname();
     String custstreet = getStreet();
     String custcity = getCity();
     String custcounty = getCounty();
     String custcountry = getCountry();
     Date custdate = getDate();
     String custemail = getEmail();
     String custphone = getPhone();
     
     
       
      //Cust cust = ejbCustFacade.insertCustomer(custname,custsurname,custstreet,custcity,custcounty,custcountry,custdate,custemail,custphone);
      int custId = ejbCustFacade.insertCustomer(custname,custsurname,custstreet,custcity,custcounty,custcountry,custdate,custemail,custphone);
          
     // int carid = car.getCarid();
     int carid =  getIdOfPickedCar();
        
     System.out.println("Customer id is " +custId + " car id is " +carid);
     
     ejbPurchaseFacade.addPurchase(carid,custId);
     ejbCustFacade.sendMessage(custname,custemail);
       
   }
    
    public String insertCarRow(){
  
     ejbCarFacade.insertNewCar(carid,make,model,yearmade,price,color,engine,description,image);
        
             
      return "success";   
    }
    public String deleteRow(){
        
         getPickedCar();
         
         int caridfordelete = idOfPickedCar;
         
        ejbCarFacade.deleteCar(caridfordelete);
        
       
        return "deletesuccess";
    }

     public String deleteCustomerRow(){
        
       
         
         int customeridfordelete = adminChosenCustomerId;
         
        ejbCustFacade.deleteCustomer(customeridfordelete);
        
       
        return "deletedsuccess";
    }
        public String deleteTransactionRow(){
        
       
         
       
         
           ejbPurchaseFacade.deleteTransaction(adminChosenTransacionId,adminChosenCustomerId);
       
        
       
        return "deletedsuccess";
    }
        
        public String searchCustomer(){
            //adminChosenCustomerName
                    //adminChosenCustomerCity
                    getSearchedCustomer();
                    
                    
            return "search";
        }
        
        public DataModel getSearchedCustomer(){
        
        customerlist = new ListDataModel(ejbCustFacade.searchCustomer(adminChosenCustomerName,adminChosenCustomerCity));
                
                return customerlist;
    }
        
        
           
      public void validateEmail(FacesContext facesContext, UIComponent uIComponent, Object value) throws
        ValidatorException {
          Pattern pattern = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
            Matcher matcher = pattern.matcher((CharSequence) value); 

        if (!matcher.matches()) {
    FacesMessage facesMessage = new FacesMessage("Invalid email");
 
            throw new ValidatorException(facesMessage);
 } 
    
}

    public String getAdminChosenCustomerName() {
        return adminChosenCustomerName;
    }

    public void setAdminChosenCustomerName(String adminChosenCustomerName) {
        this.adminChosenCustomerName = adminChosenCustomerName;
    }

    public String getAdminChosenCustomerCity() {
        return adminChosenCustomerCity;
    }

    public void setAdminChosenCustomerCity(String adminChosenCustomerCity) {
        this.adminChosenCustomerCity = adminChosenCustomerCity;
    }
     
   
    public int getAdminChosenCustomerId() {
        return adminChosenCustomerId;
    }

    public void setAdminChosenCustomerId(int adminChosenCustomerId) {
        this.adminChosenCustomerId = adminChosenCustomerId;
    }
    
    

    public DataModel getCustomerlist() {
        return customerlist;
    }

    public void setCustomerlist(DataModel customerlist) {
        this.customerlist = customerlist;
    }

    public DataModel getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(DataModel transactionList) {
        this.transactionList = transactionList;
    }
    
    
    
    
        public int getAdminChosenCarId() {
        return adminChosenCarId;
    }

    public void setAdminChosenCarId(int adminChosenCarId) {
        this.adminChosenCarId = adminChosenCarId;
    }
    
    

    public DataModel getCarpicked() {
        return carpicked;
    }

    public void setCarpicked(DataModel carpicked) {
        this.carpicked = carpicked;
    }

    public Integer getIdOfPickedCar() {
        return idOfPickedCar;
    }

    public void setMakes(DataModel makes) {
        this.makes = makes;
    }

    public String getMakeFilter() {
        return makeFilter;
    }

    public void setMakeFilter(String makeFilter) {
        this.makeFilter = makeFilter;
    }

    public CarFacade getEjbCarFacade() {
        return ejbCarFacade;
    }

    public void setEjbCarFacade(CarFacade ejbCarFacade) {
        this.ejbCarFacade = ejbCarFacade;
    }

    

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSurname() {
        return custSurname;
    }

    public void setCustSurname(String custSurname) {
        this.custSurname = custSurname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getAdminChosenTransacionId() {
        return adminChosenTransacionId;
    }

    public void setAdminChosenTransacionId(int adminChosenTransacionId) {
        this.adminChosenTransacionId = adminChosenTransacionId;
    }
    
    

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustFacade getEjbCustFacade() {
        return ejbCustFacade;
    }

    public void setEjbCustFacade(CustFacade ejbCustFacade) {
        this.ejbCustFacade = ejbCustFacade;
    }  

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearmade() {
        return yearmade;
    }

    public void setYearmade(String yearmade) {
        this.yearmade = yearmade;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PurchaseFacade getEjbPurchaseFacade() {
        return ejbPurchaseFacade;
    }

    public void setEjbPurchaseFacade(PurchaseFacade ejbPurchaseFacade) {
        this.ejbPurchaseFacade = ejbPurchaseFacade;
    }
    
 

      
    
  
    
}
