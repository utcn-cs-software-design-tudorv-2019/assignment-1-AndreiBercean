package dataAccessLayer;

public class Report 
{
	protected int ID;
	protected String studentName;
	protected String subjectName;
	protected float grade;
	
	public Report(int id, String stN, String suN, float g)
	{
		ID = id;
		studentName = stN;
		subjectName = suN;
		grade = g;
	}

	public int getID() {
		return ID;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public float getGrade() {
		return grade;
	}
	
	public String toString()
	{
		return "\nID = " + ID + "\nStudent = " + studentName + "\nSubject = " + subjectName + "\nGRADE = " + grade;
	}
}
