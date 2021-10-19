package levvel.io;

import levvel.io.controller.BlogController;
import levvel.io.data.BlogRepository;
import levvel.io.model.Blog;
import levvel.io.model.Comment;
import levvel.io.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


import java.util.ArrayList;
import java.util.List;

@Import(BlogController.class)

@SpringBootTest
class WorkSampleApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private BlogService blogService;

	@Autowired
	private BlogRepository blogRepository;

	@Test
	void testBlogContent(){
		Blog someBlog = new Blog();
		someBlog.setAuthor("Weezer");
		someBlog.setTitle("The Blue Album");
		someBlog.setText("One hit wonder band");
		someBlog.setId("1");
		someBlog.setComments(new ArrayList<>());
		blogService.addBlog(someBlog);
		assert(blogService.getBlog("1").equals(someBlog));
	}

	@Test
	void testBlogComments(){
		/* Test Single Comment */
		Blog someBlog = new Blog();
		someBlog.setAuthor("Weezer");
		someBlog.setTitle("The Blue Album");
		someBlog.setText("One hit wonder band");
		someBlog.setId("1");
		someBlog.setComments(new ArrayList<>());

		Comment someComment = new Comment();
		someComment.setAuthor("Foo Fighters");
		someComment.setText("Personally I think Weezer has more than one good album.");

		blogService.addBlog(someBlog);
		blogService.addComment(someBlog, someComment);

		List<Comment> comments = new ArrayList<>();
		comments.add(someComment);

		assert (blogService.getComments(someBlog).equals(comments));

	}
		/* Test Multiple Comments*/
	@Test
	void testMultipleBlogComments() {
		/* Initialize Blog */
		Blog someBlog = new Blog();
		someBlog.setAuthor("Weezer");
		someBlog.setTitle("The Blue Album");
		someBlog.setText("One hit wonder band");
		someBlog.setId("1");
		someBlog.setComments(new ArrayList<>());
		List<Comment> comments = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			Comment ithComment = new Comment();
			ithComment.setAuthor(i + "");
			ithComment.setText(i + "");
			blogService.addComment(someBlog, ithComment);
			comments.add(ithComment);
		}

		assert (blogService.getComments(someBlog).equals(comments));
	}

	/* Test No Text in Comment */
	@Test
	void testNullandEmptyComments() {
		/* Initialize Blog */
		Blog someBlog = new Blog();
		someBlog.setAuthor("Weezer");
		someBlog.setTitle("The Blue Album");
		someBlog.setText("One hit wonder band");
		someBlog.setId("1");
		someBlog.setComments(new ArrayList<>());

		/* Null Text */
		Comment nullComment = new Comment();
		try{ blogService.addComment(someBlog, nullComment); }
		catch(Exception e){ assert("Please insert text for comment.".equals(e.getMessage()));}

		/* No Text */
		Comment emptyComment = new Comment();
		emptyComment.setText("");
		try{ blogService.addComment(someBlog, emptyComment); }
		catch(Exception e){ assert("Please insert text for comment.".equals(e.getMessage()));}
	}

	/* Test Empty Author for Comment */
	@Test
	void testNullAndEmptyAuthor() {
		/* Initialize Blog */
		Blog someBlog = new Blog();
		someBlog.setAuthor("Weezer");
		someBlog.setTitle("The Blue Album");
		someBlog.setText("One hit wonder band");
		someBlog.setId("1");
		someBlog.setComments(new ArrayList<>());

		/* Null Author */
		Comment nullComment = new Comment();
		nullComment.setText("Null Author");
		blogService.addComment(someBlog, nullComment);

		List<Comment> comments = new ArrayList<>();
		Comment nullCommentForList = new Comment();
		nullCommentForList.setAuthor("Anonymous");
		nullCommentForList.setText("Null Author");
		comments.add(nullCommentForList);

		assert (blogService.getComments(someBlog).equals(comments));

		/* Empty Author */
		Comment emptyComment = new Comment();
		emptyComment.setAuthor("");
		emptyComment.setText("Empty Author");
		blogService.addComment(someBlog, emptyComment);

		Comment emptyCommentForList = new Comment();
		emptyCommentForList.setAuthor("Anonymous");
		emptyCommentForList.setText("Empty Author");
		comments.add(emptyCommentForList);

		assert (blogService.getComments(someBlog).equals(comments));
	}
	/* Ran POST and GET Tests using Postman */
}