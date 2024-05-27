package jeremi.linkcompresser.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jeremi.linkcompresser.Models.*;
import jeremi.linkcompresser.Repositories.LinkRepository;
import jeremi.linkcompresser.Repositories.RedirectionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LinkService {

    private LinkRepository linkRepository;
    private RedirectionRepository redirectionRepository;
    private Mapper mapper;
    private ObjectMapper objectMapper;

    public LinkService(LinkRepository linkRepository, RedirectionRepository redirectionRepository, Mapper mapper, ObjectMapper objectMapper) {
        this.linkRepository = linkRepository;
        this.redirectionRepository = redirectionRepository;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    public LinkOutPutDTO addNewLink(LinkInputDTO linkInputDTO){
        Link link = mapper.map(linkInputDTO);
        // set visit counter to 0
        link.setVisitCounter(0);
        // this save generates the id
        linkRepository.save(link);
        // create default redirection based on generated link id
        createRedirectionFromId(link);
        // save on more time
        linkRepository.save(link);
        redirectionRepository.saveAll(link.getRedirectionsURL());

        return mapper.map(link);
    }

    public List<LinkOutPutDTO> getLinks(){
        List<LinkOutPutDTO> linksDTO = new ArrayList<>();
        for (Link l : linkRepository.findAll()){
            linksDTO.add(mapper.map(l));
        }
        return linksDTO;
    }

    public Optional<LinkOutPutDTO> getLink(int id){
        Optional<Link> link = linkRepository.findById(id);
        return link.map(value -> mapper.map(value));
    }

    public Optional<LinkOutPutDTO> removeLink(int id, PasswordDTO password) throws IllegalAccessException {
        Optional<Link> link = linkRepository.findById(id);
        Optional<LinkOutPutDTO> linkDTO = link.map(v -> mapper.map(v));
        if (linkDTO.isEmpty())
            return Optional.empty();
        else if (link.get().getPassword() == null || link.get().getPassword().equals(password.getPass())){
            link.get().getRedirectionsURL().forEach(r -> redirectionRepository.deleteById(r.getId()));
            linkRepository.deleteById(id);
            return linkDTO;
        } else {
            throw new IllegalAccessException();
        }
    }

    public Optional<LinkOutPutDTO> updateLink(int id, LinkInputDTO linkInputDTO, PasswordDTO password) throws IllegalAccessException {
        Optional<Link> link = linkRepository.findById(id);

        if (link.isEmpty()) {
            return Optional.empty();
        } else if (link.get().getPassword() == null || (password != null && link.get().getPassword().equals(password.getPass()))) {

            Link updatedLink = link.get();
            updatedLink.setPassword(linkInputDTO.getPassword());
            updatedLink.setTargetURL(linkInputDTO.getTargetURL());
            updatedLink.getRedirectionsURL().forEach(r -> redirectionRepository.deleteById(r.getId()));
            updatedLink.setRedirectionsURL(mapper.map(linkInputDTO).getRedirectionsURL());

            return Optional.of(mapper.map(updatedLink));
        } else {
            throw new IllegalAccessException();
        }
    }

    private void createRedirectionFromId(Link link){
        Redirection red = new Redirection(link.getLinkId() + "",link);
        link.getRedirectionsURL().add(red);
    }

}
