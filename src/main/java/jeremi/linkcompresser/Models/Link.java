package jeremi.linkcompresser.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer linkId;
    private String link;
    private int visitCounter;
    private String password;
    @OneToMany(mappedBy = "link", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Redirection> redirections = new ArrayList<>();

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getVisitCounter() {
        return visitCounter;
    }

    public void setVisitCounter(int visitCounter) {
        this.visitCounter = visitCounter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Redirection> getRedirections() {
        return redirections;
    }

    public void setRedirections(List<Redirection> redirections) {
        this.redirections = redirections;
    }

}
