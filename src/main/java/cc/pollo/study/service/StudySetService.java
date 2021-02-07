package cc.pollo.study.service;

import cc.pollo.study.controller.set.response.StudySetCreationResponse;
import cc.pollo.study.controller.set.response.StudySetUpdateResponse;
import cc.pollo.study.model.Question;
import cc.pollo.study.model.StudySet;
import cc.pollo.study.repository.StudySetRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service responsible for getting and creating sets, mainly a wrapper
 * with all required fields for the repository
 */
@Service
public class StudySetService {

    private final StudySetRepository setRepository;

    @Value("${study.editUrlFormat}")
    private String editUrlFormat;
    @Value("${study.studyUrlFormat}")
    private String studyUrlFormat;

    public StudySetService(StudySetRepository setRepository){
        this.setRepository = setRepository;
    }

    /**
     * Attempts to find a set by it's unique id
     * @param uniqueId unique id for set
     * @return StudySet associated with the id
     */
    public StudySet getSetByUniqueId(String uniqueId){
        return setRepository.findByUniqueId(uniqueId);
    }

    /**
     * Attempts to find a set by it's edit-token
     * @param editToken edit-token for set
     * @return StudySet associated with the edit-token
     */
    public StudySet getSetByEditToken(String editToken){
        return setRepository.findByEditToken(editToken);
    }

    /**
     * Creates a new StudySet
     * @param name name of the study set
     * @param strict whether or not the set is case and symbol sensitive
     * @param inOrder whether or not the questions should be given in order
     * @param questions list of questions to include in this set
     * @return creation response with set and metadata
     */
    public StudySetCreationResponse createSet(String name, boolean strict,
                                              boolean inOrder, List<Question> questions){

        StudySet studySet = new StudySet();

        String id = generateId();
        String editToken = generateId();

        studySet.setUniqueId(id);
        studySet.setName(name);
        studySet.setEditToken(editToken);
        studySet.setStrict(strict);
        studySet.setInOrder(inOrder);
        studySet.setQuestions(questions);

        setRepository.save(studySet);

        return StudySetCreationResponse.builder()
                .data(studySet)
                .studyUrl(String.format(studyUrlFormat, studySet.getUniqueId()))
                .editUrl(String.format(editUrlFormat, studySet.getEditToken()))
                .build();

    }

    /**
     * Updates an existing StudySet
     * @param editToken edit token for the set
     * @param name new name
     * @param strict new strict value
     * @param inOrder new inOrder value
     * @param questions new question value
     * @return update response with set or null if set not found
     */
    public StudySetUpdateResponse updateSet(String editToken, String name, boolean strict,
                                            boolean inOrder, List<Question> questions) {

        StudySet set = setRepository.findByEditToken(editToken);
        if(set == null)
            return StudySetUpdateResponse.builder().message("Invalid editToken.").build();

        set.setEditToken(editToken);
        set.setName(name);
        set.setStrict(strict);
        set.setInOrder(inOrder);
        set.setQuestions(questions);

        setRepository.save(set);

        return StudySetUpdateResponse.builder()
                .data(set)
                .build();
    }

    /**
     * Generates an appropriate id for StudySet
     * @return random UUID without dashes
     */
    private String generateId(){
        return UUID.randomUUID().toString()
                .replace("-", "");
    }

}