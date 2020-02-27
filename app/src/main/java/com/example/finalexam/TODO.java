package com.example.finalexam;

public class TODO {
    private String name, date, isComplete;
    private int _id;
    public TODO() {

    }
    public TODO(int _id, String name, String date, String isComplete) {
        this._id = _id;
        this.name = name;
        this.date = date;
        this.isComplete = isComplete;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public int get_id() {
        return this._id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }
    public String isComplete() {
        return this.isComplete;
    }
}
