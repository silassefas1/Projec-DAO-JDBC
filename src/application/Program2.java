package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args) {
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Scanner scan = new Scanner(System.in);
	/*
		System.out.println("=== TEST 1: Department Insert ===");
		Department newDepartment = new Department(null, "Customer Service");
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! New Department Id: "+ newDepartment.getId());
		
		System.out.println("=== TEST 2: Department Update ===");
		Department updateDepartment = new Department(8, "Play Center");
		departmentDao.update(updateDepartment);
		System.out.println("Department Updated!");
		
		System.out.println("=== TEST 3: Department delete ===");
		System.out.print("Enter id for delete: ");
		int id = scan.nextInt();
		departmentDao.deleteById(id);
		System.out.print("Delete Completed");
		
		System.out.println("=== TEST 4: Department Find By Id ===");
		System.out.print("Enter id for find: ");
		id = scan.nextInt();
		Department department = departmentDao.findById(id);
		System.out.println(department);
		*/
		
		System.out.println("\n=== TEST 5: Department FindAll ===");
		List<Department> list = new ArrayList<>();
		list = departmentDao.findAll();
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		DB.closeConnection();
		scan.close();
	}

}
