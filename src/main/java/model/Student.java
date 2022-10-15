package model;

import exceptions.WrongValueException;
import jakarta.persistence.*;
import model.sub.SchoolType;

@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("student")
public class Student extends Ticket {

    @Column(unique = true)
    private long studentIDCard;

    @Enumerated(EnumType.STRING)
    private SchoolType schoolType;

    public Student(double basePrice, int seat, Client client, Movie movie,
                   long studentIDCard, SchoolType schoolType) throws WrongValueException {
        super(basePrice, seat, client, movie);
        setStudentIDCard(studentIDCard);
        setSchoolType(schoolType);
    }

    protected Student() {

    }

    @Override
    public double getTicketPrice() {
        return switch (schoolType) {
            case PRIMARY_SCHOOL, HIGH_SCHOOL -> getBasePrice() - getBasePrice() * 0.3;
            case STUDIES -> getBasePrice() - getBasePrice() * 0.5;
        };
    }

    public long getStudentIDCard() {
        return studentIDCard;
    }

    public void setStudentIDCard(long studentIDCard) throws WrongValueException {
        if(studentIDCard != 0) {
            this.studentIDCard = studentIDCard;
        } else {
            throw new WrongValueException("Student ID Card cannot be blank");
        }

    }

    public SchoolType getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
    }
}
