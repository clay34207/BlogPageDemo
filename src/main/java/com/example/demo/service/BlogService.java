package com.example.demo.service;

import com.example.demo.DAO.BlogDAO;
import com.example.demo.model.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogDAO blogDAO;

    public List<BlogPost> loadBlogs(String blogTopic) { // Gets all blog posts that belong to a given topic
        try {
            return blogDAO.loadBlogs(blogTopic);
        }
        catch (Exception e){}
        return new ArrayList<BlogPost>();
    }

    public List<BlogPost> getAllBlogs() { // Gets all blog posts
        try {
            return blogDAO.getAllBlogs();
        }
        catch (Exception e){}
        return new ArrayList<BlogPost>();

    }

}
