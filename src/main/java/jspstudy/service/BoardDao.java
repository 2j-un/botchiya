package jspstudy.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspstudy.dbcone.Dbconn;
import jspstudy.domain.BoardVo;
import jspstudy.domain.Criteria;
import jspstudy.domain.SearchCriteria;

public class BoardDao {

	Connection conn;
	PreparedStatement pstmt;

	public BoardDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();

	}

	// 퀴리를 실행시키는 메소드
	public int insertBoard(String subject, String content, String writer, String ip, int midx, String filename) {
		int value = 0;

		String sql = "INSERT INTO b_board(bidx, originbidx, depth, level_b, subject, content, writer, ip, midx)"
				+ "VALUES(bidx_b_seq.nextval, bidx_b_seq.nextval, 0, 0, ?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			pstmt.setInt(5, midx);
			
			value = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
		ResultSet rs = null;
		// String sql="select * from b_board where delyn='N' order by originbidx desc,
		// depth asc";

		String str = "";
		if (scri.getSearchType().equals("subject")) {
			str = "and subject like ? ";
		} else {
			str = "and writer like ? ";
		}

		String sql = "SELECT * FROM (" + "SELECT ROWNUM AS rnum, A.* FROM ("
				+ "		SELECT * FROM b_board WHERE delyn='N' " + str + " ORDER BY ORIGINBIDX desc, depth ASC) A"
				+ "	)B" + " WHERE rnum BETWEEN ? AND ?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + scri.getKeyword() + "%");
			pstmt.setInt(2, (scri.getPage() - 1) * 15 + 1);
			pstmt.setInt(3, (scri.getPage() * 15));
			rs = pstmt.executeQuery();

			// 다음 값이 존재하면 true이고 그 행으로 커서가 이동한다.
			while (rs.next()) {
				BoardVo bv = new BoardVo();
				bv.setBidx(rs.getInt("bidx")); // rs에 복사된 bidx를 bv에 옮겨 담는다.
				bv.setSubject(rs.getString("subject"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setLevel_(rs.getInt("level_b"));
				alist.add(bv); // 각각의 bv객체를 alist에 추가한다.

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

		return alist;
	}

	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv = null;
		ResultSet rs = null;

		String sql = "select * from b_board where bidx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bv = new BoardVo();
				bv.setBidx(rs.getInt("bidx"));
				bv.setOriginbidx(rs.getInt("originbidx"));
				bv.setDepth(rs.getInt("depth"));
				bv.setLevel_(rs.getInt("level_b"));

				bv.setSubject(rs.getString("subject"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bv;
	}

	public int Update(String subject, String content, String writer, int midx, int bidx) {

		int value = 0;
		String sql = "Update b_board set subject=?, content=?, writer=?, midx=? where bidx=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setInt(4, midx);
			pstmt.setInt(5, bidx);
			value = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	public int DeleteBoard(int bidx) {

		int value = 0;
		String sql = "Update a_board set delyn='Y' where bidx=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	public int replyBoard(BoardVo bv) {
		int value = 0;

		String sql1 = "update b_board set depth=depth+1 where originbidx=? and depth > ?";

		String sql2 = "insert into b_board(bidx, originbidx, depth, level_, subject, content, writer, ip, midx)"
				+ "VALUES(bidx_b_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			// 트렌잭션
			conn.setAutoCommit(false);
			// 쿼리 실행 중 자동으로 설정 되어 있는 커밋을 바꿈

			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth());
			value = pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth() + 1);
			pstmt.setInt(3, bv.getLevel_() + 1);
			pstmt.setString(4, bv.getSubject() + 1);
			pstmt.setString(5, bv.getContent() + 1);
			pstmt.setString(6, bv.getWriter() + 1);
			pstmt.setString(7, bv.getIp() + 1);
			pstmt.setInt(8, bv.getMidx_() + 1);
			value = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	public int boardTotal(SearchCriteria scri) {
		int cnt = 0;
		ResultSet rs = null;

		String str = "";
		if (scri.getSearchType().equals("subject")) {
			str = "and subject like ? ";
		} else {
			str = "and writer like ? ";
		}
		String sql = "select count(*) as cnt from b_board where delyn='N' " + str + " ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + scri.getKeyword() + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				rs.close();
				pstmt.close();
				// conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return cnt;
	}

}
