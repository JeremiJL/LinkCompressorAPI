package jeremi.linkcompresser.Controller;

import jeremi.linkcompresser.Services.LinkService;
import jeremi.linkcompresser.Services.RedirectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/red")
public class RedirectController {

    RedirectService redirectService;
    LinkService linkService;

    public RedirectController(RedirectService redirectService, LinkService linkService) {
        this.redirectService = redirectService;
        this.linkService = linkService;
    }

    @GetMapping("/{redirection}")
    public RedirectView goRedirect(@PathVariable String redirection) {
        try {
            String targetUrl = redirectService.findTargetURL(redirection);
            linkService.incrementVisitCounter(targetUrl);
            return new RedirectView(targetUrl);
        } catch (NoSuchElementException e) {
            return new RedirectView("/error");
        }

    }

}
