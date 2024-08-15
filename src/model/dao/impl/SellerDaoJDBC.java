package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection connection;
	
	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =  null;
		
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ? ");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Department department = instantiateDepartment(resultSet);
				Seller object = instantiateSeller(resultSet, department);
				return object;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
				
		
	}

	private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
		Seller object = new Seller();
		object.setId(resultSet.getInt("Id"));
		object.setName(resultSet.getString("Name"));
		object.setEmail(resultSet.getString("Email"));
		object.setBaseSalary(resultSet.getDouble("BaseSalary"));
		object.setBirthDate(resultSet.getDate("BirthDate"));
		object.setDepartment(department);
		return object;
	}

	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		Department department = new Department();
		department.setId(resultSet.getInt("DepartmentId"));
		department.setName(resultSet.getString("DepName"));
		return department;
	}

	@Override
	public List<Seller> dindAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
