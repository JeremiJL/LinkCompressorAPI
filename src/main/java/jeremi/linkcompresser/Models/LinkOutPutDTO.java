package jeremi.linkcompresser.Models;

import java.util.List;

public class LinkOutPutDTO {

    private Integer linkId;
    private String targetURL;
    private List<String> redirectionsURL;
    private int visits;

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public List<String> getRedirectionsURL() {
        return redirectionsURL;
    }

    public void setRedirectionsURL(List<String> redirectionsURL) {
        this.redirectionsURL = redirectionsURL;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }
}
