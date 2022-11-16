package org.block1;

public class Person {
    private String name;
    private String town;
     private int age;

    public Person() {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name='" + name +
                '\'' + ", age=" + age +
                ", city='" + town + '\'';
    }
}

