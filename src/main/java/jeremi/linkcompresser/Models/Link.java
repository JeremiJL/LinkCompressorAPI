package jeremi.linkcompresser.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer linkId;
    private String targetURL;
    private int visitCounter;
    private String password;
    @OneToMany(mappedBy = "link", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Redirection> redirectionsURL = new ArrayList<>();

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String link) {
        this.targetURL = link;
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

    public List<Redirection> getRedirectionsURL() {
        return redirectionsURL;
    }

    public void setRedirectionsURL(List<Redirection> redirections) {
        this.redirectionsURL = redirections;
    }

}
