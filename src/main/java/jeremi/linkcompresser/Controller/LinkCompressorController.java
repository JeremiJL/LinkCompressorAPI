package jeremi.linkcompresser.Controller;

import jeremi.linkcompresser.Models.LinkDTO;
import jeremi.linkcompresser.Services.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/links")
public class LinkCompressorController {

    private LinkService linkService;

    public LinkCompressorController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<LinkDTO> addLink(@RequestBody LinkDTO linkDTO) {
        linkService.addNewLink(linkDTO);
        return ResponseEntity.ok(linkDTO);
    }

    @GetMapping()
    public ResponseEntity<List<LinkDTO>> getLinks() {
        return ResponseEntity.ok(linkService.getLinks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkDTO> getLink(@PathVariable int id) {
        Optional<LinkDTO> dtoOptional =  linkService.getLink(id);
        return dtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}


