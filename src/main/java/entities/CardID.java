package entities;

import java.util.UUID;

public class CardID {
  private Student student;
  private UUID code;  
  
  public Student getStudent() {
    return student;
  }

  public UUID getCode() {
    return code;
  }
  
  public void setStudent(Student student) {
    if (student.getCardID() == null) {
      this.student = student;
      code = UUID.randomUUID();
      student.setCardID(this);
      System.out.println("UUID: " + code);
    }
    else
      System.out.println("esse student ja tem cartao");
  }

  public void removeStudent() {
    student.removeCardID();
  }
}