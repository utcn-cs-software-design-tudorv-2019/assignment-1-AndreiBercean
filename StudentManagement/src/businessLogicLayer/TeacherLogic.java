package businessLogicLayer;

import java.util.List;
import dataAccessLayer.Enrolment;
import dataAccessLayer.EnrolmentDAO;
import dataAccessLayer.Report;
import dataAccessLayer.ReportDAO;
import dataAccessLayer.Subject;
import dataAccessLayer.SubjectDAO;
import dataAccessLayer.User;
import dataAccessLayer.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherLogic 
{
	private UserDAO user = new UserDAO();
	private SubjectDAO subject = new SubjectDAO();
	private EnrolmentDAO enrolment = new EnrolmentDAO();
	private ReportDAO report = new ReportDAO();
	
	public User viewUserInfo(int id)
	{
		List<User> result = user.SELECT(id);
		if(result.size()>0)return result.get(0);
		return null;
	}
	
	public void edit(User x)
	{
		user.UPDATE(x, x.getID());
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
	
	public ObservableList<Report> reportStudent(int ID)
	{
		ObservableList<Report> list = FXCollections.observableArrayList();
		for(Report s : report.SELECT(ID))
		{
			list.add(s);
		}
		return list;
	}
	
	public ObservableList<User> viewUsers()
	{
		ObservableList<User> list = FXCollections.observableArrayList();
		for(User s : user.SELECT_ALL())
		{
			list.add(s);
		}
		return list;
	}
	
	public void gradeStudent(int ID, int studentID, int subjectID, float grade)
	{
		enrolment.UPDATE(new Enrolment(ID, studentID, subjectID, grade), ID);
	}
}
