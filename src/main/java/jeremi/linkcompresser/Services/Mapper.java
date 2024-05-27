package jeremi.linkcompresser.Services;

import jeremi.linkcompresser.Models.Link;
import jeremi.linkcompresser.Models.LinkInputDTO;
import jeremi.linkcompresser.Models.LinkOutPutDTO;
import jeremi.linkcompresser.Models.Redirection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Mapper {

    public Link map(LinkInputDTO dto){
        Link link = new Link();
        link.setTargetURL(dto.getTargetURL());
        link.setPassword(dto.getPassword());
        link.setVisitCounter(dto.getVisits());

        // create redirection objects
        List<Redirection> redirections = new ArrayList<>();
        if (dto.getRedirectionsURL() != null) {
            for (String reURL : dto.getRedirectionsURL()) {
                Redirection redirection = new Redirection();
                redirection.setLink(link);
                redirection.setRedirectionURL(reURL);

                redirections.add(redirection);
            }
        }
        link.setRedirectionsURL(redirections);

        return link;
    }

    public LinkOutPutDTO map(Link link){
        LinkOutPutDTO dto = new LinkOutPutDTO();
        dto.setTargetURL(link.getTargetURL());
        dto.setRedirectionsURL(link.getRedirectionsURL().stream().map(Redirection::getRedirectionURL).toList());
        dto.setVisits(link.getVisitCounter());
        dto.setLinkId(link.getLinkId());

        return dto;
    }

}
