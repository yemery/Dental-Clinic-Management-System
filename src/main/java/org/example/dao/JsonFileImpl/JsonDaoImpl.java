package org.example.dao.JsonFileImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.dao.IDao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonDaoImpl<T, ID> implements IDao<T, ID> {
    private final ObjectMapper objectMapper;
    private final File file;
    private static final String DIRECTORY_PATH = "database";
    private final Class<T> type;

    public JsonDaoImpl(String fileName, Class<T> type) {
        this.objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        // Create directory if it doesn't exist
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        this.file = new File(DIRECTORY_PATH, fileName);
        this.type = type;
    }

    public List<T> getAll() throws Exception {
        if (file.exists()) {
            return objectMapper.readValue(
                    file,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, type)
            );
        }
        return new ArrayList<>();
    }

    public T getById(ID id) throws Exception {
        List<T> currentData = getAll();
        for (T item : currentData) {
            Long itemId = (Long) item.getClass().getMethod("getId").invoke(item);
            if (itemId.equals(id)) {
                return item;
            }
        }
        return null;
    }

    public T add(T t) throws Exception {
        List<T> currentData = getAll(); // Load existing data
        currentData.add(t); // Append the new entry
        saveToFile(currentData); // Save the updated list
        return t;
    }

    public T update(T t) throws Exception {
        List<T> currentData = getAll();
        ID id = (ID) t.getClass().getMethod("getId").invoke(t);
        delete(id);
        add(t);
        return t;
    }

    public void delete(ID ID) throws Exception {
        System.out.println("Deleting ID: " + ID);
        List<T> currentData = getAll();
        T findObjectByID = getById(ID);
        currentData.remove(findObjectByID);
        saveToFile(currentData);
    }

    private void saveToFile(List<T> data) throws Exception {
        objectMapper.writeValue(file, data);
    }
}