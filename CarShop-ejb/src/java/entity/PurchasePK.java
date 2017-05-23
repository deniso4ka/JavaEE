/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author den
 */
@Embeddable
public class PurchasePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "PURCHASEID")
    private int purchaseid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTID")
    private int custid;

    public PurchasePK() {
    }

    public PurchasePK(int purchaseid, int custid) {
        this.purchaseid = purchaseid;
        this.custid = custid;
    }

    public int getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(int purchaseid) {
        this.purchaseid = purchaseid;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) purchaseid;
        hash += (int) custid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasePK)) {
            return false;
        }
        PurchasePK other = (PurchasePK) object;
        if (this.purchaseid != other.purchaseid) {
            return false;
        }
        if (this.custid != other.custid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PurchasePK[ purchaseid=" + purchaseid + ", custid=" + custid + " ]";
    }
    
}
