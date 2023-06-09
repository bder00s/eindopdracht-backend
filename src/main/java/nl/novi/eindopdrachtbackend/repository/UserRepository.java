package nl.novi.eindopdrachtbackend.repository;

import nl.novi.eindopdrachtbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


}
