package cc.pollo.study.controller.set.response;

import cc.pollo.study.model.StudySet;
import lombok.Builder;
import lombok.Data;

/**
 * Sent as a response to a successful study set creation request
 */
@Builder
@Data
public class StudySetCreationResponse {

    private StudySet data;
    private String studyUrl;
    private String editUrl;

}