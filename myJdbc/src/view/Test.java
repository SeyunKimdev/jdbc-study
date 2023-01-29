package view;

import dao.UserDAO;
import domain.UserVO;

public class Test {
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		UserVO userVO = new UserVO();
//				if(userDAO.checkId("ksy12345")) {
//					System.out.println("사용 가능한 아이디");
//					return;
//				}
//				System.out.println("중복된 아이디");
				

//		userVO.setUserIdentification("ksy1");
//		userVO.setUserName("김세윤");
//		userVO.setUserPassword("1234"); userVO.setUserPhone("01056337014");
//		userVO.setUserNickname("김김세윤"); userVO.setUserEmail("ksy1@naver.com");
//		userVO.setUserAddress("경기도 성남시 분당구"); userVO.setUserBirth("971015"); //
//		userVO.setUserGender("M"); userVO.setUserRecommenderId("ksy12345");
//		userDAO.signUp(userVO);

		//		System.out.println(userDAO.login("ksy1234", "1234"));
		//		System.out.println(userDAO.login("ksy1234567", "1234"));
		//		System.out.println(userDAO.encryptPassword("1234"));
		//		userDAO.delete(7L);
		//		System.out.println(userDAO.findId("01056337014"));
		//		userDAO.changePassword("ksy12345", "123");
		
		//		System.out.println(userDAO.select(1L));

//		System.out.println(userDAO.select(6L));
//		System.out.println(userDAO.countRecommend("ksy12345"));
//		System.out.println(userDAO.recommendedMe("ksy12345"));
//		System.out.println(userDAO.recommendMe("ksy1"));
	}
}
