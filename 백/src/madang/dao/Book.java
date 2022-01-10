package madang.dao;

import madang.utils.Sql;
import madang.vo.BookVO;

import java.sql.*;


public class Book extends MadangDAO{
    public Book(Connection con) {
        super(con); // 부모 생성자 호출
    }

    public void select() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(Sql.SELECT_BOOK); // 미리 정의한 쿼리 문자열 사용
        showResult(rs); // 부모 메서드 호출
    }

    public void select(String keyword) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(Sql.SELECT_BOOK_BY_KEYWORD);
        stmt.setString(1, '%' + keyword + '%');
        ResultSet rs = stmt.executeQuery();
        showResult(rs);
    }

    public boolean insert(BookVO vo) throws SQLException {
//		String format = "insert into book (bookid, bookname, publisher, price) "
//				+ "value ({0}, ''{1}'', ''{2}'', {3,number,#})";
//		String query = MessageFormat.format(format, vo.getBookVO());
//		Statement stmt = con.createStatement();
//		if (!stmt.execute(query)) {
//			return false;
//		}
//		return true;

        PreparedStatement stmt = con.prepareStatement(Sql.INSERT_BOOK);
        stmt.setInt(1, vo.getBookid());
        stmt.setString(2, vo.getBookname());
        stmt.setString(3, vo.getPublisher());
        stmt.setInt(4, vo.getPrice());
        if (!stmt.execute()) {
            return false;
        }
        return true;
    }
}
