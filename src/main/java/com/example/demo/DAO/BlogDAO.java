package com.example.demo.DAO;

import com.example.demo.model.BlogPost;
import com.example.demo.model.BlogTopic;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;
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



    public List<BlogPost> getBlog(String blogTopic) throws Exception{
        if(blogTopic.equals("All")) {
            return getAllBlogs();
        }

        List<BlogPost> list = new ArrayList<BlogPost>();
        try (Reader reader = Files.newBufferedReader(Path.of("/Users/claymallory/Desktop/demo/src/main/java/com/example/demo/blogs.csv"))) {
            try (CSVReader csvReader = new CSVReader(reader)) {

                String[] arr = csvReader.readNext();
                String[] nextLine;
                while ((nextLine = csvReader.readNext()) != null) {
                    System.out.println(nextLine[3]);
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

                String[] arr = csvReader.readNext();
                String[] nextLine;
                while ((nextLine = csvReader.readNext()) != null) {

                        list.add(new BlogPost(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4]));


                }


            }
        }
        return list;
    }

    public List<String[]> readAllLines() throws Exception {
        try (Reader reader = Files.newBufferedReader(Path.of("/Users/claymallory/Desktop/demo/src/main/java/com/example/demo/blogs.csv"))) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                List<String[]> arr = csvReader.readAll();
                //System.out.println(arr.get(1)[0]);
                return csvReader.readAll();
            }
        }
    }
}
