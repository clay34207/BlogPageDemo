package com.example.demo.controller;

import com.example.demo.model.BlogPost;
import com.example.demo.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    private BlogService blogService;

    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @GetMapping("/allBlogs")
    public ResponseEntity<List<BlogPost>> getAllBlogs() throws Exception{ // Returns all blogs
        List<BlogPost> blogs = blogService.getAllBlogs();
        return new ResponseEntity<List<BlogPost>>(blogs, HttpStatus.OK);
    }


    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<BlogPost>> loadBlogs(@PathVariable("topic") String blogTopic) throws Exception { // Returns all blogs that belong to a given topic
        List<BlogPost> post = blogService.loadBlogs(blogTopic);
        return new ResponseEntity<List<BlogPost>>(post, HttpStatus.OK);
    }
}
