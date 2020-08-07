package za.co.idealogic.javaexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.idealogic.javaexercise.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
