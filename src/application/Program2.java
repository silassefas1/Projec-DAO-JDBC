package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
	
		System.out.println("=== TEST 1: Department Insert ===");
		Department newDepartment = new Department(null, "Customer Service");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! New Department Id: "+ newDepartment.getId());
		
		System.out.println("=== TEST 2: Department Update ===");
		Department updateDepartment = new Department(8, "Play Center");
		departmentDao.update(updateDepartment);
		System.out.println("Department Updated!");
		

		DB.closeConnection();
		
	}

}
