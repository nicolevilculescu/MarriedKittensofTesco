package com.example.marriedkittensoftesco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.marriedkittensoftesco.QuizContract.*;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MarriedKittensofTesco.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_HIGHSCORE_TABLE = "CREATE TABLE " +
                HighScoreTable.TABLE_NAME + " (" +
                HighScoreTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HighScoreTable.COLUMN_MUSIC + " TEXT, " +
                HighScoreTable.COLUMN_MOVIES + " TEXT, " +
                HighScoreTable.COLUMN_BOOKS + " TEXT, " +
                HighScoreTable.COLUMN_SPORTS + " TEXT, " +
                HighScoreTable.COLUMN_GAMES + " TEXT, " +
                HighScoreTable.COLUMN_USER_ID + " INTEGER, " +
                "FOREIGN KEY(" + HighScoreTable.COLUMN_USER_ID + ") REFERENCES " +
                UsersTable.TABLE_NAME + "(" + UsersTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        final String SQL_CREATE_USERS_TABLE = "CREATE TABLE " +
                UsersTable.TABLE_NAME + " (" +
                UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UsersTable.COLUMN_USERNAME + " TEXT, " +
                UsersTable.COLUMN_PASSWORD + " TEXT, " +
                UsersTable.COLUMN_FNAME + " TEXT, " +
                UsersTable.COLUMN_LNAME + " TEXT, " +
                UsersTable.COLUMN_LEVEL + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " (" +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.CATEGORY + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_HIGHSCORE_TABLE);
        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillUsersTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + HighScoreTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillUsersTable () {
        User u = new User("WildCats", "password", "Wild", "Cats", 5);
        addUser(u);
        addHighscore(1);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("What is the highest-grossing film of all time without taking inflation into account?", "Titanic ", "Avengers: Endgame", "Avatar", "Star Wars: The Force Awakens", 2,"Movies");
        addQuestion(q1);
        Question q2 = new Question(" Which actor or actress is killed off in the opening scene of the movie Scream?", "Courtney Cox ", "Neve Campbell", "Drew Barrymore", "Rose McGowan", 3,"Movies");
        addQuestion(q2);
        Question q3 = new Question(" Which film did Steven Spielberg win his first Oscar for Best Director?", "Jaws", "Catch Me If You Can", "E.T.", "Schindler’s List", 4,"Movies");
        addQuestion(q3);
        Question q4 = new Question("Which film won the first Academy Award for Best Picture?", "All Quiet on the Western Front", "Sunrise", "Wings", "Metropolis", 3,"Movies");
        addQuestion(q4);
        Question q5 = new Question("What is the name of Quint’s shark-hunting boat in Jaws?", "The Whale", "The Orca", "The Dolphin", "The Shark", 2,"Movies");
        addQuestion(q5);
        Question q6 = new Question("What was the first feature-length animated film ever released?", "Pinocchio ", "Fantasia", "Snow White and the Seven Dwarfs ", "Dumbo", 3,"Movies");
        addQuestion(q6);
        Question q7 = new Question(" What was the first original Disney song to win an Academy Award for Best Original Song?", "Someday My Prince Will Come", "Circle of Life", "Beauty and the Beast", "When You Wish Upon a Star", 4,"Movies");
        addQuestion(q7);
        Question q8 = new Question("What is the longest movie ever made?", "The stand", "Dances with wolves", "Hamlet", "The cure for insomnia", 4,"Movies");
        addQuestion(q8);
        Question q9 = new Question("What movie, starring Al Jolson, is generally considered to be the first talking picture?", "Are you a mason?", "The jazz singer", "The singing fool", "New York Nights", 2,"Movies");
        addQuestion(q9);
        Question q10 = new Question("What was the first movie by Pixar to receive a rating higher than G in the United States?", "Finding Nemo", "Monsters Inc.", "Toy Story", "The Incredibles", 4,"Movies");
        addQuestion(q10);

        Question q11 = new Question("What MTV music show premiered in 1998?", "Total Request Live ", "Unplugged", "MTV News", "MTV Rocks Off", 1,"Music");
        addQuestion(q11);
        Question q12 = new Question(" What was the name of The Spice Girls’ debut album in 1996?", "Forever ", "Spiceworld", "Spice", "Wannabe", 3,"Music");
        addQuestion(q12);
        Question q13 = new Question(" What Nashville venue was Taylor Swift playing at when she was discovered?", "Mercy Lounge ", "The Bluebird Cafe", "The Basement East", "12 & Porter", 2,"Music");
        addQuestion(q13);
        Question q14 = new Question(" What is Fergie’s real name? ", "Stacie Ann Fergusen", "Stacey Ann Fergusen", "Stacie Ann Ferguson", "Stacey Ferguson", 2,"Music");
        addQuestion(q14);
        Question q15 = new Question("How many black keys are found on a traditional piano?", "32 ", "47", "34", "52", 1,"Music");
        addQuestion(q15);
        Question q16 = new Question("Who was awarded the very first gold record?", "Perry Como", "The Beatles", "Elvis Presley", "Nat King Cole", 1,"Music");
        addQuestion(q16);
        Question q17 = new Question("What pop singer is known as The Material Girl?", "Taylor Swift", "Britney Spears", "Madonna", "Christina Aguilera", 3,"Music");
        addQuestion(q17);
        Question q18 = new Question("What Depeche Mode song was inspired by Priscilla Presley's book Elvis and Me?", "Strangelove", "Personal Jesus", "World in my eyes", "Sweetest perfection", 2,"Music");
        addQuestion(q18);
        Question q19 = new Question("What is the oldest surviving musical instrument?", "Drum", "Lyre", "Trumpet", "Flute", 4,"Music");
        addQuestion(q19);
        Question q20 = new Question("What singer holds the world record for most words in a hit single?", "Busta Rhymes", "50 Cent", "Kanye West", "Eminem", 4,"Music");
        addQuestion(q20);

        Question q21 = new Question("Who wrote The Iliad?", "Euripides", "Beowulf", "Homer", "Virgil", 3,"Books");
        addQuestion(q21);
        Question q22 = new Question("Put the three cantos of The Divine Comedy in the correct order.", "Paradiso, Purgatorio, Inferno", "Inferno, Limbo, Paradiso", "Purgatorio, Inferno, Limbo", "Inferno, Purgatorio, Paradiso", 4,"Books");
        addQuestion(q22);
        Question q23 = new Question("Don Quixote was written in which language?", "Old English", "Italian", "Spanish", "Greek", 3,"Books");
        addQuestion(q23);
        Question q24 = new Question("Truman Capote’s In Cold Blood takes its name from which Shakespeare play?", "Macbeth", "Timon of Athens", "Othello", "Hamlet", 2,"Books");
        addQuestion(q24);
        Question q25 = new Question("The Girl with the Dragoon Tattoo was originally published in which language?", "Swedish", "Norweigian", "Danish", "English", 1,"Books");
        addQuestion(q25);
        Question q26 = new Question("E.L. James’s Fifty Shades of Grey was originally fan fiction for which book series?", "The Twilight Series", "The Harry Potter Series", "The Outlander Series", "The Left Behind Series", 2,"Books");
        addQuestion(q26);
        Question q27 = new Question("What is the seventh and final installment in J. K. Rowling’s Harry Potter series?", "Harry Potter and the Order of the Phoenix", "Harry Potter and the Half-Blood Prince", "Harry Potter and the Cursed Child", "Harry Potter and the Deathly Hallows", 4,"Books");
        addQuestion(q27);
        Question q28 = new Question("Chronologically, this novel comes first in C. S. Lewis’s Chronicles of Narnia series.", "The Lion, the Witch and the Wardrobe", "The Voyage of the Dawn Treader", "The Magician’s Nephew", "The Silver Chair", 3,"Books");
        addQuestion(q28);
        Question q29 = new Question("When was Edgar Allan Poe’s “The Raven” first published?", "1838", "1840", "1845", "1850", 3,"Books");
        addQuestion(q29);
        Question q30 = new Question("How many acts are in Romeo and Juliet?", "Two", "Three", "Four", "Five", 4,"Books");
        addQuestion(q30);

        Question q31 = new Question("What’s the diameter of a basketball hoop in inches?", " 12", "16", "18", "20", 3,"Sports");
        addQuestion(q31);
        Question q32 = new Question("How big is an Olympic sized swimming pool in meters?", "50 meters long and 25 meters wide", "25 meters long and 20 meters wide", "25 meters long and 45 meters wide", "100 meters long and 25 meters wide",1 ,"Sports");
        addQuestion(q32);
        Question q33 = new Question("In professional basketball, how high is the basketball hoop from the ground?", " 10 feet", "15 feet", "12 feet", "9 feet", 1,"Sports");
        addQuestion(q33);
        Question q34 = new Question("The Olympics are held every how many years?", " 4 years", "5 years", "8 years", "2 years", 1,"Sports");
        addQuestion(q34);
        Question q35 = new Question("What is Canada’s national sport?", " Lacrosse", "Hockey", "Soccer", "Football", 1,"Sports");
        addQuestion(q35);
        Question q36 = new Question("What Years Did Ronaldinho Play With Barcelona?", "2003-2008 ", "2003-2010", "2001-2006", "2002-2007", 1,"Sports");
        addQuestion(q36);
        Question q37 = new Question("How Is Soccer Player Edson Arantes Do Nascimento Better Known?", " Ronaldinho", "Pele", "Eddy", "Arminio", 2,"Sports");
        addQuestion(q37);
        Question q38 = new Question(" Which Jamaican Sprinter Won Gold Medals At The 100m, 200m, And 4 X 100m Relay At Three Consecutive Olympic Games From 2008 – 2016?", " Usain Bolt", "Usain Jolt", "Donald Quarrie", "Asafa Powell",1 ,"Sports");
        addQuestion(q38);
        Question q39 = new Question("What Does NBA Stand For?", "National Basketball Association ", "National Book Award", "National Bar Association", "National Bank of Azerbaijan",1 ,"Sports");
        addQuestion(q39);
        Question q40 = new Question("What Part Of The Body Can’t Touch The Ball In Soccer?", " Hands", "Nether Regions", "Feet", "Shoulders",1 ,"Sports");
        addQuestion(q40);

        Question q41 = new Question("What Is The Name Of The Final Course In All “Mario Kart” Videogames?", " Rainbow Road", "Flowe Hill", "Mushroom Cup", "Lakeside Park",1,"Games");
        addQuestion(q41);
        Question q42 = new Question("Mario Originated As A Character In Which Classic Videogame", "Donkey Kong", "Mario Kart", "Luigi's Pizza", "The Plumber",1,"Games");
        addQuestion(q42);
        Question q43 = new Question("Solid Snake Is A Hero In Which Famous Videogame Franchise?", "Metal Gear", "Dark Souls", "Call Of Duty", "Blood Bowl",1,"Games");
        addQuestion(q43);
        Question q44 = new Question("Nintendo Began As A Company Selling Which Products?", "Video Games", "Clothes", "Playing Cards", "Socks",1,"Games");
        addQuestion(q44);
        Question q45 = new Question("Which French Video Game Company Publishes The Far Cry Series?", "Ubisoft", "Blizzard", "Steam", "AGEod",1,"Games");
        addQuestion(q45);
        Question q46 = new Question("Underground, Hot Pursuit And Most Wanted Are Installments Of Which Racing Video Game Franchise", "Need For Speed", "F1", "Dirt", "Gran Turismo",1,"Games");
        addQuestion(q46);
        Question q47 = new Question("What 2014 Game Gives You The Name “Douchebag”?", "South Park: The Stick of Truth", "South Park: The Fractured Butt Whole", "Minecraft", "Mr.Robot", 1,"Games");
        addQuestion(q47);
        Question q48 = new Question(" What Was Mario’s First Profession", " Carpenter", "Plumber", "Electrician", "Ghost Buster",1,"Games");
        addQuestion(q48);
        Question q49 = new Question("In Which Game Do Players Compete In A Futuristic Version Of Soccer With Cars", " Rocket League", "Cars 3", "Footbal Cars the Video Game", "FifaCars",1,"Games");
        addQuestion(q49);
        Question q50 = new Question("Which Of The Following Games Has Not Been Turned Into A Movie", "Quaqe ", "Warcraft", "The Witcher", "Sonic",1,"Games");
        addQuestion(q50);
    }

    public void addHighscore(int id) {
        Highscore h = new Highscore(0, 0, 0, 0, 0, id);
        ContentValues cv = new ContentValues();
        cv.put(HighScoreTable.COLUMN_MUSIC, h.getMusic());
        cv.put(HighScoreTable.COLUMN_MOVIES, h.getMovies());
        cv.put(HighScoreTable.COLUMN_BOOKS, h.getBooks());
        cv.put(HighScoreTable.COLUMN_SPORTS, h.getSports());
        cv.put(HighScoreTable.COLUMN_GAMES, h.getGames());
        cv.put(HighScoreTable.COLUMN_USER_ID, h.getUser_id());
        db.insert(HighScoreTable.TABLE_NAME, null, cv);
    }

    public void addUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(UsersTable.COLUMN_USERNAME, user.getUsername());
        cv.put(UsersTable.COLUMN_PASSWORD, user.getPassword());
        cv.put(UsersTable.COLUMN_FNAME, user.getFirstName());
        cv.put(UsersTable.COLUMN_LNAME, user.getLastName());
        cv.put(UsersTable.COLUMN_LEVEL, user.getLevel());
        db.insert(UsersTable.TABLE_NAME, null, cv);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.CATEGORY, question.getCategory());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + UsersTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(c.getString(c.getColumnIndex(UsersTable.COLUMN_USERNAME)));
                user.setPassword(c.getString(c.getColumnIndex(UsersTable.COLUMN_PASSWORD)));
                user.setFirstName(c.getString(c.getColumnIndex(UsersTable.COLUMN_FNAME)));
                user.setLastName(c.getString(c.getColumnIndex(UsersTable.COLUMN_LNAME)));
                user.setLevel(c.getInt(c.getColumnIndex(UsersTable.COLUMN_LEVEL)));
                users.add(user);
            } while (c.moveToNext());
        }
        c.close();
        return users;
    }

    public ArrayList<Question> getQuestions(String category) {
        String selection = QuestionsTable.CATEGORY + " = ?";
        String[] selectionArgs = new String[]{category};

        ArrayList<Question> questions = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategory(c.getString(c.getColumnIndex(QuestionsTable.CATEGORY)));
                questions.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questions;
    }

    public int findId(String username) {
        int id = 0;
        Cursor c = db.rawQuery("SELECT " + UsersTable._ID + " FROM " + UsersTable.TABLE_NAME +
                " WHERE " + UsersTable.COLUMN_USERNAME + " = " + "\"" +  username + "\"", null);
        if (c.moveToFirst()) {
            do {
                id = c.getInt(c.getColumnIndex(UsersTable._ID));
            } while (c.moveToNext());
        }
        c.close();
        return id;
    }

    public boolean updateHighscore(int score, String category, int id) {
        int highscore = getHighscore(category, id);
        if(highscore < score) {
            final String SQL_UPDATE_HIGHSCORE_TABLE = "UPDATE  " + HighScoreTable.TABLE_NAME +
                    " SET " + category + " = " + score +
                    " WHERE " + HighScoreTable.COLUMN_USER_ID + " = " + "\"" + id + "\"";

            db.execSQL(SQL_UPDATE_HIGHSCORE_TABLE);
            return true;
        }
        return false;
    }

    public int getHighscore(String category, int id) {
        int highscore = 0;
        Cursor c = db.rawQuery("SELECT " + category + " FROM " + HighScoreTable.TABLE_NAME +
                " WHERE " + HighScoreTable.COLUMN_USER_ID + " = " + "\"" + id + "\"", null);
        if (c.moveToFirst()) {
            do {
                highscore = c.getInt(c.getColumnIndex(category));
            } while (c.moveToNext());
        }
        c.close();
        return highscore;
    }

    public int findLevel(int id) {
        int level = 0;
        Cursor c = db.rawQuery("SELECT " + UsersTable.COLUMN_LEVEL + " FROM " + UsersTable.TABLE_NAME +
                " WHERE " + UsersTable._ID + " = " + "\"" +  id + "\"", null);
        if (c.moveToFirst()) {
            do {
                level = c.getInt(c.getColumnIndex(UsersTable.COLUMN_LEVEL));
            } while (c.moveToNext());
        }
        c.close();
        return level;
    }

    public void updateLevel(int id) {
        final String SQL_UPDATE_USER_TABLE = "UPDATE  " + UsersTable.TABLE_NAME +
                " SET " + UsersTable.COLUMN_LEVEL + " = " + (findLevel(id) + 1) +
                " WHERE " + UsersTable._ID + " = " + "\"" + id + "\"";
        db.execSQL(SQL_UPDATE_USER_TABLE);
    }

    public String findName(int id) {
        String name = "";
        Cursor c = db.rawQuery("SELECT " + UsersTable.COLUMN_FNAME + " FROM " + UsersTable.TABLE_NAME +
                " WHERE " + UsersTable._ID + " = " + "\"" +  id + "\"", null);
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(UsersTable.COLUMN_FNAME));
            } while (c.moveToNext());
        }
        c.close();
        return name;
    }
}
