package com.android.voicenotes.model;


public class Note {

    private long time;
    private String text;
    private boolean done = false;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (time != note.time) return false;
        return text != null ? text.equals(note.text) : note.text == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (time ^ (time >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
