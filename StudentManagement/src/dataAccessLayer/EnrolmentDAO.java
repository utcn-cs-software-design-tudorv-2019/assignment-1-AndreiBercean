package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrolmentDAO extends AbstractDAO<Enrolment> 
{
	protected List<Enrolment> createObjects(ResultSet resultSet)
	{
		List<Enrolment> list = new ArrayList<Enrolment>();
			try {
				while(resultSet.next())
				{
					int ID = (Integer)resultSet.getObject("ID");
					int studentID = (Integer)resultSet.getObject("studentID");
					int subjectID = (Integer)resultSet.getObject("subjectID");
					float grade = (Float)resultSet.getObject("grade");
					list.add(new Enrolment(ID,studentID,subjectID,grade));
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
	
	private String createInsertQueryNoId(Enrolment obj)
	{
		StringBuilder s = new StringBuilder();
		s.append("INSERT INTO Enrolment (StudentID, SubjectID, grade) VALUES ('" + obj.getStudentID() + "', " + obj.getSubjectID() + ", '" + obj.getGrade() + "');");
		return s.toString();
	}
	
	public int INSERT_NO_ID(Enrolment obj)
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
