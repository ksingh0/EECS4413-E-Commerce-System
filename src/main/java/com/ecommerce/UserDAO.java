package  com.ecommerce;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
// CHECK if username exists
	public boolean existsByUsername(String username) {
		String sql = "SELECT 1 FROM users WHERE username = ?";
		try (Connection conn = DatabaseConnection.connectUsers();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			return ps.executeQuery().next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// CREATE User
	public User create(User user) {
		String sql = "INSERT INTO users (username, password, first_name, last_name, street_name, street_number, city,state, country, postal_code,is_seller) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.connectUsers();
			 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getStreetName());
			ps.setString(6, user.getStreetNumber());
			ps.setString(7, user.getCity());
			ps.setString(8, user.getState());
			ps.setString(9, user.getCountry());
			ps.setString(10, user.getPostalCode());
			ps.setBoolean(11, user.isSeller());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					user.setId(rs.getLong(1));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	// READ by id (fetch a user by Id)
	public User findById(long id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		try (Connection conn = DatabaseConnection.connectUsers();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getLong("id"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setFirstName(rs.getString("first_name"));
					u.setLastName(rs.getString("last_name"));
					u.setStreetName(rs.getString("street_name"));
					u.setStreetNumber(rs.getString("street_number"));
					u.setCity(rs.getString("city"));
					u.setState(rs.getString("state"));
					u.setCountry(rs.getString("country"));
					u.setPostalCode(rs.getString("postal_code"));
					u.setSeller(rs.getBoolean("is_seller"));
					return u;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	// list all users
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		List<User> users = new ArrayList<>();
		try (Connection conn = DatabaseConnection.connectUsers();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirstName(rs.getString("first_name"));
				u.setLastName(rs.getString("last_name"));
				u.setStreetName(rs.getString("street_name"));
				u.setStreetNumber(rs.getString("street_number"));
				u.setCity(rs.getString("city"));
				u.setState(rs.getString("state"));
				u.setCountry(rs.getString("country"));
				u.setPostalCode(rs.getString("postal_code"));
				u.setSeller(rs.getBoolean("is_seller"));
				users.add(u);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return users;
	}

	// UPDATE a USER by id
	public User update(long id, User user) {
		String sql = "UPDATE users SET password = ?, first_name = ?, last_name = ?, street_name = ?, street_number = ?, city = ?, state = ? , country = ?, postal_code = ? WHERE id = ?";
		try (Connection conn = DatabaseConnection.connectUsers();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getStreetName());
			ps.setString(5, user.getStreetNumber());
			ps.setString(6, user.getCity());
			ps.setString(7, user.getState());
			ps.setString(8, user.getCountry());
			ps.setString(9, user.getPostalCode());
			ps.setLong(10, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		user.setId(id);
		return user;
	}

	// DELETE A USER by id
	public void delete(long id) {
		String sql = "DELETE FROM users WHERE id = ?";
		try (Connection conn = DatabaseConnection.connectUsers();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// READ by username (fetch a user by username)
	public User findByUsername(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		try (Connection conn = DatabaseConnection.connectUsers();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User u = new User();
					u.setId(rs.getLong("id"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setFirstName(rs.getString("first_name"));
					u.setLastName(rs.getString("last_name"));
					u.setStreetName(rs.getString("street_name"));
					u.setStreetNumber(rs.getString("street_number"));
					u.setCity(rs.getString("city"));
					u.setState(rs.getString("state"));      
		            u.setCountry(rs.getString("country"));
		            u.setPostalCode(rs.getString("postal_code"));
		            u.setSeller(rs.getBoolean("is_seller")); 
					return u;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
