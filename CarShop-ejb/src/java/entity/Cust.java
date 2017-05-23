/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author den
 */
@Entity
@Table(name = "CUST")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cust.findAll", query = "SELECT c FROM Cust c"),
    @NamedQuery(name = "Cust.findByCustid", query = "SELECT c FROM Cust c WHERE c.custid = :custid"),
    @NamedQuery(name = "Cust.findByCustname", query = "SELECT c FROM Cust c WHERE c.custname = :custname"),
    @NamedQuery(name = "Cust.findBySurname", query = "SELECT c FROM Cust c WHERE c.surname = :surname"),
    @NamedQuery(name = "Cust.findByStreet", query = "SELECT c FROM Cust c WHERE c.street = :street"),
    @NamedQuery(name = "Cust.findByCity", query = "SELECT c FROM Cust c WHERE c.city = :city"),
    @NamedQuery(name = "Cust.findByCounty", query = "SELECT c FROM Cust c WHERE c.county = :county"),
    @NamedQuery(name = "Cust.findByCountry", query = "SELECT c FROM Cust c WHERE c.country = :country"),
    @NamedQuery(name = "Cust.findByDob", query = "SELECT c FROM Cust c WHERE c.dob = :dob"),
    @NamedQuery(name = "Cust.findByPhone", query = "SELECT c FROM Cust c WHERE c.phone = :phone"),
    @NamedQuery(name = "Cust.findByEmail", query = "SELECT c FROM Cust c WHERE c.email = :email")})
public class Cust implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTID")
    private Integer custid;
    @Size(max = 45)
    @Column(name = "CUSTNAME")
    private String custname;
    @Size(max = 45)
    @Column(name = "SURNAME")
    private String surname;
    @Size(max = 45)
    @Column(name = "STREET")
    private String street;
    @Size(max = 45)
    @Column(name = "CITY")
    private String city;
    @Size(max = 45)
    @Column(name = "COUNTY")
    private String county;
    @Size(max = 45)
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
   // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "PHONE")
    private String phone;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cust")
    private Collection<Purchase> purchaseCollection;

    public Cust() {
    }

    public Cust(Integer custid) {
        this.custid = custid;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Purchase> getPurchaseCollection() {
        return purchaseCollection;
    }

    public void setPurchaseCollection(Collection<Purchase> purchaseCollection) {
        this.purchaseCollection = purchaseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custid != null ? custid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cust)) {
            return false;
        }
        Cust other = (Cust) object;
        if ((this.custid == null && other.custid != null) || (this.custid != null && !this.custid.equals(other.custid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cust[ custid=" + custid + " ]";
    }
    
}
