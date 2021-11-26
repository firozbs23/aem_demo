package com.aem.demo.core.models.impl;

import com.aem.demo.core.models.SlingModelDemo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = SlingModelDemo.class)
public class SlingModelDemoImpl implements SlingModelDemo {

    @Inject
    @Default(values = "Demo")
    private String firstName;
    @Inject
    @Default(values = "")
    private String lastName;

    @Override
    public String getFirstName() {
        return firstName.trim().toUpperCase();
    }

    @Override
    public String getLastName() {
        return lastName.trim().toUpperCase();
    }

    @Override
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
