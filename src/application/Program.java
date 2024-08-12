package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		//teste de funcionalidade
		Department object = new Department(1, "Books");
		//System.out.println(object);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = new Seller(21,"Bob", "bob@gmaiol.com", new Date(), 3000.0, object);
		System.out.println(seller);
	}

}
