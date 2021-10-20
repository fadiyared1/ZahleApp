package com.example.menu;

public class Questions {
    private String question ;
    private String time;
    private String name ;


    public Questions(String question, String time, String name) {
        this.question = question;
        this.time = time;
        this.name = name;

    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getQuestion() {
        return question;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }



}
