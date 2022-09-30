package com.elitechinc.churchplanner.Classes;



public class databaseQueries {


    public static final String SQL_CREATE_USER =
            "CREATE TABLE USER (UserName text, Email text )";

    public static final String SQL_CREATE_MOVIE =
            "CREATE TABLE MovieDetails (MovieId integer , MovieName text, StartTime text, BookedSeatNo integer, MovieDate text)";

    public static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS  USER  ";

    public static final String SQL_DELETE_Movie_Details =
            "DROP TABLE IF EXISTS MovieDetails  ";


    }
