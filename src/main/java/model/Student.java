package model;

import exceptions.WrongValueException;
import model.sub.SchoolType;

public class Student extends Ticket {
    private String studentIDCard;
    private SchoolType schoolType;

    public Student(int id, double basePrice, int seat, Client client, Movie movie,
                   String studentIDCard, SchoolType schoolType) throws WrongValueException {
        super(id, basePrice, seat, client, movie);
        setStudentIDCard(studentIDCard);
        setSchoolType(schoolType);
    }

    @Override
    public double getTicketPrice() {
        return switch (schoolType) {
            case PRIMARY_SCHOOL, HIGH_SCHOOL -> getBasePrice() - getBasePrice() * 0.3;
            case STUDIES -> getBasePrice() - getBasePrice() * 0.5;
        };
    }

    public String getStudentIDCard() {
        return studentIDCard;
    }

    public void setStudentIDCard(String studentIDCard) throws WrongValueException {
        if(!studentIDCard.isBlank()) {
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
