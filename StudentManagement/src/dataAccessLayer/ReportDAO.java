package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO
{	
	private List<Report> createObjects(ResultSet resultSet)
	{
		List<Report> list = new ArrayList<Report>();
			try {
				while(resultSet.next())
				{
					int ID = (Integer)resultSet.getObject("ID");
					String studentName = (String)resultSet.getObject("Student name");
					String subjectName = (String)resultSet.getObject("Subject Name");
					float grade = (float)resultSet.getObject("Grade");
					list.add(new Report(ID,studentName,subjectName,grade));
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
	
	private String createSelectQuery()
	{
		StringBuilder s = new StringBuilder();
		s.append("SELECT enrolment.ID AS \"ID\",user.name AS\"Student name\",subject.name AS \"Subject Name\",enrolment.grade AS \"Grade\" FROM enrolment,subject,user WHERE enrolment.StudentID = ? AND enrolment.SubjectID = subject.ID AND enrolment.StudentID = user.ID;");
		return s.toString();
	}
	
	public List<Report> SELECT(int id )
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery();
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			//ConnectionFactory.close(connection);
		}
		return null;
	}
}
