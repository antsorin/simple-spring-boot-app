package com.example.simpleapp.portfolio;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioRestController {

	@Autowired
	private CommentDangerousRepository commentRepository;

	@GetMapping("/api/portfolio/comments")
	public List<Comment> getComments() throws SQLException {
		List<Comment> result = commentRepository.findAll();
		return result;
	}

	@PostMapping("/api/portfolio/comments")
	public Comment createComment(@RequestBody Comment comment) throws SQLException {
		Comment result = commentRepository.save(comment);
		return result;
	}
}
