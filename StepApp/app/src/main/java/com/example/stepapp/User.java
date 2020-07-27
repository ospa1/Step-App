package com.example.stepapp;

public class User {
    private String username;
    private int steps;

    //constructor
    public User(){
    }
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

    public String getUsername() {
        return username;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
