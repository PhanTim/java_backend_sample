package levvel.io.controller;

import levvel.io.model.Blog;
import levvel.io.service.BlogService;
import levvel.io.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blog")
public class BlogController {


    private BlogService blogService;
    private ArrayList<Comment> comments;

    @PostMapping("/post")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog) {
        blog.setCreatedDate(LocalDateTime.now());
        blog.setLastModifiedDate(LocalDateTime.now());
        blogService.addBlog(blog);
        return ResponseEntity.ok().body(blog);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable String id) {
        Blog blog = blogService.getBlog(id);
        return ResponseEntity.ok().body(blog);
    }

    /*Adds comment to list of comments for specified blog given id*/
    @PostMapping("/post/{id}/comment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment, @PathVariable String id) {
        Blog blog = blogService.getBlog(id);
        comment.setCreateDate(LocalDateTime.now());
        comment.setLastModifiedDate(LocalDateTime.now());
        blogService.addComment(blog, comment);
        return ResponseEntity.ok().body(comment);
    }

    /*Returns list of comments for a specified blog given id */
    @GetMapping("/post/{id}/comment")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String id) {
        Blog blog = blogService.getBlog(id);
        return ResponseEntity.ok().body(blogService.getComments(blog));
    }



}
