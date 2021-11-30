package com.aem.demo.core.servlets;


import com.aem.demo.core.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.jcr.*;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component(service = Servlet.class, property = {
        Constants.SERVICE_DESCRIPTION + "=" + "This is a demo servlet",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET,
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_POST,
        ServletResolverConstants.SLING_SERVLET_PATHS + "=" + "/bin/public/aem_demo/demo"
})

public class MyDemoServlet extends SlingAllMethodsServlet {


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        // response.setContentType("text/plain");
        // response.getWriter().write("Hello From Demo Servlet , I am Firoz Mahmud.");

        response.setContentType("application/json");
        //response.getWriter().write("{'name' : 'Firoz Mahmud' , 'address' : 'Dhaka'}");
        response.getWriter().println("{\n" +
                "\n" +
                "\t\"name\": \"Firoz Mahmud\",\n" +
                "\t\"address\": \"Dhaka\"\n" +
                "\n" +
                "}");
    }


    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {


        try {
          /*  ResourceResolver resourceResolver = request.getResourceResolver();

            Resource resource = resourceResolver.getResource("/content/aem_demo");

            Node node = resource != null ? resource.adaptTo(Node.class) : null;

            Node newNode = node != null ? node.addNode("demoNode", "nt:unstructured") : null;

            if (newNode != null) {
                newNode.setProperty("name", "Demo Node, From Servlet");
            }

            resourceResolver.commit();*/

            // Getting parameterized data
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String address = request.getParameter("address");

            // Getting the request body and convert it into string
            String requestBody = IOUtils.toString(request.getReader());


            JSONObject jsonObject = new JSONObject(requestBody);  // Convert request body string to JSONObject


            ObjectMapper mapper = new ObjectMapper();
            Person personPojo = mapper.readValue(requestBody, Person.class);


            response.setContentType("text/plain");
            response.getWriter().println("Successfully posted " + personPojo.getAddress() + "'s data : " + requestBody);

        } catch (Exception exception) {

            response.setContentType("text/plain");
            response.getWriter().println("Failed to post : " + exception.getLocalizedMessage());

        }

    }


    private String getRequestBodyAsString(SlingHttpServletRequest request) throws IOException {  // No need
        String body;

        body = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        return body;
    }


    private String getBody(SlingHttpServletRequest request) throws IOException {  // No need

        String body;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;

                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        body = stringBuilder.toString();
        return body;
    }

}
