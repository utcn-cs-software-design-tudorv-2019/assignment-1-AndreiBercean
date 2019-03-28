package dataAccessLayer;

public class Student 
{	
	protected int userID;
	protected int group;
	
	public Student(int id, int g)
	{
		userID = id;
		group = g;
	}
	
	public int getGroup()
	{
		return group;
	}
	
	public void setGroup(int group)
	{
		this.group = group;
	}
	
	public int getUserID()
	{
		return userID;
	}
}
