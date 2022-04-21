package com.example.simpleapp.portfolio;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PortfolioController {

	@Autowired
	private CommentRepository commentsRepository;

	@GetMapping("/portfolio")
	public String getPortfolio(Model model) throws SQLException {

		prepareModelData(model);

		return "portfolio-template";
	}

	@PostMapping("/portfolio")
	public String createPortfolio(@ModelAttribute Comment comment, Model model) throws SQLException {

		commentsRepository.save(comment);
		prepareModelData(model);

		return "portfolio-template";
	}

	private void prepareModelData(Model model) throws SQLException {
		List<Comment> comments = commentsRepository.findAll();
		model.addAttribute("comments", comments);
	}
}
