package businessLogicLayer;

import dataAccessLayer.Student;
import dataAccessLayer.StudentDAO;
import dataAccessLayer.Subject;
import dataAccessLayer.SubjectDAO;
import dataAccessLayer.User;
import dataAccessLayer.UserDAO;
import javafx.collections.*;

import java.util.List;

import dataAccessLayer.Enrolment;
import dataAccessLayer.EnrolmentDAO;

public class StudentLogic 
{
	private UserDAO user = new UserDAO();
	private StudentDAO student = new StudentDAO();
	private EnrolmentDAO enrolment = new EnrolmentDAO();
	private SubjectDAO subject = new SubjectDAO();
	
	public User viewUserInfo(int id)
	{
		List<User> result = user.SELECT(id);
		if(result.size()>0)return result.get(0);
		return null;
	}
	
	public Student viewStudentInfo(int id)
	{
		List<Student> result = student.SELECT(id);
		if(result.size()>0)return result.get(0);
		return null;
	}
	
	public void edit(User x)
	{
		user.UPDATE(x, x.getID());
	}
	
	public void delete(int id)
	{
		user.DELETE(id);
	}
	
	public ObservableList<Subject> viewSubjects()
	{
		ObservableList<Subject> list = FXCollections.observableArrayList();
		for(Subject s : subject.SELECT_ALL())
		{
			list.add(s);
		}
		return list;
	}
	
	public void enrol(int studentID, int subjectID)
	{
		enrolment.INSERT_NO_ID(new Enrolment(1,studentID,subjectID,0.0f));
	}
}
