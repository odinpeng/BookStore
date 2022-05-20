package impl;

import pojo.Book;

import java.util.List;

/*t_book表的CRUD的操作实现*/
public class BookDaoImpl extends BaseDao implements BookDao{

    /**
     * 添加书籍
     * @param book
     * @return 返回-1操作失败，反之亦然
     */
    @Override
    public int addBook(Book book) {
        String sql="insert into t_book (name,price,author,sales,stock,img_path) values (?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),
                book.getImgPath());
    }

    /**
     * 根据id删除书籍
     * @param id
     * @return
     */
    @Override
    public int deleteBookById(Integer id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    /**
     * 更改书籍
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        String sql="update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id=?";
        return update(sql,book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),
                book.getImgPath(),book.getId());
    }

     /**
     *根据ID查询数据
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(Integer id) {
        String sql="select id, name, price, author, sales, stock, img_path as imgPath from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    /**
     * 查询多本书籍
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        String sql="select id, name, price, author, sales, stock, img_path as imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    /**
     * 查询数据总数
     * @return
     */
    @Override
    public Integer queryForPageTotalCount() {
        String sql="select count(*) from t_book";
        Number number = (Number) queryForSingleValue(sql);
        return number.intValue();
    }

    /**
     * 查询当前页面数据
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select id, name, price, author, sales, stock, img_path as imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    /**
     * 根据价格区间查询其中所有数据
     * @param min
     * @param max
     * @return
     */
    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql="select count(*) from t_book where price between ? and ?";
        Number number = (Number) queryForSingleValue(sql,min,max);
        return number.intValue();
    }

    /**
     * 根据价格区间查询当前页面显示数据
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select id, name, price, author, sales, stock, img_path as imgPath " +
                "from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }
}
