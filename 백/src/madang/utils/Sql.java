package madang.utils;

public interface Sql {
    public static final String SELECT_ORDERS = "select * from orders o, customer c, book b where o.custid = c.custid and o.bookid = b.bookid and b.bookid = ?";
    public static final String SELECT_BOOK = "select bookid, bookname, publisher, price from book";
    public static final String SELECT_BOOK_BY_KEYWORD = "select bookid, bookname, publisher, price from book where bookname like ? ";
    public static final String INSERT_BOOK = "insert into book (bookid, bookname, publisher, price) value ( ?, ?, ?, ? )";
}
