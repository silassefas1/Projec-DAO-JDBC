package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, object.getName());
			preparedStatement.setString(2, object.getEmail());
			preparedStatement.setDate(3, new java.sql.Date(object.getBirthDate().getTime()));
			preparedStatement.setDouble(4, object.getBaseSalary());
			preparedStatement.setInt(5, object.getDepartment().getId());
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if(rowsAffected>0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					int id = resultSet.getInt(1);
					object.setId(id);
				}
				DB.closeResultSet(resultSet);
			}else {
				throw new DbException("Enexpected Erro! no rows affected!");
			}
			
		
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(preparedStatement);
		}
		
	}

	@Override
	public void update(Seller object) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement =connection.prepareStatement(
					"UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary =?, DepartmentId = ? "
					+ "WHERE id = ?");
			preparedStatement.setString(1, object.getName());
			preparedStatement.setString(2, object.getEmail());
			preparedStatement.setDate(3, new java.sql.Date(object.getBirthDate().getTime()));
			preparedStatement.setDouble(4, object.getBaseSalary());
			preparedStatement.setInt(5, object.getDepartment().getId());
			preparedStatement.setInt(6, object.getId());
			
			preparedStatement.executeUpdate();
			
					
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(preparedStatement);
		}
		
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
	public List<Seller> findAll() {
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id"
					+ " ORDER BY Name"
					);
			resultSet = preparedStatement.executeQuery();
			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> departmentMap = new HashMap<>();
			
			while(resultSet.next()) {
				Department newDepartment = departmentMap.get(resultSet.getInt("DepartmentId"));
				if(newDepartment == null) {
					newDepartment = instantiateDepartment(resultSet);
					departmentMap.put(resultSet.getInt("DepartmentId"), newDepartment);
				}
				
				Seller object = instantiateSeller(resultSet, newDepartment);
				sellerList.add(object);
			}	
				return sellerList;
			
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findBydepartment(Department department) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =  null;
		
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id"
					+ " WHERE DepartmentId = ?"
					+ " ORDER BY Name");
			preparedStatement.setInt(1, department.getId());
			resultSet = preparedStatement.executeQuery();
			
			List<Seller> sellerList = new ArrayList<>();
			Map<Integer, Department> departmentMap = new HashMap<>();
			
			
			while(resultSet.next()) {
				Department newDepartment = departmentMap.get(resultSet.getInt("DepartmentId"));
				if(newDepartment == null) {
					newDepartment = instantiateDepartment(resultSet);
					departmentMap.put(resultSet.getInt("DepartmentId"), newDepartment);
				}
				
				Seller object = instantiateSeller(resultSet, newDepartment);
				sellerList.add(object);
				
			}
			return sellerList;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
		
	}

}
