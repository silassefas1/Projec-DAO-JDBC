package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
	
		System.out.println("=== TEST 1: Seller FindById ===");
		Department newDepartment = new Department(null, "Customer Service");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! New Department Id: "+ newDepartment.getId());
		

	}

}
