package main.model;


public class Employee {
    String lastName;
    String firstName;
    String email;
    String department;
    Float salary;

    public Employee (String lastName, String firstName, String email, String department, Float salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public Float getSalary() {
        return salary;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email ){
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}