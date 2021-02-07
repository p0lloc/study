package cc.pollo.study.controller.set.response;

import cc.pollo.study.model.StudySet;
import lombok.Builder;
import lombok.Data;

/**
 * Sent as a response to a successful study set update request
 */
@Data
@Builder
public class StudySetUpdateResponse {

    private StudySet data;
    private String message;

}