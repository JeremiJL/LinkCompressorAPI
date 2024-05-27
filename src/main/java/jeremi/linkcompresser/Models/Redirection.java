package jeremi.linkcompresser.Models;


import jakarta.persistence.*;

@Entity
public class Redirection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String redirectionURL;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Link link;

    public Redirection(String redirectionURL, Link link) {
        this.redirectionURL = redirectionURL;
        this.link = link;
    }

    public Redirection() {
    }

    public String getRedirectionURL() {
        return redirectionURL;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRedirectionURL(String redirection) {
        this.redirectionURL = redirection;
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
