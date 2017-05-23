/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author den
 */
@Entity
@Table(name = "PURCHASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT p FROM Purchase p"),
    @NamedQuery(name = "Purchase.findByPurchaseid", query = "SELECT p FROM Purchase p WHERE p.purchasePK.purchaseid = :purchaseid"),
    @NamedQuery(name = "Purchase.findByPurchasedate", query = "SELECT p FROM Purchase p WHERE p.purchasedate = :purchasedate"),
    @NamedQuery(name = "Purchase.findByCustid", query = "SELECT p FROM Purchase p WHERE p.purchasePK.custid = :custid")})
public class Purchase implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PurchasePK purchasePK;
    @Column(name = "PURCHASEDATE")
    @Temporal(TemporalType.DATE)
    private Date purchasedate;
    @JoinColumn(name = "CARID", referencedColumnName = "CARID")
    @ManyToOne(optional = false)
    private Car carid;
    @JoinColumn(name = "CUSTID", referencedColumnName = "CUSTID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cust cust;

    public Purchase() {
    }

    public Purchase(PurchasePK purchasePK) {
        this.purchasePK = purchasePK;
    }

    public Purchase(int purchaseid, int custid) {
        this.purchasePK = new PurchasePK(purchaseid, custid);
    }

    public PurchasePK getPurchasePK() {
        return purchasePK;
    }

    public void setPurchasePK(PurchasePK purchasePK) {
        this.purchasePK = purchasePK;
    }

    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    public Car getCarid() {
        return carid;
    }

    public void setCarid(Car carid) {
        this.carid = carid;
    }

    public Cust getCust() {
        return cust;
    }

    public void setCust(Cust cust) {
        this.cust = cust;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchasePK != null ? purchasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Purchase)) {
            return false;
        }
        Purchase other = (Purchase) object;
        if ((this.purchasePK == null && other.purchasePK != null) || (this.purchasePK != null && !this.purchasePK.equals(other.purchasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Purchase[ purchasePK=" + purchasePK + " ]";
    }
    
}
