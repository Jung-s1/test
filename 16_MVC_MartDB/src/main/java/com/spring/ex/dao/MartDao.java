package com.spring.ex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.spring.ex.dto.MartBean;

public class MartDao {

	private static MartDao instance;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	public static MartDao getInstance() {
		if(instance == null) {
			instance = new MartDao();
		}

		return instance;
	}

	private MartDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sqlid", "sqlpw");

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertMart(MartBean mb) {
		String sql = "insert into mart values(mart_seq.nextval, ?, ?, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, mb.getId());
			ps.setString(2, mb.getPw());
			ps.setString(3, mb.getProduct());
			ps.setString(4, mb.getTime());
			ps.setString(5, mb.getApprove());
			ps.setString(6, mb.getAgree());

			ps.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps != null)
					ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//insertMart

	public ArrayList<MartBean> selectAll() {
		String sql = "select * from mart order by num";
		ArrayList<MartBean> lists = new ArrayList<MartBean>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MartBean mb = new MartBean(); 
				mb.setNum(rs.getInt("num"));
				mb.setId(rs.getString("id"));
				mb.setPw(rs.getString("pw"));
				mb.setProduct(rs.getString("product"));
				mb.setTime(rs.getString("time"));
				mb.setApprove(rs.getString("approve"));
				mb.setAgree(rs.getString("agree"));

				lists.add(mb);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps!=null) {
					ps.close();
				}
				if (rs!=null) {
					rs.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}	
		}

		return lists;
	}//select

	public void delete(int num) {

		String sql = "delete from mart where num=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null)
					ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//delete

	public int deleteAll(String[] rowcheckArr) { // 3
		int cnt = -1;
		String sql = "delete mart where num=? ";
		
		try {
			
			for(int i=1;i<rowcheckArr.length;i++) { // 1~<3
				sql += " or num=?";
			}
			
			ps = conn.prepareStatement(sql);
			
			for(int i=0;i<rowcheckArr.length;i++) {
				int rowcheck = Integer.parseInt(rowcheckArr[i]);
				ps.setInt(i+1, rowcheck);
			}
			
			cnt += ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ps!=null) {
					ps.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
		return cnt;
	}//deleteAll

	public MartBean getMartByNum(int num) {
		String sql = "select * from mart where num = ?";
		MartBean mb = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				mb = new MartBean();
				mb.setNum(rs.getInt("num"));
				mb.setId(rs.getString("id"));
				mb.setPw(rs.getString("pw"));
				mb.setProduct(rs.getString("product"));
				mb.setTime(rs.getString("time"));
				mb.setApprove(rs.getString("approve"));
				mb.setAgree(rs.getString("agree"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mb;
	}//getMartByNum

	public void update(MartBean mb) {
		try {
			String sql = "update mart set id=?, pw=?, product=?, time=?, approve=?, agree =? where num=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mb.getId());
			ps.setString(2, mb.getPw());
			ps.setString(3, mb.getProduct());
			ps.setString(4, mb.getTime());
			ps.setString(5, mb.getApprove());
			ps.setString(6, mb.getAgree());
			ps.setInt(7, mb.getNum());
			
			ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println("update sql 오류");
		}finally {
			try {
				if(ps != null)
					ps.close();
			}catch (SQLException e) {
				System.out.println("update 끊기 오류");
			}
		}
		
	}//update
}






