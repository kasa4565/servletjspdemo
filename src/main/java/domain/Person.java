package domain;

import java.util.ArrayList;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String birthday;
    private String email;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void SetParameter(String param, String value)
    {
        if(param.equals("id")) id = Integer.parseInt(value);
        else if(param.equals("firstName")) firstName = value;
        else if(param.equals("lastName")) lastName = value;
        else if(param.equals("gender")) gender = value;
        else if(param.equals("birthday")) birthday = value;
        else if(param.equals("email")) email = value;
        else if(param.equals("age")) age = Integer.parseInt(value);
    }

    public static ArrayList<String> GetLabels()
    {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("id");
        labels.add("firstName");
        labels.add("lastName");
        labels.add("gender");
        labels.add("birthday");
        labels.add("email");
        labels.add("age");
        return labels;
    }
}
