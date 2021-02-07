package cc.pollo.study.controller.set;

import cc.pollo.study.model.StudySet;
import cc.pollo.study.service.StudySetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller for views that users may request
 */
@Controller
public class ViewController {

    private final StudySetService setService;
    @Value("${study.studyUrlFormat}")
    private String studyUrlFormat;

    public ViewController(StudySetService setService) {
        this.setService = setService;
    }

    @GetMapping("/")
    public String serveIndex(){
        return "template_index";
    }

    @GetMapping("/{uniqueId}")
    public String serveView(Model model,
                            @PathVariable("uniqueId") String uniqueId){

        StudySet set = setService.getSetByUniqueId(uniqueId);
        if(set == null)
            return null;

        model.addAttribute("set", set);

        return "template_view";
    }

    @GetMapping("/create")
    public String serveCreateView(){
        return "template_create";

    }

    @GetMapping("/edit/{token}")
    public String serveEditView(Model model,
                                @PathVariable("token") String token){

        StudySet set = setService.getSetByEditToken(token);
        if(set == null)
            return "redirect:/";

        model.addAttribute("studyUrl", String.format(studyUrlFormat, set.getUniqueId()));
        model.addAttribute("set", set);

        return "template_edit";
    }

}