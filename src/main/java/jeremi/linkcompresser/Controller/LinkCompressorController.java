package jeremi.linkcompresser.Controller;

import jeremi.linkcompresser.Models.LinkDTO;
import jeremi.linkcompresser.Services.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/links/")
public class LinkCompressorController {

    private LinkService linkService;

    public LinkCompressorController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<LinkDTO> addLink(@RequestBody LinkDTO linkDTO) {
        if (linkService.addNewLink(linkDTO))
            return ResponseEntity.ok(linkDTO);
        else
            return ResponseEntity.badRequest().build();
    }


}


