package basePackage.repository;

import basePackage.entity.Message;
import basePackage.projection.MessageProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "message", excerptProjection = MessageProjection.class)
public interface MessageRepository extends JpaRepository<Message, Long> {
}
