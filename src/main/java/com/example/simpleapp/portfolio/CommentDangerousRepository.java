package com.example.simpleapp.portfolio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <b>WARNING!</b> Do NOT use in production. This class is purposefully designed
 * to add security vulnerabilities to the app, for educational purposes. Handle
 * with care!
 *
 */
@Component
public class CommentDangerousRepository {

	private static final String TABLE_NAME = "comment";
	private static final String COL_NAME_ID = "id";
	private static final String COL_NAME_NAME = "name";
	private static final String COL_NAME_MESSAGE = "message";

	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	public List<Comment> findAll() throws SQLException {

		List<Comment> result = new ArrayList<>();

		String selectSql = "SELECT * FROM " + TABLE_NAME;
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();
				ResultSet resultSet = stmt.executeQuery(selectSql)) {

			while (resultSet.next()) {
				Comment dto = new Comment();
				dto.setId(resultSet.getInt(COL_NAME_ID));
				dto.setName(resultSet.getString(COL_NAME_NAME));
				dto.setMessage(resultSet.getString(COL_NAME_MESSAGE));
				result.add(dto);
			}
		}

		return result;
	}

	public Comment save(Comment comment) throws SQLException {
		Comment result = null;

		// INSERT INTO comments (name, message) VALUES ('my name', 'my message')

		// TODO: Remove the SQL injection VULNERABILITY introduced here
		String sql = "INSERT INTO " + TABLE_NAME +" ("
				+ COL_NAME_NAME + ", " + COL_NAME_MESSAGE
				+ ") "
				+ "VALUES ('"
				+ comment.getName() + "', '" + comment.getMessage()
				+ "')";

		// String sql = "INSERT INTO " + TABLE_NAME + " (" + COL_NAME_NAME + ", " + COL_NAME_MESSAGE + ") VALUES (?, ?)";

		try (Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

			// stmt.setString(1, comment.getName());
			// stmt.setString(2, comment.getMessage());

			int rowsAffected = stmt.executeUpdate();
//			if (rowsAffected == 0) {
//				throw new SQLException("No rows affected.");
//			}
			
			int generatedId = 0;
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
				if(generatedKeys.next()) {
					generatedId = generatedKeys.getInt(1);
				}
				else {
					// throw new SQLException("No id obtained.");
				}
				
			}

			result = new Comment(generatedId, comment.getName(), comment.getMessage());
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, username, password);
	}
}
