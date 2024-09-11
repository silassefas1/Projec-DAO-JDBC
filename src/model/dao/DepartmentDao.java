package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {

	void insert(Department object);
	void update(Department object);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();
}
