package jeremi.linkcompresser.Services;

import jeremi.linkcompresser.Models.Link;
import jeremi.linkcompresser.Models.LinkDTO;
import jeremi.linkcompresser.Repositories.LinkRepository;
import jeremi.linkcompresser.Repositories.RedirectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

    private LinkRepository linkRepository;
    private RedirectionRepository redirectionRepository;
    private Mapper mapper;

    public LinkService(LinkRepository linkRepository, RedirectionRepository redirectionRepository, Mapper mapper) {
        this.linkRepository = linkRepository;
        this.redirectionRepository = redirectionRepository;
        this.mapper = mapper;
    }

    public void addNewLink(LinkDTO linkDTO){
        Link link = mapper.map(linkDTO);
        linkRepository.save(link);
        redirectionRepository.saveAll(link.getRedirections());
    }

    public List<LinkDTO> getLinks(){
        List<LinkDTO> linksDTO = new ArrayList<>();
        for (Link l : linkRepository.findAll()){
            linksDTO.add(mapper.map(l));
        }
        return linksDTO;
    }

    public Optional<LinkDTO> getLink(int id){
        Optional<Link> link = linkRepository.findById(id);
        return link.map(value -> mapper.map(value));
    }

}
