package tech.suvraneel.projects.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.suvraneel.projects.entity.ProjectEntity;

@Repository
public interface ProjectDAO extends CrudRepository<ProjectEntity, String> {

}
