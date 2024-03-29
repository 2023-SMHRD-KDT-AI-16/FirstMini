package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class memberDAO {

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	// connection
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String user = "mp_21K_bigdata22_p1_5";
			String pw = "smhrd5";
			String url = "jdbc:oracle:thin:@project-db-campus.smhrd.com:1523:xe";

			conn = DriverManager.getConnection(url, user, pw);
//         if (conn != null) {
//				System.out.println("연결 성공");
//			} else {
//				System.out.println("연결 실패");
//			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
	}

	// close 하는 메소드
	private void allClose() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// id중복
	public boolean id_Check(String id) {// 회원가입할 때 입력된 id

		getConn();
		String sql = "SELECT COUNT(ID) FROM MEMBER WHERE ID=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			rs.next();
			if (rs.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			allClose();
		}
		return false;
	}

	// 회원가입
	public int joinMember(memberDTO mdto) {
		getConn();
		String sql = "insert into member(id, pw, name) values( ?, ?, ? )";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mdto.getId());
			psmt.setString(2, mdto.getPw());
			psmt.setString(3, mdto.getName());

			int row = psmt.executeUpdate();

			return row;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			allClose();
		}
	}

	// 로그인
	public memberDTO login(String log_id, String log_pw) {
		// ArrayList<memberDTO> dtoList = new ArrayList<memberDTO>();

		getConn();
		memberDTO mdto = null;
		String sql = "select *  from member where id=? and pw=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, log_id);
			psmt.setString(2, log_pw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);
				String name = rs.getString(3);
				mdto = new memberDTO(id, pw, name);
			}
			return mdto;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 탈퇴
	public int delete(String del_id) {
		getConn();
		String sql = "delete from member where id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, del_id);
			int row = psmt.executeUpdate();

			return row;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			allClose();
		}
	}

	public songDTO selMusic(int index) {

		getConn();
		try {
			String sql = "SELECT * FROM (SELECT ROWNUM AS NUM,SINGER,SINGERE,SONG,SONGE,SINGERH,SONGH,FOLDER from song_list)X WHERE X.NUM = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, index);

			rs = psmt.executeQuery();
			songDTO sdto = null;
			while (rs.next()) {
				String select_singer = rs.getString(2);
				String select_singerEng = rs.getString(3);
				String select_song = rs.getString(4);
				String select_songEng = rs.getString(5);
				String select_hint = rs.getString(6);
				String select_hintSong = rs.getString(7);
				String select_path = rs.getString(8);

				sdto = new songDTO(select_singer, select_singerEng, select_song, select_songEng, select_hint,
						select_hintSong, select_path);
			}
			return sdto;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			allClose();
		}
	}

	public ArrayList<memberDTO> rank() {
		ArrayList<memberDTO> dtoList = new ArrayList<memberDTO>();

		getConn();
		try {
			String sql = "select * from (select * from member where max is not null order by max desc)where rownum<=5";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(3);
				int max1 = rs.getInt(4);
				memberDTO mdto = new memberDTO(id,name, max1,0);
				dtoList.add(mdto);
			}
			return dtoList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			allClose();
		}
	}

	public ArrayList<memberDTO> rank2() {
		ArrayList<memberDTO> dtoList = new ArrayList<memberDTO>();

		getConn();
		try {
			String sql = "select * from (select * from member where max2 is not null order by max2 desc)where rownum<=5";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(3);
				int max2 = rs.getInt(5);
				memberDTO mdto = new memberDTO(id,name,0,max2);
				dtoList.add(mdto);
			}
			return dtoList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			allClose();
		}
	}
	
	
	
	public void setMax(String id, int max) {
		getConn();

		try {
			String sql = "update member set max = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, max);
			psmt.setString(2, id);
			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			allClose();
		}

	}

	public int getMax(String id) {
		getConn();

		try {
			String sql = "select max from member where id= ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			int max = 0;
			while (rs.next()) {
				max = rs.getInt(1);
			}
			return max;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			allClose();
		}

	}

	public void setMax2(String id, int max) {
		getConn();

		try {
			String sql = "update member set max2 = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, max);
			psmt.setString(2, id);
			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			allClose();
		}

	}

	public int getMax2(String id) {
		getConn();

		try {
			String sql = "select max2 from member where id= ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			int max = 0;
			while (rs.next()) {
				max = rs.getInt(1);
			}
			return max;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			allClose();
		}

	}
}
