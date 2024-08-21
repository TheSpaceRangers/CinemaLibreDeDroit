package fr.biohabitat.test.repository;

import fr.biohabitat.test.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByTitleContaining(String title, Pageable pageable);
    int countByTitleContaining(String title);
    @Query(value = "SELECT * FROM Movies ORDER BY like_movie DESC LIMIT :top", nativeQuery = true)
    List<Movie> findTopMoviesByLike(@Param("top") int top);

    @Query(value = "SELECT * FROM Movies ORDER BY created_at DESC LIMIT :top", nativeQuery = true)
    List<Movie> findTopMoviesByDate(@Param("top") int top);
}
