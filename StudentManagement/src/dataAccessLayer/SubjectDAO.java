package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO extends AbstractDAO<Subject> 
{
	protected List<Subject> createObjects(ResultSet resultSet)
	{
		List<Subject> list = new ArrayList<Subject>();
			try {
				while(resultSet.next())
				{
					int ID = (Integer)resultSet.getObject("ID");
					int teacherID = (Integer)resultSet.getObject("teacherID");
					String name = (String)resultSet.getObject("name");
					list.add(new Subject(ID,teacherID,name));
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
	
	private String createInsertQueryNoId(Subject obj)
	{
		StringBuilder s = new StringBuilder();
		s.append("INSERT INTO Subject (TeacherID, name) VALUES ('" + obj.getTeacherID() + "', '" + obj.getName() + "');");
		return s.toString();
	}
	
	public int INSERT_NO_ID(Subject obj)
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
