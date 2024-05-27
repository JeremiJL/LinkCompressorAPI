package jeremi.linkcompresser.Models;


import jakarta.persistence.*;

@Entity
public class Redirection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String redirection;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Link link;

    public Redirection(String redirection, Link link) {
        this.redirection = redirection;
        this.link = link;
    }

    public Redirection() {
    }

    public String getRedirection() {
        return redirection;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRedirection(String redirection) {
        this.redirection = redirection;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Integer getId() {
        return id;
    }


}
