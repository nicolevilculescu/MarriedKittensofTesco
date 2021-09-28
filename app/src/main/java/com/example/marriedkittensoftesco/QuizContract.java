package com.example.marriedkittensoftesco;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract() {}

    public static class HighScoreTable implements BaseColumns {
        public static final String TABLE_NAME = "highscore";
        public static final String COLUMN_MUSIC = "music";
        public static final String COLUMN_MOVIES = "movies";
        public static final String COLUMN_BOOKS = "books";
        public static final String COLUMN_SPORTS = "sports";
        public static final String COLUMN_GAMES = "games";
        public static final String COLUMN_USER_ID = "user_id";
    }

    public static class UsersTable implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_FNAME = "first_name";
        public static final String COLUMN_LNAME = "last_name";
        public static final String COLUMN_LEVEL = "level";
    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
        public static final String CATEGORY = "category";
    }
}
