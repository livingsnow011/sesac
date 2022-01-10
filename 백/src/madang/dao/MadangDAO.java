package madang.dao;

import java.sql.*;

public class MadangDAO {

    Connection con;  // 연결

    MadangDAO(Connection con) {
        this.con = con;
    }

    void showResult(ResultSet rs) throws SQLException { // 조회 결과를 메타데이터를 이용해서 출력
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();

        for (int i = 1; i <= count; i++) {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.println();
        while (rs.next()) {
            for (int i = 1; i <= count; i++) {
                int columnType = rsmd.getColumnType(i);
                switch (columnType) {
                    case Types.NUMERIC:
                        System.out.print(rs.getInt(i) + "\t");
                        break;
                    case Types.VARCHAR:
                        System.out.print(rs.getString(i) + "\t");
                        break;
                    case Types.DATE:
                        System.out.print(rs.getDate(i) + "\t");
                        break;
                    default:
                        System.out.print(rs.getString(i) + "\t");
                        break;
                }
            }
            System.out.println();
        }
    }

}
