package jeremi.linkcompresser.Repositories;

import jeremi.linkcompresser.Models.Link;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Integer> {

    List<Link> findAllByTargetURL(String targetURL);

}
