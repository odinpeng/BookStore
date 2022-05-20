package test;

import impl.BookDao;
import impl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;
import pojo.Page;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"Java核心技术",new BigDecimal("111"),"Hou",1000,2000,
                "static/img/shop1.jpg"));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(3);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(2,"Java核心技术(卷1)",new BigDecimal("111"),"Hou",1000,2000,
                "static/img/shop1.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        for (Book book:bookDao.queryBooks()){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book book:bookDao.queryForPageItems(10,Page.PAGE_SIZE)){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(30,80));
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book:bookDao.queryForPageItemsByPrice(0,20,100,200)){ //begin从0开始
            System.out.println(book);
        }
    }



}