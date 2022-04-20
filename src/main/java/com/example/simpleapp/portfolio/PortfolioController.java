package com.example.simpleapp.portfolio;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PortfolioController {

	@Autowired
	private CommentRepository commentsRepository;

	@GetMapping("/portfolio")
	public String getPortfolio(Model model, HttpSession session,
			@RequestParam(required = false, name = "theme") String theme) throws SQLException {

		prepareSessionAndModelData(model, session, theme);

		return "portfolio-template";
	}

	@PostMapping("/portfolio")
	public String createPortfolio(@ModelAttribute Comment comment, Model model, HttpSession session,
			@RequestParam(required = false, name = "theme") String theme) throws SQLException {

		commentsRepository.save(comment);

		prepareSessionAndModelData(model, session, theme);

		return "portfolio-template";
	}

	private void prepareSessionAndModelData(Model model, HttpSession session, String theme) throws SQLException {
		if (theme != null) {
			session.setAttribute("theme", theme);
		}
		model.addAttribute("theme", session.getAttribute("theme"));

		List<Comment> comments = commentsRepository.findAll();
		model.addAttribute("comments", comments);
	}
}
