package com.aem.demo.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.models.factory.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class MyDemoModelTest {
    private final AemContext ctx = new AemContext();
    @InjectMocks
    private MyDemoModel model;
    @Mock
    private Person person;

    @BeforeEach
    void setUp() {
        ctx.addModelsForClasses(MyDemoModel.class);
        ctx.load().json("/com/aem/demo/core/models/MyDemoModel.json", "/component");

        ctx.currentResource("/component/demo_comp");
        model = Objects.requireNonNull(ctx.getService(ModelFactory.class))
                .createModel(Objects.requireNonNull(ctx.currentResource()), MyDemoModel.class);
    }

    @Test
    void testName() {
        String nameExpected = "demo_name";
        model.setName(nameExpected);
        String nameActual = model.getName();
        assertEquals(nameExpected, nameActual);
    }

    @Test
    void testPerson() {
        model.setPerson(person);
        assertEquals(person, model.getPerson());
    }

    @Test
    void testCounter() {
        String[] counterExpected = {"1", "4"};
        model.setCounter(counterExpected);
        assertEquals(counterExpected, model.getCounter());
    }
}