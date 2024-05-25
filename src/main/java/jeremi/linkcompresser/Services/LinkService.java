package jeremi.linkcompresser.Services;

import jeremi.linkcompresser.Models.Link;
import jeremi.linkcompresser.Models.LinkDTO;
import jeremi.linkcompresser.Models.Redirection;
import jeremi.linkcompresser.Repositories.LinkRepository;
import jeremi.linkcompresser.Repositories.RedirectionRepository;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    private LinkRepository linkRepository;
    private RedirectionRepository redirectionRepository;
    private DTOtoEntityMapper dtoToEntityMapper;

    public LinkService(LinkRepository linkRepository, RedirectionRepository redirectionRepository, DTOtoEntityMapper dtoToEntityMapper) {
        this.linkRepository = linkRepository;
        this.redirectionRepository = redirectionRepository;
        this.dtoToEntityMapper = dtoToEntityMapper;
    }

    public Boolean addNewLink(LinkDTO linkDTO){
        Link link = dtoToEntityMapper.map(linkDTO);
        linkRepository.save(link);
        redirectionRepository.saveAll(link.getRedirections());

        return true;
    }

}
