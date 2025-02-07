package nl.getthere.demo.example3;

import java.util.List;

public class BookClub {

    private String name;
    private String currentBook;
    private List<Person> members;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(String currentBook) {
        this.currentBook = currentBook;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "BookClub{" +
                "name='" + name + '\'' +
                ", currentBook='" + currentBook + '\'' +
                ", members=\n" + members +
                '}';
    }
}
