package madang.main;

import java.sql.*;
import java.util.Scanner;

import madang.dao.Book;
import madang.dao.Orders;
import madang.utils.ConnManager;
import madang.utils.Menu;
import madang.vo.BookVO;


public class DbApp {

    private Book book;
    private Orders orders;

    public DbApp(Connection c) {
        this.book = new Book(c);
        this.orders = new Orders(c);
    }

    private static Scanner scanner = new Scanner(System.in);

    private void doSelect() throws SQLException {
        this.book.select();
    }

    private void doSelectWithKeyword() throws SQLException {
        System.out.print("책 검색 > ");
        String keyword = scanner.next();
        this.book.select(keyword);
    }

    private void doInsert() throws SQLException {
        System.out.print("BOOK ID > ");
        int bookId = scanner.nextInt();
        System.out.print("BOOK NAME > ");
        String bookName = scanner.next();
        System.out.print("PUBLISHER > ");
        String publisher = scanner.next();
        System.out.print("PRICE > ");
        int price = scanner.nextInt();

        BookVO bookVo = new BookVO();
        bookVo.setBookid(bookId);
        bookVo.setBookname(bookName);
        bookVo.setPublisher(publisher);
        bookVo.setPrice(price);

        this.book.insert(bookVo);
    }

    private void getOrderInfoByBookId() throws SQLException {
        System.out.print("BOOK ID > ");
        int bookId = scanner.nextInt();
        this.orders.selectOrders(bookId);
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = ConnManager.getConnection();
            DbApp app = new DbApp(conn);

            boolean run = true;
            while(run) {
                int menu = showMenuGetInput();
                switch (menu) {
                    case Menu.FETCH_ALL: app.doSelect(); break;
                    case Menu.FETCH_WITH_KEYWORD: app.doSelectWithKeyword(); break;
                    case Menu.REGIST: app.doInsert(); break;
                    case Menu.SALE_INFO: app.getOrderInfoByBookId(); break;
                    case Menu.EXIT: run = false; break;
                }
            }
            System.out.println("종료되었습니다.");

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int showMenuGetInput() {
        System.out.println("\n\n\n");
        System.out.println("--------------------------------------------------");
        System.out.println("1.전체조회   2.검색   3.등록   4.판매내역   0.종료");
        System.out.println("--------------------------------------------------");
        System.out.print("선택 > ");
        int menu = scanner.nextInt();
        return menu;
    }
}
