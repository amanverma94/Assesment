package com.assessment.api.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AmanVerma
 */
@Entity
@Table(name = "todos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todos.findAll", query = "SELECT t FROM Todos t")
    , @NamedQuery(name = "Todos.findById", query = "SELECT t FROM Todos t WHERE t.id = :id")
    , @NamedQuery(name = "Todos.findByTitle", query = "SELECT t FROM Todos t WHERE t.title = :title")
    , @NamedQuery(name = "Todos.findByCompleted", query = "SELECT t FROM Todos t WHERE t.completed = :completed")})
public class Todos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "completed")
    private Boolean completed;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne
    private UserDetails user;

    public Todos() {
    }

    public Todos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
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
        if (!(object instanceof Todos)) {
            return false;
        }
        Todos other = (Todos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitygenerator.Todos[ id=" + id + " ]";
    }
    
}
