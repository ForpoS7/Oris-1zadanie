package entity;

import java.time.LocalDate;
import java.sql.Date;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Date dateOfBirth;
    private Integer coolness;
    private Double armSpan;

    public User(Long id, String firstName, String lastName, Integer age, Date dateOfBirth, Integer coolness, Double armSpan) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.coolness = coolness;
        this.armSpan = armSpan;
    }

    public User(String firstName, String lastName, Integer age, Date dateOfBirth, Integer coolness, Double armSpan) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.coolness = coolness;
        this.armSpan = armSpan;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getCoolness() {
        return coolness;
    }

    public Double getArmSpan() {
        return armSpan;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", coolness=" + coolness +
                ", armSpan=" + armSpan +
                '}';
    }
}
