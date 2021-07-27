package ru.job4j.tracker.oop.profession;

public class Profession {

    private String name;
    private String surname;
    private String educations;
    private String birthday;

    public Profession(String name, String surname, String educations, String birthday) {
        this.name = name;
        this.surname = surname;
        this.educations = educations;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEducation() {
        return educations;
    }

    public String getBirthday() {
        return birthday;
    }
}
