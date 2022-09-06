package com.example.demo.service;

import com.example.demo.DAO.BlogDAO;
import com.example.demo.model.BlogPost;
import com.example.demo.model.BlogTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogDAO blogDAO;

    public BlogTopic[] getTopics() {
        try{
            return blogDAO.getTopics();
        }
        catch (Exception e) {}
        return new BlogTopic[]{};
    }


    public List<BlogPost> getBlog(String blogTopic) {
        try {
            return blogDAO.getBlog(blogTopic);
        }
        catch (Exception e){}
        return new ArrayList<BlogPost>();
    }

    public List<BlogPost> getAllBlogs() {
        try {
            return blogDAO.getAllBlogs();
        }
        catch (Exception e){}
        return new ArrayList<BlogPost>();

    }

}
