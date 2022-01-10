package madang.dao;

import java.sql.*;
import madang.utils.Sql;

public class Orders extends MadangDAO {

    public Orders(Connection con) {
        super(con);
    }

    public void selectOrders(int bookId) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(Sql.SELECT_ORDERS);
        pstmt.setInt(1, bookId);
        ResultSet rs = pstmt.executeQuery();
// 조회 결과를 출력
//		while (rs.next()) {
//			System.out.print(rs.getInt("bookid") + "\t");
//			System.out.print(
//					rs.getString("bookname") + "(" + rs.getString("publisher") + ", " + rs.getInt("price") + ")\t");
//			System.out.print(rs.getString("name") + "\t");
//			System.out.print(rs.getDate("orderdate") + "\t");
//			System.out.println(rs.getInt("saleprice") + "\t");
//		}

// ResultSetMetaData를 이용해서 조회 결과를 출력
//		ResultSetMetaData rsmd = rs.getMetaData();
//		int count = rsmd.getColumnCount();
//		System.out.println(count);
//		for (int i = 1; i <= count; i ++) {
//			System.out.print(rsmd.getColumnName(i) + "\t");
//		}
//		System.out.println();
//
//		while(rs.next()) {
//			for (int i = 1; i <= count; i ++) {
//				int columnType = rsmd.getColumnType(i);
//				switch(columnType) {
//				case Types.NUMERIC: System.out.print(rs.getInt(i) + "\t"); break;
//				case Types.VARCHAR: System.out.print(rs.getString(i) + "\t"); break;
//				case Types.DATE: System.out.print(rs.getDate(i) + "\t"); break;
//				default: System.out.print(rs.getString(i) + "\t"); break;
//				}
//			}
//			System.out.println();
//		}

// 부모 클래스에 정의한 결과 출력 메소드를 호출
        showResult(rs);
    }

}
