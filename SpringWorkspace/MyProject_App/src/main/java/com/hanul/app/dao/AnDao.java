package com.hanul.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AnDao {
	DataSource dataSource;

	public AnDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/team01");
			/*dataSource = (DataSource) context.lookup("java:/comp/env/CSS");*/
		} catch (NamingException e) {
			e.getMessage();
		}

	}
	
	//7. 회원가입 : command에서 넘어온 값을 받는다
	public int anJoin(String id, String passwd, String name, 
    							String phonenumber, String address) {
		
		// 데이터베이스와 연결하여 원하는 결과물을 얻는다
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into member(id, passwd, name, phonenumber, address) " + 
			               "values('" + id + "', '" + passwd + "', '" + name + "', '" + 
					        			phonenumber + "', '" + address + "' )";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + "삽입성공");				
			} else {
				System.out.println(state + "삽입실패");
			}
			
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		} finally {
			try {				
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (connection != null) {
					connection.close();
				}	

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}

		//8. 원하는 값을 리턴시킨다
		return state;
		
	}
	
	
	
	
}
