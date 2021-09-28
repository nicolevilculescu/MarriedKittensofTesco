package com.example.marriedkittensoftesco;

public class Highscore {
    private int music;
    private int movies;
    private int books;
    private int sports;
    private int games;
    private int user_id;

    public Highscore() {}

    public Highscore(int music, int movies, int books, int sports, int games, int user_id) {
        this.music = music;
        this.movies = movies;
        this.books = books;
        this.sports = sports;
        this.games = games;
        this.user_id = user_id;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    public int getMovies() {
        return movies;
    }

    public void setMovies(int movies) {
        this.movies = movies;
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }

    public int getSports() {
        return sports;
    }

    public void setSports(int sports) {
        this.sports = sports;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
