package com.example.menu;

public class Answers {
    private String answer ;
    private String time;
    private String name ;

    public Answers(String answer, String time, String name) {
        this.answer = answer;
        this.time = time;
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

}
