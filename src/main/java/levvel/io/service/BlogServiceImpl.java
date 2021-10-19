package levvel.io.service;

import levvel.io.data.BlogRepository;
import levvel.io.model.Blog;
import levvel.io.model.Comment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {

    private BlogRepository blogRepository;

    @Override
    public void addBlog(Blog blog) {
        blog.setComments(new ArrayList<>());
        blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(String id) {
        return blogRepository.findById(id).orElseGet(null);
    }

    /* Given blog, add comment to list of comments of blog*/
    @Override
    public void addComment(Blog blog, Comment comment){
        if(comment.getText() != null && !comment.getText().equals("")) {
            if(comment.getAuthor() == null || comment.getAuthor().equals("") ) { comment.setAuthor("Anonymous"); }
            blog.getComments().add(comment);
            blogRepository.save(blog);
        }
        else{ throw new NullPointerException("Please insert text for comment."); }
    }

    /* Given blog, return list of comments*/
    @Override
    public List<Comment> getComments(Blog blog){ return blog.getComments(); }

}
