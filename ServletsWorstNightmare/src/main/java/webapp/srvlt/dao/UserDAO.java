package webapp.srvlt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import webapp.srvlt.config.ConfigClass;
import webapp.srvlt.model.User;

public class UserDAO {
	private final static String SQL_GET_USER = "select login,passw from users where login=? and passw=?";
	private final static String SQL_CHECK_LOGIN = "SELECT login FROM users WHERE login = ?";
	private final static String SQL_INSERT_USER = "insert into users(login,passw) values(?,?)";

	public UserDAO() throws ClassNotFoundException, SQLException {
		new ConfigClass();
	}

//	public boolean insertUser(User user) throws SQLException, ClassNotFoundException {
//		String query = "insert into users(login,passw) values(?,?)";
//		Connection connection = ConfigClass.getConnection();
//		PreparedStatement ps = null;
//
//		try {
//			PreparedStatement preparedStatement = connection
//					.prepareStatement("SELECT login FROM users WHERE login = ?");
//			preparedStatement.setString(1, user.getLogin());
//			ResultSet result = preparedStatement.executeQuery();
//			if (result.next()) {
//				preparedStatement.close();
//				return false;
//			}
//			ps = connection.prepareStatement(query);
//
//			ps.setString(1, user.getLogin());
//			ps.setString(2, user.getPassw());
//			ps.executeUpdate();
//			return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	public boolean isValidUser(final String login, final byte[] password) throws ClassNotFoundException, SQLException {
		Connection connection = ConfigClass.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(SQL_GET_USER);

			ps.setString(1, login);
			ps.setBytes(2, password);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
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

	public boolean insertUser(User user) throws ClassNotFoundException, SQLException {
		Connection connection = ConfigClass.getConnection();
		PreparedStatement ps = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_LOGIN);
			preparedStatement.setString(1, user.getLogin());
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				preparedStatement.close();
				return false;
			} else {
				preparedStatement = connection.prepareStatement(SQL_INSERT_USER);

				preparedStatement.setString(1, user.getLogin());
				preparedStatement.setBytes(2, user.getPassw());

				preparedStatement.executeUpdate();
				preparedStatement.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
