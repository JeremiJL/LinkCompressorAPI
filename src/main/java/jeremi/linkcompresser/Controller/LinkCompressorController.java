package jeremi.linkcompresser.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jeremi.linkcompresser.Models.LinkInputDTO;
import jeremi.linkcompresser.Models.LinkOutPutDTO;
import jeremi.linkcompresser.Models.PasswordDTO;
import jeremi.linkcompresser.Services.LinkService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path="/api/v1/links",
        produces = {MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE})
public class LinkCompressorController {

    private LinkService linkService;

    public LinkCompressorController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping
    public ResponseEntity<LinkOutPutDTO> addLink(@RequestBody LinkInputDTO linkInputDTO) {
        LinkOutPutDTO savedLinkDTO = linkService.addNewLink(linkInputDTO);
        URI savedLinkLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLinkDTO.getLinkId())
                .toUri();
        return ResponseEntity.created(savedLinkLocation).body(savedLinkDTO);
    }

    @GetMapping()
    public ResponseEntity<List<LinkOutPutDTO>> getLinks() {
        return ResponseEntity.ok(linkService.getLinks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkOutPutDTO> getLink(@PathVariable int id) {
        Optional<LinkOutPutDTO> dtoOptional =  linkService.getLink(id);
        return dtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LinkOutPutDTO> removeLink(@PathVariable int id, @RequestBody(required = false) PasswordDTO password){
        try {
            Optional<LinkOutPutDTO> dtoOptional = linkService.removeLink(id,password);
            return dtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
        } catch (IllegalAccessException iae){
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}/{password}")
    public ResponseEntity<LinkOutPutDTO> updateLink(@PathVariable int id, @RequestBody LinkInputDTO linkDTO, @PathVariable String password) {
        try {
            Optional<LinkOutPutDTO> dtoOptional = linkService.updateLink(id,linkDTO,new PasswordDTO(password));
            return dtoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalAccessException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LinkOutPutDTO> updateLink(@PathVariable int id, @RequestBody LinkInputDTO linkDTO) {
        return updateLink(id,linkDTO,null);
    }







}


