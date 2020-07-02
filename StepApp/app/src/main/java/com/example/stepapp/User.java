package com.example.stepapp;

public class User {
    private String username;
    private int steps;

    //constructor
    //TODO check for valid usernames
    public User(String user){
        username = user;
    }

    //add to the steps
    public void addSteps(int num){
        steps += num;
    }

    public int getSteps() {
        return steps;
    }
}
