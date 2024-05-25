package jeremi.linkcompresser.Repositories;

import jeremi.linkcompresser.Models.Link;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<Link, Integer> {
}
