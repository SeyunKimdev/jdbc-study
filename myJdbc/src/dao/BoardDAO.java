package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.BoardVO;

public class BoardDAO {

	public Connection connection; //���� ��ü
	public PreparedStatement preparedStatement; //���� ���� ��ü
	public ResultSet resultSet; //��� ���̺� ��ü

	//	   ���� �ۼ��� �۸� �������� �����ϰ�
	//	   ���� ������ ������ ��ȸ�� ��

	//	   �Խñ� �߰�
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
			System.out.println("insert() SQL�� ����");
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

	//	   �Խñ� ��ȸ
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
			System.out.println("select() SQL�� ����");
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

	//	   �Խñ� ����
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
			System.out.println("update() SQL�� ����");
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

	//	   �Խñ� ����
	public void delete(Long boardId) {
		String query = "DELETE FROM TBL_BOARD WHERE BOARD_ID = ? AND USER_ID = ?";
		connection = DBConnecter.getConnection();

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, boardId);
			preparedStatement.setLong(2, UserDAO.userId);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("delete() SQL�� ����");
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

	//	   �Խñ� ��ü ��ȸ	<- �̰͸� �α��� ���� ����
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
			System.out.println("selectAll() SQL�� ����");
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
