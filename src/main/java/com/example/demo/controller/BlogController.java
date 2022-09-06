package com.example.demo.controller;

import com.example.demo.model.BlogPost;
import com.example.demo.model.BlogTopic;
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

    @GetMapping("/topics")
    public ResponseEntity<BlogTopic[]> getTopics(@PathVariable("topic") String blogTopic) {
            BlogTopic[] topics = blogService.getTopics();
            return new ResponseEntity<BlogTopic[]>(topics, HttpStatus.OK);
    }

    @GetMapping("/allBlogs")
    public ResponseEntity<List<BlogPost>> getAllBlogs() throws Exception{
        List<BlogPost> blogs = blogService.getAllBlogs();
        return new ResponseEntity<List<BlogPost>>(blogs, HttpStatus.OK);
    }


    @GetMapping("/topic/{topic}")
    public ResponseEntity<List<BlogPost>> getBlog(@PathVariable("topic") String blogTopic) throws Exception {
            List<BlogPost> post = blogService.getBlog(blogTopic);
            return new ResponseEntity<List<BlogPost>>(post, HttpStatus.OK);
    }
}
