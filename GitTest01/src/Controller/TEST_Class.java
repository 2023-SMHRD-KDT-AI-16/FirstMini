package Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.MemberDTO;

public class TEST_Class {
	
	public ArrayList<songDTO> selMusic() {
		ArrayList<songDTO> dtoList = new ArrayList<songDTO>();
		
		getConn();
		try {
			String sql = "select *\r\n"
					+ "from ( select *  \r\n"
					+ "       from member\r\n"
					+ "       where max is not null\r\n"
					+ "       order by max desc )\r\n"
					+ "where rownum<=5;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String table_pw = rs.getString(2);
				String name = rs.getString(3);
				int age = rs.getInt(4);
				songDTO sdto = new songDTO(id, table_pw, name, age);
				dtoList.add(sdto);
			} 
			return dtoList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			allClose();
		}
	}
	
	
	
	




}
