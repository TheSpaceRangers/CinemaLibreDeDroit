package fr.biohabitat.test.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_movies;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "path", length = 255)
    private String path;

    @Column(name = "like_movie")
    private int like_movie;

    @Column(name = "dislike_movie")
    private int dislike_movie;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    public int getDislikeMovie() {
        return dislike_movie;
    }
    public void setDislikeMovie(int dislike_movie) {
        this.dislike_movie = dislike_movie;
    }

    public int getLikeMovie() {
        return like_movie;
    }

    public void setLikeMovie(int like_movie) {
        this.like_movie = like_movie;
    }
}