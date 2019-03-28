package dataAccessLayer;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class AbstractDAO<T> 
{
	private final Class<T> type;
	
	@SuppressWarnings("unchecked")
	public AbstractDAO()
	{
		type = (Class<T>)((ParameterizedType)(Object) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private String createSelectQuery(String field)
	{
		StringBuilder s = new StringBuilder();
		s.append("SELECT ");
		s.append(" * ");
		s.append(" FROM ");
		s.append(type.getSimpleName());
		s.append(" WHERE " + field + " =?");
		return s.toString();
	}
	
	private String createSelectAllQuery()
	{
		StringBuilder s = new StringBuilder();
		s.append("SELECT ");
		s.append(" * ");
		s.append(" FROM ");
		s.append(type.getSimpleName());
		return s.toString();
	}
	
	private String createDeleteQuery(String field)
	{
		StringBuilder s = new StringBuilder();
		s.append("DELETE ");
		s.append("FROM ");
		s.append(type.getSimpleName());
		s.append(" WHERE " + field + " =?");
		return s.toString();
	}
	
	private String createInsertQuery(T obj)
	{
		StringBuilder s = new StringBuilder();
		s.append("INSERT INTO ");
		s.append(type.getSimpleName());
		s.append(" (");
		int size = type.getDeclaredFields().length, i  = 0;
		for (Field field : type.getDeclaredFields()) 
		{
		    i++;
		    if(i != 1)
		    {
		    	if(i == size)s.append(field.getName()+")");
		    	else s.append(field.getName()+", ");
		    }
		}
		s.append(" VALUES(");
		size = type.getDeclaredFields().length; i  = 0;
		for (Field field : type.getDeclaredFields()) 
		{
			try {
				Object value = field.get(obj);
			    i++;
			    if(i != 1)
			    {
			    	if(i == size)
			    	{
			    		if(field.getType().isAssignableFrom(String.class))s.append("'"+value.toString()+"')");
			    		else s.append(value.toString()+")");
			    	}
			    	else 
			    	{
			    		if(field.getType().isAssignableFrom(String.class))s.append("'"+value.toString()+"', ");
			    		else s.append(value.toString()+", ");
			    	}
			    }
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return s.toString();
	}
	
	public String createUpdateQuery(T obj)
	{
		StringBuilder s = new StringBuilder();
		s.append("UPDATE ");
		s.append(type.getSimpleName());
		s.append(" SET ");
		int size = type.getDeclaredFields().length, i  = 0;
		for (Field field : obj.getClass().getDeclaredFields()) 
		{
			try {
				Object value = field.get(obj);
			    i++;
			    if(i != 1)
			    {
			    	if(i == size)
			    	{
			    		if(field.getType().isAssignableFrom(String.class))s.append(field.getName() + " = " + "'"+value.toString()+"' ");
			    		else s.append(field.getName() + " = " + value.toString() + " ");
			    	}
			    	else 
			    	{
			    		if(field.getType().isAssignableFrom(String.class))s.append(field.getName() + " = " + "'"+value.toString()+"', ");
			    		else s.append(field.getName() + " = " + value.toString() + ", ");
			    	}
			    }
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		s.append("WHERE id =?");
		return s.toString();
	}
	
	protected List<T> createObjects(ResultSet resultSet)
	{
		return null;
	}
	
	public List<T> SELECT_ALL()
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAllQuery();
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
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
	
	public List<T> SELECT(int id )
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		
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
	
	public int DELETE(int id)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int result = 0;
		String query = createDeleteQuery("id");
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.toString();
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
	
	public int INSERT(T obj)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int result;
		String query = createInsertQuery(obj);
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
	
	public int UPDATE(T obj, int id)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int result;
		String query = createUpdateQuery(obj);
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
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
