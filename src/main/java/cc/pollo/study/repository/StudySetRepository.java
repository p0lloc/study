package cc.pollo.study.repository;

import cc.pollo.study.model.StudySet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

/**
 * Repository responsible for retrieving and storing StudySets
 */
public interface StudySetRepository extends MongoRepository<StudySet, UUID> {

    StudySet findByUniqueId(String uniqueId);

    StudySet findByEditToken(String editToken);

}