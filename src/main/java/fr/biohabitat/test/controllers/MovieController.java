package fr.biohabitat.test.controllers;

import fr.biohabitat.test.entities.Movie;
import fr.biohabitat.test.repository.MovieRepository;
import fr.biohabitat.test.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService service;

    @GetMapping("/movies/list")
    public String seeAllMovie(Model model) {
        List<Movie> movies = service.getAll();
        model.addAttribute("movies", movies);
        return "movies";
    }

    @GetMapping("/movies/{id}")
    public String seeMovie(@PathVariable long id, Model model) {
        Movie movie = service.getById(id);
        System.out.println(movie);
        model.addAttribute("movie", movie);
        return "seeMovie";
    }

    @GetMapping("/search")
    public String searchMovie(@RequestParam String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
        List<Movie> movies = service.getByContainerTitle(title, page, size);
        int totalPages = service.getTotalPages(title, size);
        model.addAttribute("movies", movies);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        return "search";
    }

}
