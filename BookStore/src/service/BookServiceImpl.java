package service;

import impl.BookDao;
import impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;

import java.util.List;

public class BookServiceImpl implements BookService{
    BookDao bookDao=new BookDaoImpl();

    /**
     * 添加书籍
     * @param book
     * @return 返回-1操作失败，反之亦然
     */
    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }

    /**
     * 根据id删除书籍
     * @param id
     * @return
     */
    @Override
    public int deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    /**
     * 更改书籍
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        return bookDao.updateBook(book);
    }

    /**
     *根据ID查询数据
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    /**
     * 查询多本书籍
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    /**
     * 进行分页操作
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        /*设置数据总数*/
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        /*设置总页码数*/
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount/pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);


        /*设置page的页数和页面显示数*/
        page.setPageNo(pageNo);

        /*设置当前页的数据*/
        int begin=(page.getPageNo()-1)*pageSize; //当前数据开始索引=（页码数-1）*页面显示数
        List<Book> items=bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        /*设置数据总数*/
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);
        /*设置总页码数*/
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount/pageSize>0){
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        /*设置page的页数和页面显示数*/
        page.setPageNo(pageNo);

        /*设置当前页的数据*/
        int begin=(page.getPageNo()-1)*pageSize; //当前数据开始索引=（页码数-1）*页面显示数
        List<Book> items=bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
