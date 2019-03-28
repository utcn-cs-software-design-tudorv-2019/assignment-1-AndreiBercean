package dataAccessLayer;

public class Subject 
{
	protected int ID;
	protected int teacherID;
	protected String name;
	
	public Subject(int id, int tID, String n)
	{
		ID = id;
		teacherID = tID;
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public int getTeacherID() {
		return teacherID;
	}
	
	public String toString()
	{
		return ID + ", " + teacherID + ", " + name + "\n";
	}
}
