package dataAccessLayer;

public class Enrolment 
{
	protected int ID;
	protected int studentID;
	protected int subjectID;
	protected float grade;
	
	public Enrolment(int id, int s, int sub, float g)
	{
		ID = id;
		studentID = s;
		subjectID = sub;
		grade = g;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public int getID() {
		return ID;
	}

	public int getStudentID() {
		return studentID;
	}

	public int getSubjectID() {
		return subjectID;
	}
	
	public String toString()
	{
		return"\nID = " + ID + "\nstudent = " + studentID + "\nsubjectID = " + subjectID + "\ngrade = " + grade;
	}
}
