package dataAccessLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends AbstractDAO<Student>
{
	protected List<Student> createObjects(ResultSet resultSet)
	{
		List<Student> list = new ArrayList<Student>();
			try {
				while(resultSet.next())
				{
					int userID = (Integer)resultSet.getObject("ID");
					int group = (Integer)resultSet.getObject("group");
					list.add(new Student(userID,group));
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
}
