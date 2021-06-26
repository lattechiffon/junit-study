package com.lattechiffon.junit.chapter2;

import com.lattechiffon.junit.chapter1.Scoreable;

public class Criterion implements Scoreable {
    private Weight weight;
    private Answer answer;
    private int score;

    public Criterion(Answer answer, Weight weight) {
        this.answer = answer;
        this.weight = weight;
    }

    public Answer getAnswer() { return answer; }
    public Weight getWeight() { return weight; }

    public void setScore(int score) { this.score = score; }
    public int getScore() { return score; }
}