package com.mongospring.vo;

public class Employee {

    String name;
    String passport;
    Address address;
    long salary;

    public Employee(String name, String passport, Address address, long salary) {
        this.name = name;
        this.passport = passport;
        this.address = address;
        this.salary = salary;
    }

    public long getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    public Address getAddress() {
        return address;
    }
}
