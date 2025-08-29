package org.javase;


import com.fasterxml.jackson.databind.JsonNode;
import org.openjdk.jmh.runner.RunnerException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.Semaphore;

class SimpleClass implements Cloneable {

    private String name;

    public SimpleClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public SimpleClass clone() {
        try {
            return (SimpleClass) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SimpleClass that) {
            return Objects.equals(name, that.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}

public class Main {
    private static final String CONFIG = "config";
    private static final String DATA = "data";
    private static final String TYPE = "type";
    private static final String TYPE_LIST = "list";
    private static final String NAME = "name";
    private static final String FIELD = "field";
    private static final String FIELDS = "fields";

//    public static void main(String[] args) throws RunnerException, IOException {


    /// /
    /// /        Set<String> set = new HashSet<>(){{
    /// /            add("One");
    /// /            add("Two");
    /// /            add("Three");
    /// /        }};
    /// /
    /// /        System.out.println(set);
    /// /        System.out.println(100 % 2 == 1);
    /// /        System.out.println((100 & 1) != 0);
    /// /        System.out.println('a'+'b');
    /// /        scaleImage();
//
//        ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Dhaka"));
//        System.out.println(dateTime);
//    }
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); // only 3 threads allowed

        for (int i = 1; i <= 10; i++) {
            int threadId = i;
            new Thread(() -> {
                try {
                    System.out.println("Thread " + threadId + " waiting for permit...");
                    semaphore.acquire();
                    System.out.println("Thread " + threadId + " got permit!");

                    // Critical section
                    Thread.sleep(2000); // simulate work

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Thread " + threadId + " releasing permit.");
                    semaphore.release();
                }
            }).start();
        }
    }

    public static void scaleImage() throws IOException {
        BufferedImage imgSource = ImageIO.read(new File("C:\\Users\\Sadiul Hakim\\OneDrive\\Desktop\\image.jpg"));
        BufferedImage imgDestination = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imgDestination.createGraphics();
        AffineTransform affinetransformation = AffineTransform.getScaleInstance(2, 2);
        g.drawRenderedImage(imgSource, affinetransformation);
        ImageIO.write(imgDestination, "JPG", new File("C:\\Users\\Sadiul Hakim\\OneDrive\\Desktop\\image2.jpg"));
    }

//    public static void main(String[] args) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            JsonNode root = mapper.readTree(new File("C:\\Users\\Sadiul Hakim\\OneDrive\\Desktop\\json\\final 2.json"));
//            Set<String> editorFieldsName = getEditorFieldsName(root);
//            System.out.println(editorFieldsName);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        Set<String> names = new HashSet<>();
//        names.add("heatRates");
//        names.add("HeatRates");
//
//        System.out.println(names);
//    }

    private static Set<String> getEditorFieldsName(JsonNode root) {

        Set<String> editorFieldName = new HashSet<>();
        Stack<JsonNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            JsonNode current = stack.pop();

            // If this is a list, add the name
            if (current.has(DATA) && FieldType.contains(getFieldType(current)) && current.has(NAME)) {
                editorFieldName.add(current.get(NAME).asText());
            }

            // Add children to the stack
            if (current.isObject()) {
                Iterator<JsonNode> fields = current.elements();
                while (fields.hasNext()) {
                    JsonNode child = fields.next();
                    if (child.isObject() || child.isArray()) {
                        stack.push(child);
                    }
                }
            } else if (current.isArray()) {
                current.forEach(stack::push);
            }
        }
        return editorFieldName;
    }

    private static String getFieldType(JsonNode field) {
        if (field.has(DATA)) {
            return field.get(DATA)
                    .get(TYPE)
                    .asText();
        }
        return "";
    }
}

