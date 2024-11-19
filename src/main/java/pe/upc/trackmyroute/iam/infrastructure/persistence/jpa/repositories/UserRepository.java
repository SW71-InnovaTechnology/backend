package pe.upc.trackmyroute.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.upc.trackmyroute.iam.domain.model.aggregates.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_has_roles WHERE user_id = :userId", nativeQuery = true)
    void deleteRolesByUserId(@Param("userId") Long userId);
}
