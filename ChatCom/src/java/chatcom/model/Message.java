package chatcom.model;
// Generated 17-mar-2018 13.20.00 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Message generated by hbm2java
 */
@Entity
@Table(name="message"
    ,catalog="chatcom"
)@XmlRootElement

public class Message  implements java.io.Serializable {


     private Integer id;
     private String data;
     private String type;
    // private Set<Instance> instances = new HashSet<Instance>(0);

    public Message() {
    }

	
    public Message(String data, String type) {
        this.data = data;
        this.type = type;
    }
    /*public Message(String data, String type, Set<Instance> instances) {
       this.data = data;
       this.type = type;
       this.instances = instances;
    }*/
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="data", nullable=false, length=256)
    public String getData() {
        return this.data;
    }
    
    public void setData(String data) {
        this.data = data;
    }

    
    @Column(name="type", nullable=false, length=8)
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

/*@OneToMany(fetch=FetchType.LAZY, mappedBy="message")
    public Set<Instance> getInstances() {
        return this.instances;
    }
    
    public void setInstances(Set<Instance> instances) {
        this.instances = instances;
    }*/




}


