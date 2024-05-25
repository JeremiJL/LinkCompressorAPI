package jeremi.linkcompresser.Services;

import jeremi.linkcompresser.Models.Link;
import jeremi.linkcompresser.Models.LinkDTO;
import jeremi.linkcompresser.Models.Redirection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOtoEntityMapper {

    public Link map(LinkDTO dto){

        // test
        System.out.println(dto.getLinkId());
        System.out.println(dto.getLink());
        System.out.println(dto.getPassword());

        Link link = new Link();
        link.setLink(dto.getLink());
        link.setPassword(dto.getPassword());
        link.setVisitCounter(dto.getVisits());

        // create redirection objects
        List<Redirection> redirections = new ArrayList<>();
        if (dto.getRedirections() != null) {
            for (String reURL : dto.getRedirections()) {
                Redirection redirection = new Redirection();
                redirection.setLink(link);
                redirection.setRedirection(reURL);

                redirections.add(redirection);
            }
        }

        link.setRedirections(redirections);

        return link;
    }

}
