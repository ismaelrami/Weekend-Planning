package fr.istic.m2il.weekendplanning.repository;

import fr.istic.m2il.weekendplanning.domain.Activity;
import fr.istic.m2il.weekendplanning.domain.Place;
import fr.istic.m2il.weekendplanning.domain.User;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLogin(String login);
    

    Page<User> findAllByLoginNot(Pageable pageable, String login);

    @EntityGraph(attributePaths = "authorities")
    User findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames="users")
    Optional<User> findOneWithAuthoritiesByLogin(String login);

    @Query("SELECT u FROM User u JOIN u.activities a JOIN u.places p WHERE a.name LIKE :aname AND p.nom LIKE :pname")
    List<User> findAllByActivityAndPlace(@Param("aname") String aname, @Param("pname") String pname);
    
   
}
