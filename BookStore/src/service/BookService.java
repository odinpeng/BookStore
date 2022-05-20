package service;

import pojo.Book;
import pojo.Page;

import java.util.List;

public interface BookService {
    /**
     * 添加书籍
     * @param book
     * @return 返回-1操作失败，反之亦然
     */
    public int addBook(Book book);

    /**
     * 根据id删除书籍
     * @param id
     * @return
     */
    public int deleteBookById(Integer id);

    /**
     * 更改书籍
     * @param book
     * @return
     */
    public int updateBook(Book book);

    /**
     *根据ID查询数据
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 查询多本书籍
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 进行分页操作
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> page(int pageNo, int pageSize);

    /**
     * 根据价格分页
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
