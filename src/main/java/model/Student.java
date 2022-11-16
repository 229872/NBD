package model;

import exceptions.WrongValueException;
import model.sub.SchoolType;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Student extends Ticket {

    @BsonProperty("student_id")
    private long studentIDCard;

    @BsonProperty("school_type")
    private SchoolType schoolType;

    public Student(double basePrice, int seat, Client client, Movie movie,
                   long studentIDCard, SchoolType schoolType) throws WrongValueException {
        super(new UniqueId(), basePrice, seat, client, movie);
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

    @Override
    public void close() throws Exception {

    }
}
