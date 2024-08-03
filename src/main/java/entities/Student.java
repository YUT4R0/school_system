package entities;

import java.util.ArrayList;

public class Student {
  private String name;
  private int age;
  private CardID cardID;
  private ArrayList<Course> courses;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
    courses = new ArrayList<Course>();
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public CardID getCardID() {
    return cardID;
  }

  public ArrayList<Course> getCourses() {
    return courses;
  }

  public void setName(String name) {
    if (name.length() > 0)
      this.name = name;
    else
      System.out.println("name cannot be public void");
  }

  public void setAge(int age) {
    if (age > 0)
      this.age = age;
    else
      System.out.println("age must be over than 0");
  }

  public void setCardID(CardID cardID) {
    if (this.cardID == null)
      this.cardID = cardID;
    else
      System.out.println("esse Student ja tem cartao");
  }

  public void removeCardID() {
    if (this.cardID != null)
      cardID = null;
  }

  public void setCourse(Course course) {
    if (this.cardID != null)
      courses.add(course);
    else
      System.out.println("Student nao possui Cartao!");
  }

  public void removeCourse(Course course) {
    for (Course _course : courses)
      if (_course == course)
        courses.remove(course);
  }
}