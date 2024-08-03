package entities;

import java.util.ArrayList;

public class Professor {
  private String name;
  private int age;
  private ArrayList<Course> courses;

  public Professor(String name, int age) {
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

  public void setCourse(Course course) {
    courses.add(course);
    course.setProfessor(this);
  }

  public void removeCourse(Course course) {
    for (Course _course : courses) {
      if (_course == course) {
        courses.remove(course);
        _course.removeProfessor();
      }
    }
  }
}