package com.example.demo.DAO;
import com.example.demo.model.BlogPost;
import com.example.demo.model.BlogTopic;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Repository
public class BlogDAO {
    public BlogTopic[] getTopics() {
        try{
            return null;
        }
        catch (Exception e){}
        return new BlogTopic[]{};
    }

    public List<BlogPost> loadBlogs(String blogTopic) throws Exception{
        List<BlogPost> list = new ArrayList<BlogPost>();
        try (Reader reader = Files.newBufferedReader(Path.of("/Users/claymallory/Desktop/demo/src/main/java/com/example/demo/blogs.csv"))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] nextLine;
                while ((nextLine = csvReader.readNext()) != null) {
                    if (nextLine[3].equals(blogTopic)) {
                        list.add(new BlogPost(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4]));
                    }
                }
            }
        }
        return list;
    }

    public List<BlogPost> getAllBlogs() throws Exception{
        List<BlogPost> list = new ArrayList<BlogPost>();
        try (Reader reader = Files.newBufferedReader(Path.of("/Users/claymallory/Desktop/demo/src/main/java/com/example/demo/blogs.csv"))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                String[] nextLine;
                while ((nextLine = csvReader.readNext()) != null) {
                        list.add(new BlogPost(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4]));
                }
            }
        }
        return list;
    }
}
