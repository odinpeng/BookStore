package test;

import org.junit.Test;
import pojo.Book;
import pojo.Page;
import service.BookService;
import service.BookServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
    private BookService bookService=new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"Java核心技术(卷2)",new BigDecimal("120"),"Peng",
                2000,1000,"static/img/shop1.jpg"));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(4);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(2,"Java核心技术(卷1)",new BigDecimal("222"),"Hou",1000,2000,
                "static/img/shop1.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        for (Book book:bookService.queryBooks()){
            System.out.println(book);
        }
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(2, 4);
        System.out.println(page);
    }

    @Test
    public void pageByPrice(){
        Page<Book> page = bookService.pageByPrice(0, 4,100,200);
        System.out.println(page);
    }




}