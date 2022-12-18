package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;
import exceptions.WrongValueException;
import model.sub.SchoolType;

import java.util.UUID;

@Entity(defaultKeyspace = "cinema")
@CqlName("tickets")
@PropertyStrategy(mutable = false)
public class Student extends Ticket {

    private long studentIDCard;
    private SchoolType schoolType;

    public Student(UUID id, long studentIDCard, SchoolType schoolType, double basePrice, UUID clientId, UUID movieId, String discriminator, int seat) {
        super(id, basePrice, clientId, movieId, discriminator, seat);
        this.studentIDCard = studentIDCard;
        this.schoolType = schoolType;
    }

    public Student(double basePrice, int seat, UUID clientId, UUID movieId, long studentIDCard, SchoolType schoolType) {
        super(basePrice, clientId, movieId, "student", seat);
        this.studentIDCard = studentIDCard;
        this.schoolType = schoolType;
    }

//    public double getTicketPrice() {
//        return switch (schoolType) {
//            case PRIMARY_SCHOOL, HIGH_SCHOOL -> getBasePrice() - getBasePrice() * 0.3;
//            case STUDIES -> getBasePrice() - getBasePrice() * 0.5;
//        };
//    }

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
