package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.web.connection.ConnectionProvider;
import com.mvc.web.connection.JdbcUtil;
import com.mvc.web.entity.content.EtcList;
import com.mvc.web.entity.content.Notice;
import com.mvc.web.entity.content.Picture;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

public class ContentDAO {
	/* 글 목록 */

//	public List<Notice> getList(){
//		return getList(1,"");
//	}
//
//	public List<Notice> getList(int page){
//		return getList(page,"");
//	}
	
	private static ContentDAO instance=new ContentDAO(); //처음에 생성한 하나의 dao
	
	public static ContentDAO getInstance() {
		return instance;
		
	}
	
	public EtcList getList(int page, String field, String qurry,String rank) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		int start = 1+ (page-1)*10;
		int end = page*10;
		int count=0;
		
		String sql = "select * ,  (select count(id) "
						+ "		     from tbl_board "
						+ "		    where "+field+" like ? "
						+ "		     and useFlag ='Y'"
						+ "         and boardid in (select boardID "
						+ "			from user_auth "
						+ "        where rankcd=?)) as count "
						+ "	from (select @rownum:=@rownum+1 as num ,n.* "
						+ "		    from( select * "
						+ "		         from tbl_board "
						+ "				 where "+field+" like ? "
						+ "		          and useFlag ='Y'"
						+ "                and boardid in (select boardID "
						+ "					from user_auth "
						+ "                 where rankcd=?) "
						+ "     order by regdate desc)n "
						+ "		  where (@rownum:=0)=0)num "
						+ " where num.num between ? and ?  "; // 조회 sql
		
		List<Notice> noticeList = new ArrayList<>();
		
		try {
			con=ConnectionProvider.getConnection();
			psmt=con.prepareStatement(sql);
			psmt.setString(1, "%"+qurry+"%");
			psmt.setString(2, rank);
			psmt.setString(3, "%"+qurry+"%");
			psmt.setString(4, rank);
			psmt.setInt(5, start);
			psmt.setInt(6, end);
			 rs = psmt.executeQuery();
			while (rs.next()) {
				int id1 = rs.getInt("id");
				String boardid=rs.getString("boardid");
				String title = rs.getString("title");
				String writeid = rs.getString("writeid");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				 count = rs.getInt("count");
			
				//조회 된 값을 입력하여 초기화하는 생성자 생성
				Notice ns = new Notice(id1, title, writeid, content, regdate, hit);
				noticeList.add(ns);
				
				//list 에 조회된 값이 저장된 notice 객체 추가
							
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		EtcList el= new EtcList(count,noticeList);
		
		return el;
	}
	
	/*자세히 보기*/
	public Notice getDetail(int no) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		String sql = " SELECT tb.id, bm.board_name, tb.title, tb.writeid, tb.content, tb.regdate, tb.hit "
				+ "      FROM tbl_board tb, "
				+ "	          board_master bm "
				+ "     WHERE bm.board_id = tb.boardid "
				+ "       AND tb.useFlag = 'Y' "
				+ "       AND tb.id = ?  ";
		Notice ns = null;
		try {
			 con = ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			psmt.setInt(1, no);
			 rs = psmt.executeQuery();

			if (rs.next()) {
				int id1 = rs.getInt("id");
				String board =rs.getString("board_name");
				String title = rs.getString("title");
				String writeid = rs.getString("writeid");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				//조회 된 값을 입력하여 초기화하는 생성자 생성
				 ns = new Notice(board,id1, title, writeid, content, regdate, hit);
				//list 에 조회된 값이 저장된 notice 객체 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		return ns;
	}

	public int getCount(String field, String qurry) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		String sql="select count(id) from notice where "+field+" like ? and useFlag='Y'";
		int count=0;
		
		try {
			//Class.forName(driver);  필드 값으로 driver 명칭 선언
			 con = ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			psmt.setString(1, "%"+qurry+"%");
			 rs = psmt.executeQuery();

			if (rs.next()) {
				count=rs.getInt(1);
	
			}
			System.out.println("count : "+count);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		return count;
	}

	public int regeditNotice(Notice nt) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		String sql="insert into tbl_board(writeid,title,content) value(?,?,?)";
		int result=0;
		try {
			 con =ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			psmt.setString(1, nt.getWriteid());
			psmt.setString(2, nt.getTitle());
			psmt.setString(3, nt.getContent());
			result=psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		return result;
	}
	
	/*글수정*/
	public int update(String title, String content, int id1) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		String sql="update tbl_board set title=?,content=? where id=? ";
		int result=0;
		try {
			 con =ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2,content);
			psmt.setInt(3, id1);
			result=psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		return result;
	}

	
	public String getCheckUser(int id1) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		String sql="select writeid from notice where id=?";
		String writeid="";
		
		try {
			 con =ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			 psmt.setInt(1, id1);
			 rs=psmt.executeQuery();
			 if(rs.next()) {
				 writeid=rs.getString("writeid");
			 }
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		return writeid;
	}
	//hit 수 증가
	public void UpHit(int id_) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		String sql="update tbl_board set hit=hit+1 where id=?";
		
		try {
			 con =ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			 psmt.setInt(1, id_);
			 psmt.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
	}

	public List<Picture> getPictureList() {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		
		
		List<Picture> list = new ArrayList<>();
		String sql = "select * from tbl_picture"; // 조회 sql
		try {
			con=ConnectionProvider.getConnection();
			 psmt = con.prepareStatement(sql);
			 rs = psmt.executeQuery();
			while (rs.next()) {
				int id1 = rs.getInt("id");
				String ptitle=rs.getString("ptitle");
				String writeid = rs.getString("writeid");
				String path = rs.getString("path");
				Date regdate = rs.getTimestamp("regdate");
			
				//조회 된 값을 입력하여 초기화하는 생성자 생성
				Picture pt = new Picture(id1,ptitle, writeid, path, regdate);
				list.add(pt);
				
				//list 에 조회된 값이 저장된 notice 객체 추가
							
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		
		return list;
	}
}
