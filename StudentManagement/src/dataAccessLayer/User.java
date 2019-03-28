package dataAccessLayer;

public class User 
{
	protected int ID;
	protected String name;
	protected int age;
	protected String password;
	
	public User(int id, String n, int a, String p)
	{
		ID = id;
		name = n;
		age = a;
		password = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String toString()
	{
		return"\nID = " + ID + "\nname = " + name + "\nage = " + age + "\npassword = " + password;
	}
}
