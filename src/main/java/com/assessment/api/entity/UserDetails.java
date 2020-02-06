package com.assessment.api.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "user_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDetails.findAll", query = "SELECT u FROM UserDetails u")
    , @NamedQuery(name = "UserDetails.findById", query = "SELECT u FROM UserDetails u WHERE u.id = :id")
    , @NamedQuery(name = "UserDetails.findByName", query = "SELECT u FROM UserDetails u WHERE u.name = :name")
    , @NamedQuery(name = "UserDetails.findByUsername", query = "SELECT u FROM UserDetails u WHERE u.username = :username")
    , @NamedQuery(name = "UserDetails.findByEmail", query = "SELECT u FROM UserDetails u WHERE u.email = :email")
    , @NamedQuery(name = "UserDetails.findByPhone", query = "SELECT u FROM UserDetails u WHERE u.phone = :phone")
    , @NamedQuery(name = "UserDetails.findByWebsite", query = "SELECT u FROM UserDetails u WHERE u.website = :website")})
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "website")
    private String website;
    @OneToMany(mappedBy = "user")
    private Collection<Albums> albumsCollection;
    @JoinColumn(name = "address", referencedColumnName = "id")
    @ManyToOne
    private Address address;
    @JoinColumn(name = "company", referencedColumnName = "id")
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "user")
    private Collection<Todos> todosCollection;
    @OneToMany(mappedBy = "user")
    private Collection<Posts> postsCollection;

    public UserDetails() {
    }

    public UserDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @XmlTransient
    public Collection<Albums> getAlbumsCollection() {
        return albumsCollection;
    }

    public void setAlbumsCollection(Collection<Albums> albumsCollection) {
        this.albumsCollection = albumsCollection;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @XmlTransient
    public Collection<Todos> getTodosCollection() {
        return todosCollection;
    }

    public void setTodosCollection(Collection<Todos> todosCollection) {
        this.todosCollection = todosCollection;
    }

    @XmlTransient
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
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
        if (!(object instanceof UserDetails)) {
            return false;
        }
        UserDetails other = (UserDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    @Override
	public String toString() {
		return "UserDetails [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email
				+ ", phone=" + phone + ", website=" + website + ", address=" + address + ", company=" + company + "]";
	}
    
}
