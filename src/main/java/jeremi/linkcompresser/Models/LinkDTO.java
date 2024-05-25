package jeremi.linkcompresser.Models;

import java.util.List;

public class LinkDTO {

    private Integer linkId;
    private String link;
    private String password;
    private List<String> redirections;
    private int visits;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRedirections() {
        return redirections;
    }

    public void setRedirections(List<String> redirections) {
        this.redirections = redirections;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }
}
