package com.assessment.api.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AmanVerma
 */
@Entity
@Table(name = "geo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Geo.findAll", query = "SELECT g FROM Geo g")
    , @NamedQuery(name = "Geo.findById", query = "SELECT g FROM Geo g WHERE g.id = :id")
    , @NamedQuery(name = "Geo.findByLat", query = "SELECT g FROM Geo g WHERE g.lat = :lat")
    , @NamedQuery(name = "Geo.findByLng", query = "SELECT g FROM Geo g WHERE g.lng = :lng")})
public class Geo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lat")
    private String lat;
    @Column(name = "lng")
    private String lng;
    @OneToMany(mappedBy = "geo")
    private Collection<Address> addressCollection;

    public Geo() {
    }

    public Geo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @XmlTransient
    public Collection<Address> getAddressCollection() {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection) {
        this.addressCollection = addressCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Geo)) {
            return false;
        }
        Geo other = (Geo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitygenerator.Geo[ id=" + id + " ]";
    }
    
}
