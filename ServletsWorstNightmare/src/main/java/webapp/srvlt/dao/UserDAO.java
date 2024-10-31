package webapp.srvlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webapp.srvlt.config.ConfigClass;
import webapp.srvlt.model.User;

public class UserDAO {

	public UserDAO() throws ClassNotFoundException, SQLException {
		new ConfigClass();
	}

	public boolean isValidUser(final String login, final String password) throws ClassNotFoundException, SQLException {
		Connection connection = ConfigClass.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("select login,passw from users where login=? and passw=?");

			ps.setString(1, login);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	public boolean insertUser(User user) throws SQLException, ClassNotFoundException {
		String query = "insert into users(login,passw) values(?,?)";
		Connection connection = ConfigClass.getConnection();
		PreparedStatement ps = null;

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT login FROM users WHERE login = ?");
			preparedStatement.setString(1, user.getLogin());
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				preparedStatement.close();
				return false;
			}
			ps = connection.prepareStatement(query);

			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassw());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
