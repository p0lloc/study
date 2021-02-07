package cc.pollo.study.model;

import lombok.Data;
import lombok.Getter;

/**
 * Question shown in StudySets
 */
@Data
public class Question {

    @Getter
    private String term;
    @Getter
    private String definition;

}