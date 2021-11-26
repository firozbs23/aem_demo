package com.aem.demo.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.naming.Name;

@Model(adaptables = Resource.class)
public class MyDemoModel {


    private String name;

    private String[] counter;

    private Person person;

    @PostConstruct
    protected void init() {
        name = "Firoz Mahmud okay";

        setCounter();
    }

    public String getName() {
        return name;
    }

    public String[] getCounter() {
        return counter;
    }

    public void setCounter() {
        this.counter = new String[]{"1", "2", "3", "4", "5"};
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCounter(String[] counter) {
        this.counter = counter;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
