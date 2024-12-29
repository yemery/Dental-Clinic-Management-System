package org.example.dao.JsonFileImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonDaoImpl<T, ID> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File file;

    // Constructor to initialize the file
    public JsonDaoImpl(String filePath) {
        this.file = new File(filePath);
    }

    // Fetch all entries from the file
    public List<T> getAll() throws Exception {
        if (file.exists()) {
            return objectMapper.readValue(file, new TypeReference<List<T>>() {});
        }
        return new ArrayList<>();
    }

    // Fetch an entry by ID
    public T getById(ID id) throws Exception {
        List<T> currentData = getAll();
        for (T item : currentData) {
            if (item.toString().contains(id.toString())) {
                return item;
            }
        }
        return null;
    }

    // Append a new entry to the file
    public T add(T t) throws Exception {
        List<T> currentData = getAll(); // Load existing data
        currentData.add(t); // Append the new entry
        saveToFile(currentData); // Save the updated list
        return t;
    }

    // Update an entry (remove and re-add it)
    public T update(T t) throws Exception {
        delete(t); // Remove the existing entry
        add(t); // Add the updated entry
        return t;
    }

    // Delete an entry from the file
    public void delete(T t) throws Exception {
        List<T> currentData = getAll();
        currentData.remove(t);
        saveToFile(currentData);
    }

    // Save the list to the file
    private void saveToFile(List<T> data) throws Exception {
        objectMapper.writeValue(file, data);
    }

    // Main method for testing
    public static void main(String[] args) {
        try {
            // Example class to test the DAO
            class Book {
                private String id;
                private String title;

                public Book() {}

                public Book(String id, String title) {
                    this.id = id;
                    this.title = title;
                }

                @Override
                public String toString() {
                    return "Book{id='" + id + "', title='" + title + "'}";
                }

                // Getters and setters for ObjectMapper
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            // Create a JsonDaoImpl instance with file path
            JsonDaoImpl<Book, String> bookDao = new JsonDaoImpl<>("books.json");

            // Create some books
            Book book1 = new Book("1", "Effective Java");
            Book book2 = new Book("2", "Clean Code");

            // Add books
            System.out.println("Adding books...");
            bookDao.add(book1);
            bookDao.add(book2);

            // Get all books
            System.out.println("Fetching all books...");
            System.out.println(bookDao.getAll());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
