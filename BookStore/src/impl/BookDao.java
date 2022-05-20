package impl;
/*关于t_book表的CRUD*/
import pojo.Book;
import pojo.Page;

import java.util.List;

public interface BookDao {
    /**
     * 添加书籍
     * @param book
     * @return
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
     * 查询页面数据总数
     * @return
     */
    public Integer queryForPageTotalCount();

    /**
     * 查询当前页面数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 根据价格区间查询其中所有数据
     * @param min
     * @param max
     * @return
     */
    Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 根据价格区间查询当前页面显示数据
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
