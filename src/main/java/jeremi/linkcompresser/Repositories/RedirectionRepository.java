package jeremi.linkcompresser.Repositories;

import jeremi.linkcompresser.Models.Redirection;
import org.springframework.data.repository.CrudRepository;

public interface RedirectionRepository extends CrudRepository<Redirection, Integer> {


     Redirection findRedirectionByRedirectionURL(String redirectionURL);

}
