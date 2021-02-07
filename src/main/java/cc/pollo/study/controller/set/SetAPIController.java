package cc.pollo.study.controller.set;

import cc.pollo.study.controller.set.response.StudySetCreationResponse;
import cc.pollo.study.controller.set.response.StudySetUpdateResponse;
import cc.pollo.study.model.Question;
import cc.pollo.study.service.StudySetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Controller for StudySet-related API requests
 */
@Controller
public class SetAPIController {

    private final ObjectMapper objectMapper;
    private final StudySetService studySetService;

    public SetAPIController(ObjectMapper objectMapper, StudySetService studySetService){
        this.objectMapper    = objectMapper;
        this.studySetService = studySetService;
    }

    @PostMapping("/api/studysets/create")
    @ResponseBody
    public ResponseEntity<StudySetCreationResponse> createStudySet(@RequestParam("name") String name,
                                                                   @RequestParam("strict") boolean strict,
                                                                   @RequestParam("inOrder") boolean inOrder,
                                                                   @RequestParam("questions") String questionData) throws JsonProcessingException {

        List<Question> questions = objectMapper.readValue(questionData, new TypeReference<>(){});
        return ResponseEntity.ok(studySetService.createSet(name, strict, inOrder, questions));
    }

    @PostMapping("/api/studysets/update")
    public ResponseEntity<StudySetUpdateResponse> updateStudySet(@RequestParam("editToken") String editToken,
                                                                 @RequestParam("name") String name,
                                                                 @RequestParam("strict") boolean strict,
                                                                 @RequestParam("inOrder") boolean inOrder,
                                                                 @RequestParam("questions") String questionData) throws JsonProcessingException {

        List<Question> questions = objectMapper.readValue(questionData, new TypeReference<>(){});
        return ResponseEntity.ok( studySetService.updateSet(editToken, name, strict, inOrder, questions));
    }

}