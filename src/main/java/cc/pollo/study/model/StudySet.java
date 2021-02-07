package cc.pollo.study.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Study set created, modified and shown to users
 * Contains all information about the set
 */
@Data
public class StudySet {

    @Id
    private String uniqueId;

    private String name;
    private String editToken;
    private boolean strict;
    private boolean inOrder;
    private List<Question> questions;

}