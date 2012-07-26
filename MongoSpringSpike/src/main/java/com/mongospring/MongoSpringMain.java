package com.mongospring;

import com.mongospring.config.SpringMongoConfig;
import com.mongospring.vo.Address;
import com.mongospring.vo.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MongoSpringMain {

    public static void main(String[] args) {
        System.out.println("Spring Mongo Demo");

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");


        //SaveEmployee(mongoOperation);


        //Find employee who lives in USA
        Query query = new Query(Criteria.where("address.country").is("USA"));
        List<Employee> employees = mongoOperation.find(query, Employee.class);
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " : " + employee.getSalary());
        }

        System.out.println("***************************************************");

        //Find employee who does not live in USA
        query = new Query(Criteria.where("address.country").ne("USA"));
        employees = mongoOperation.find(query, Employee.class);
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " : " + employee.getSalary());
        }

        System.out.println("***************************************************");


        //Find count of employees who lives in USA
        query = new Query(Criteria.where("address.country").ne("USA"));
        long count=mongoOperation.executeCommand(
                "{ " +
                        "\"count\" : \"" + "Employee" + "\"," +
                        "\"query\" : " + query.getQueryObject().toString() +
                        " }").getLong("n");

        System.out.println("Count: "+count);
        System.out.println("***************************************************");


        //List all employees order by salary  who lives in USA
        query = new Query(Criteria.where("address.country").is("USA"));
        query.sort().on("salary", Order.ASCENDING);
        employees = mongoOperation.find(query, Employee.class);
        for (Employee employee : employees) {
            System.out.println(employee.getName() + " : " + employee.getSalary());
        }

        System.out.println("***************************************************");

        //Get sum of salary earned by US employees


    }

    private static void SaveEmployee(MongoOperations mongoOperation) {
        Employee employee = new Employee("Tina", "H0123444", getAddress("12C", "NYC", "USA", 23444), 20000);
        mongoOperation.save(employee, "employee");

        Employee employee1 = new Employee("Ginnete", "Y7773444", getAddress("7c", "NYC", "USA", 45333), 40000);
        mongoOperation.save(employee1, "employee");

        Employee employee2 = new Employee("Revolia", "A11008", getAddress("50c", "NYC", "USA", 98888), 10000);
        mongoOperation.save(employee2, "employee");

        Employee employee3 = new Employee("Kiran", "J22212", getAddress("MGRoad", "Pune", "IND", 41002), 50000);
        mongoOperation.save(employee3, "employee");
    }

    private static Address getAddress(String street, String city, String country, int zipCode) {
        return new Address(street, city, country, zipCode);
    }
}
