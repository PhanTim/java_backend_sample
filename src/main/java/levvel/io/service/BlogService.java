package levvel.io.service;

import levvel.io.model.Blog;
import levvel.io.model.Comment;

import java.util.List;

public interface BlogService {

    void addBlog(Blog blog);

    Blog getBlog(String id);

    /* Given blog, add comment to list of comments of blog*/
    void addComment(Blog blog, Comment comment);

    /* Given blog, return list of comments*/
    List<Comment> getComments(Blog blog);
}
