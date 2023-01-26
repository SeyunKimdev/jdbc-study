package view;

import dao.UserDAO;

public class Test {
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
//		if(userDAO.checkId("hds1234")) {
//			System.out.println("사용 가능한 아이디");
//			return;
//		}
//		System.out.println("중복된 아이디");
		
		System.out.println(userDAO.select(1L));
		
	}
}
