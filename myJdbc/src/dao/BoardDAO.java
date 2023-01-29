package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.BoardVO;

public class BoardDAO {

	public Connection connection; //연결 객체
	public PreparedStatement preparedStatement; //쿼리 관리 객체
	public ResultSet resultSet; //결과 테이블 객체

	//	   내가 작성한 글만 수정삭제 가능하고
	//	   수정 삭제할 때에는 조회한 후

	//	   게시글 추가
	public void insert(BoardVO boardVO) {
		String query = "INSERT INTO TBL_BOARD (BOARD_ID, BOARD_TITLE, BOARD_CONTENT, BOARD_REGISTER_DATE, USER_ID)"
				+ "VALUES(SEQ_BOARD.NEXTVAL, ?, ?, SYSDATE, ?) ";
		connection = DBConnecter.getConnection();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, boardVO.getBoardTitle());
			preparedStatement.setString(2, boardVO.getBoardContent());
			preparedStatement.setLong(3, UserDAO.userId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("insert() SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//				if(resultSet != null) {
				//					resultSet.close();
				//				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}

	}

	//	   게시글 조회
	public BoardVO select() {
		String query = "SELECT BOARD_ID, BOARD_TITLE, BOARD_CONTENT, BOARD_REGISTER_DATE, BOARD_UPDATE_DATE, USER_ID "
				+ "FROM TBL_USER U JOIN TBL_BOARD B ON U.USER_ID = B.USER_ID AND BOARD_ID = ?";
		connection = DBConnecter.getConnection();

		BoardVO board = null;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, UserDAO.userId);

			resultSet = preparedStatement.executeQuery();

			if(resultSet.next()) {
				board = new BoardVO();
				board.setBoardId(resultSet.getLong(1));
				board.setBoardTitle(resultSet.getString(2));
				board.setBoardRegisterDate(resultSet.getString(3));
				board.setBoardUpdateDate(resultSet.getString(4));
				board.setUserId(resultSet.getLong(5));
			}
		} catch (SQLException e) {
			System.out.println("select() SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return board;
	}

	//	   게시글 수정
	public void update(BoardVO boardVO) {
		String query = "UPDATE TBL_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ?, BOARD_REGISTER_DATE =?, BOARD_UPDATE_DATE = SYSDATE "
				+ "WHERE BOARD_ID = ?";
		connection = DBConnecter.getConnection();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, boardVO.getBoardTitle());
			preparedStatement.setString(2, boardVO.getBoardContent());
			preparedStatement.setString(3, boardVO.getBoardRegisterDate());
			preparedStatement.setLong(4, boardVO.getBoardId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("update() SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
//				if(resultSet != null) {
//					resultSet.close();
//				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	//	   게시글 삭제
	public void delete(Long boardId) {
		String query = "DELETE FROM TBL_BOARD WHERE BOARD_ID = ? AND USER_ID = ?";
		connection = DBConnecter.getConnection();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, boardId);
			preparedStatement.setLong(2, UserDAO.userId);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("delete() SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
//				if(resultSet != null) {
//					resultSet.close();
//				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	//	   게시글 전체 조회	<- 이것만 로그인 없이 가능
	public ArrayList<BoardVO> selectAll() {
		String query = "SELECT BOARD_ID, BOARD_TITLE, BOARD_CONTENT, BOARD_REGISTER_DATE, BOARD_UPDATE_DATE, USER_ID "
				+ "FROM TBL_BOARD";
		connection = DBConnecter.getConnection();
		
		ArrayList<BoardVO> boards = null;
		BoardVO board = null;
		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				board = new BoardVO();
				board.setBoardId(resultSet.getLong(1));
				board.setBoardTitle(resultSet.getString(2));
				board.setBoardRegisterDate(resultSet.getString(3));
				board.setBoardUpdateDate(resultSet.getString(4));
				board.setUserId(resultSet.getLong(5));
				
				boards.add(board);
			}
		} catch (SQLException e) {
			System.out.println("selectAll() SQL문 오류");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(resultSet != null) {
					resultSet.close();
				}
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return boards;
	}
}
