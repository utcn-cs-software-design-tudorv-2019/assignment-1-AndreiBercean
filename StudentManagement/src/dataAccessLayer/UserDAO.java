package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> 
{
	protected List<User> createObjects(ResultSet resultSet)
	{
		List<User> list = new ArrayList<User>();
			try {
				while(resultSet.next())
				{
					int ID = (Integer)resultSet.getObject("ID");
					String name = (String)resultSet.getObject("name");
					int age = (Integer)resultSet.getObject("age");
					String password = (String)resultSet.getObject("password");
					list.add(new User(ID,name,age,password));
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return list;
	}
	
	private String createInsertQueryNoId(User obj)
	{
		StringBuilder s = new StringBuilder();
		s.append("INSERT INTO User (name, age, password) VALUES ('" + obj.getName() + "', " + obj.getAge() + ", '" + obj.getPassword() + "');");
		return s.toString();
	}
	
	public int INSERT_NO_ID(User obj)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int result;
		String query = createInsertQueryNoId(obj);
		//System.out.println("\n" + query);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			result = statement.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			//ConnectionFactory.close(connection);
		}
		return -1;
	}
}
