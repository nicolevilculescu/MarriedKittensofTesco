package com.example.marriedkittensoftesco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "extraCategory";
    public static final String EXTRA_ID = "extraId";

    private TextView hs_music;
    private TextView hs_movies;
    private TextView hs_books;
    private TextView hs_sports;
    private TextView hs_games;
    private TextView level;
    private TextView name;

    private TextView Music, Movies, Books, Sports, Games;
    private ImageView i_Music, i_Movies, i_Books, i_Sports, i_Games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Music = (TextView) findViewById(R.id.music_txt);
        i_Music = (ImageView) findViewById(R.id.music_img);
        Movies = (TextView) findViewById(R.id.movies_txt);
        i_Movies = (ImageView) findViewById(R.id.movies_img);
        Books = (TextView) findViewById(R.id.books_txt);
        i_Books = (ImageView) findViewById(R.id.books_img);
        Sports = (TextView) findViewById(R.id.sports_txt);
        i_Sports = (ImageView) findViewById(R.id.sports_img);
        Games = (TextView) findViewById(R.id.games_txt);
        i_Games = (ImageView) findViewById(R.id.games_img);

        hs_music = (TextView) findViewById(R.id.hs_music);
        hs_movies = (TextView) findViewById(R.id.hs_movies);
        hs_books = (TextView) findViewById(R.id.hs_books);
        hs_sports = (TextView) findViewById(R.id.hs_sports);
        hs_games = (TextView) findViewById(R.id.hs_games);

        level = (TextView) findViewById(R.id.level);
        name = (TextView) findViewById(R.id.name);

        loadHighscore();

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_ID, 0);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);

        level.setText("Level: " + dbHelper.findLevel(id));
        name.setText("Hello, " + dbHelper.findName(id));

        if(dbHelper.findLevel(id) < 2) {
            Movies.setVisibility(View.INVISIBLE);
            i_Movies.setVisibility(View.INVISIBLE);
            hs_movies.setVisibility(View.INVISIBLE);
            Books.setVisibility(View.INVISIBLE);
            i_Books.setVisibility(View.INVISIBLE);
            hs_books.setVisibility(View.INVISIBLE);
            Sports.setVisibility(View.INVISIBLE);
            i_Sports.setVisibility(View.INVISIBLE);
            hs_sports.setVisibility(View.INVISIBLE);
            Games.setVisibility(View.INVISIBLE);
            i_Games.setVisibility(View.INVISIBLE);
            hs_games.setVisibility(View.INVISIBLE);
        } else if (dbHelper.findLevel(id) < 3) {
            Books.setVisibility(View.INVISIBLE);
            i_Books.setVisibility(View.INVISIBLE);
            hs_books.setVisibility(View.INVISIBLE);
            Sports.setVisibility(View.INVISIBLE);
            i_Sports.setVisibility(View.INVISIBLE);
            hs_sports.setVisibility(View.INVISIBLE);
            Games.setVisibility(View.INVISIBLE);
            i_Games.setVisibility(View.INVISIBLE);
            hs_games.setVisibility(View.INVISIBLE);
        } else if (dbHelper.findLevel(id) < 4) {
            Sports.setVisibility(View.INVISIBLE);
            i_Sports.setVisibility(View.INVISIBLE);
            hs_sports.setVisibility(View.INVISIBLE);
            Games.setVisibility(View.INVISIBLE);
            i_Games.setVisibility(View.INVISIBLE);
            hs_games.setVisibility(View.INVISIBLE);
        } else if (dbHelper.findLevel(id) < 5) {
            Games.setVisibility(View.INVISIBLE);
            i_Games.setVisibility(View.INVISIBLE);
            hs_games.setVisibility(View.INVISIBLE);
        }

        Music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                intent.putExtra(EXTRA_CATEGORY, "Music");
                Intent intent2 = getIntent();
                intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                startActivityForResult(intent, 0);
            }
        });
        i_Music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                intent.putExtra(EXTRA_CATEGORY, "Music");
                Intent intent2 = getIntent();
                intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                startActivityForResult(intent, 0);
            }
        });

        Movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                    intent.putExtra(EXTRA_CATEGORY, "Movies");
                    Intent intent2 = getIntent();
                    intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                    startActivityForResult(intent, 1);
            }
            });
            i_Movies.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                        intent.putExtra(EXTRA_CATEGORY, "Movies");
                        Intent intent2 = getIntent();
                        intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                        startActivityForResult(intent, 1);
                }
            });

            Books.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                        intent.putExtra(EXTRA_CATEGORY, "Books");
                        Intent intent2 = getIntent();
                        intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                        startActivityForResult(intent, 2);
                }
            });
            i_Books.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                        intent.putExtra(EXTRA_CATEGORY, "Books");
                        Intent intent2 = getIntent();
                        intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                        startActivityForResult(intent, 2);
                }
            });

            Sports.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                        intent.putExtra(EXTRA_CATEGORY, "Sports");
                        Intent intent2 = getIntent();
                        intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                        startActivityForResult(intent, 3);
                }
            });
            i_Sports.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                        intent.putExtra(EXTRA_CATEGORY, "Sports");
                        Intent intent2 = getIntent();
                        intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                        startActivityForResult(intent, 3);
                }
            });

            Games.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                        intent.putExtra(EXTRA_CATEGORY, "Games");
                        Intent intent2 = getIntent();
                        intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                        startActivityForResult(intent, 4);
                }
            });
            i_Games.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(SecondActivity.this, MusicActivity.class);
                        intent.putExtra(EXTRA_CATEGORY, "Games");
                        Intent intent2 = getIntent();
                        intent.putExtra(EXTRA_ID, intent2.getIntExtra(MainActivity.EXTRA_ID, 0));
                        startActivityForResult(intent, 4);
                }
            });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_ID, 0);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);

        if(resultCode == RESULT_OK) {
            if (requestCode == 0) {
                int score = data.getIntExtra(MusicActivity.EXTRA_SCORE, 0);
                hs_music.setText("HS: " + score);
            } else if (requestCode == 1) {
                int score = data.getIntExtra(MusicActivity.EXTRA_SCORE, 0);
                hs_movies.setText("HS: " + score);
            } else if (requestCode == 2) {
                int score = data.getIntExtra(MusicActivity.EXTRA_SCORE, 0);
                hs_books.setText("HS: " + score);
            } else if (requestCode == 3) {
                int score = data.getIntExtra(MusicActivity.EXTRA_SCORE, 0);
                hs_sports.setText("HS: " + score);
            } else if (requestCode == 4) {
                int score = data.getIntExtra(MusicActivity.EXTRA_SCORE, 0);
                hs_games.setText("HS: " + score);
            }
            if(dbHelper.findLevel(id) >= 2) {
                Movies.setVisibility(View.VISIBLE);
                i_Movies.setVisibility(View.VISIBLE);
                hs_movies.setVisibility(View.INVISIBLE);
            }
            if(dbHelper.findLevel(id) >= 3) {
                Books.setVisibility(View.VISIBLE);
                i_Books.setVisibility(View.VISIBLE);
                hs_books.setVisibility(View.VISIBLE);
            }
            if (dbHelper.findLevel(id) >= 4) {
                Sports.setVisibility(View.VISIBLE);
                i_Sports.setVisibility(View.VISIBLE);
                hs_sports.setVisibility(View.VISIBLE);
            }
            if (dbHelper.findLevel(id) == 5) {
                Games.setVisibility(View.VISIBLE);
                i_Games.setVisibility(View.VISIBLE);
                hs_games.setVisibility(View.VISIBLE);
            }
            level.setText("Level: " + dbHelper.findLevel(id));
        }
    }

    private void loadHighscore() {
        Intent intent = getIntent();
        int id = intent.getIntExtra(MainActivity.EXTRA_ID, 0);
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        hs_music.setText("HS: " + dbHelper.getHighscore("music", id));
        hs_movies.setText("HS: " + dbHelper.getHighscore("movies", id));
        hs_books.setText("HS: " + dbHelper.getHighscore("books", id));
        hs_sports.setText("HS: " + dbHelper.getHighscore("sports", id));
        hs_games.setText("HS: " + dbHelper.getHighscore("games", id));
    }
}