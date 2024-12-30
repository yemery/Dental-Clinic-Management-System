package org.example.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextDaoImpl<T> {
    private final File file;
    private final Class<T> entityClass;

    public TextDaoImpl(String filePath, Class<T> entityClass) {
        this.file = new File(filePath);
        this.entityClass = entityClass;
    }

    public List<T> getAll() throws Exception {
        List<T> entities = new ArrayList<>();
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    T entity = entityClass.getDeclaredConstructor(String.class).newInstance(line);
                    entities.add(entity);
                }
            }
        }
        return entities;
    }

    public T getById(String id) throws Exception {
        List<T> currentData = getAll();
        for (T entity : currentData) {
            if (entity.toString().contains(id)) {
                return entity;
            }
        }
        return null;
    }

    public void add(T entity) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(entity.toString());
            writer.newLine();
        }
    }

    public void update(T oldEntity, T newEntity) throws Exception {
        List<T> currentData = getAll();
        currentData.remove(oldEntity);
        currentData.add(newEntity);
        saveToFile(currentData);
    }

    public void delete(T entity) throws Exception {
        List<T> currentData = getAll();
        currentData.remove(entity);
        saveToFile(currentData);
    }

    private void saveToFile(List<T> data) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (T entity : data) {
                writer.write(entity.toString());
                writer.newLine();
            }
        }
    }

    public static class Book {
        private final String id;
        private final String title;
        private final String author;
        private final String description;

        public Book(String id, String title, String author, String description) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.description = description;
        }

        public Book(String csvLine) {
            String[] parts = csvLine.split(",");
            this.id = parts[0];
            this.title = parts[1];
            this.author = parts[2];
            this.description = parts[3];
        }

        @Override
        public String toString() {
            return id + "," + title + "," + author + "," + description;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Book book = (Book) obj;
            return id.equals(book.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        try {
            TextDaoImpl<Book> bookDao = new TextDaoImpl<>("books.txt", Book.class);

            List<Book> books = new ArrayList<>();
            books.add(new Book("1", "Effective Java", "Joshua Bloch", "A comprehensive guide to programming in Java."));
            books.add(new Book("2", "Clean Code", "Robert C. Martin", "A handbook of agile software craftsmanship."));
            books.add(new Book("3", "Clean Code", "Robert C. Martin", "A handbook of agile software craftsmanship."));


            System.out.println("Adding books...");
            for (Book book : books) {
                bookDao.add(book);
            }

            System.out.println("Fetching all books...");
            List<Book> allBooks = bookDao.getAll();
            allBooks.forEach(System.out::println);

            System.out.println("Deleting the first book...");
            bookDao.delete(books.get(0));

            System.out.println("Fetching all books after deletion...");
            bookDao.getAll().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}