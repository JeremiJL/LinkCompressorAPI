package jeremi.linkcompresser.Services;

import jeremi.linkcompresser.Models.Redirection;
import jeremi.linkcompresser.Repositories.RedirectionRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RedirectService {

    RedirectionRepository redirectionRepository;

    public RedirectService(RedirectionRepository repository) {
        this.redirectionRepository = repository;
    }

    public String findTargetURL(String redirectionURL){
        Redirection redirection = redirectionRepository.findRedirectionByRedirectionURL(redirectionURL);
        if (redirection != null)
            return redirection.getLink().getTargetURL();
        throw new NoSuchElementException();
    }
}
