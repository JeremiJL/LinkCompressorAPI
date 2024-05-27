package jeremi.linkcompresser.Models;

import java.util.List;

public class LinkInputDTO {

    private String targetURL;
    private String password;
    private List<String> redirectionsURL;
    private int visits;

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
