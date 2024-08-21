package fr.biohabitat.test.service;

import fr.biohabitat.test.repository.MovieRepository;
import fr.biohabitat.test.entities.Movie; // Adjust the package according to your project structure
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public final List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public final Movie getById(long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public final List<Movie> getByContainerTitle(String title, int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        System.out.println(pageable);
        Page<Movie> moviePage = movieRepository.findByTitleContaining(title, pageable);
        System.out.println(moviePage);
        return moviePage.getContent();
    }

    public final int getTotalPages(String title, int size) {
        int totalCount = movieRepository.countByTitleContaining(title);
        return (int) Math.ceil((double) totalCount / size);
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public final List<Movie> getByLike(int top) {
        return movieRepository.findTopMoviesByLike(top);
    }

    public final List<Movie> getByDate(int top) {
        return movieRepository.findTopMoviesByDate(top);
    }
}
