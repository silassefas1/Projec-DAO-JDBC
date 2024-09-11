package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection connection;
	
	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department object) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO department (Name) VALUES (?) "
					,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, object.getName());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected >0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					int id = resultSet.getInt(1);
					object.setId(id);
				}
				DB.closeResultSet(resultSet);
			}else {
				throw new DbException("Unexpected Error! No rows affected!");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(preparedStatement);
			
		}
		
	}

	@Override
	public void update(Department object) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE department "
					+ "SET Name = ? "
					+ "WHERE "
					+ "Department.Id = ?");
			preparedStatement.setString(1, object.getName());
			preparedStatement.setInt(2, object.getId());
			
			preparedStatement.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(preparedStatement);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(
					"DELETE FROM department "
					+ "WHERE Department.Id = ?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(preparedStatement);
		}
		
	}
	
	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setId(resultSet.getInt("department.Id"));
		department.setName(resultSet.getString("department.Name"));
		return department;
	}


	@Override
	public Department findById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM department WHERE department.Id = ?");
			preparedStatement.setInt(1, id);
		
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Department department = instantiateDepartment(resultSet);
				return department;
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(preparedStatement);
		}

		return null;
	}
	

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}



}
