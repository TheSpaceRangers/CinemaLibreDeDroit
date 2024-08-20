package fr.biohabitat.test.controllers;

import fr.biohabitat.test.entities.Movie;
import fr.biohabitat.test.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieRepository movieRepository;

    @GetMapping("/movies/list")
    public String seeAllMovie(Model model) {
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        return "movies";
    }
}
