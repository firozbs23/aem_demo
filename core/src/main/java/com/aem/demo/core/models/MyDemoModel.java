package com.aem.demo.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class)
public class MyDemoModel {


    private String name;

    private String[] counter;

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
        this.name = "Firoz Mahmud From Setter";
    }
}
