package pojo;

import java.util.List;
import java.util.Objects;

/**
 * 用来表示分页模型对象
 * @param <T> 表示对具体的JavaBean模块进行分页操作
 */
public class Page<T> {
    /*页面显示数量*/
    public static final Integer PAGE_SIZE=4;

    /*当前页码*/
    private Integer pageNo;
    /*总页码*/
    private Integer pageTotal;
    /*数据总数*/
    private Integer pageTotalCount;
    /*页面显示数量*/
    private Integer pageSize=PAGE_SIZE;
    /*显示当前页的数据*/
    private List<T> items;
    /*设置Url*/
    private String url;

    public Page(Integer pageNo, Integer pageTotal, Integer pageTotalCount, Integer pageSize, List<T> items, String url) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageTotalCount = pageTotalCount;
        this.pageSize = pageSize;
        this.items = items;
        this.url = url;
    }

    public Page() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page<?> page = (Page<?>) o;
        return Objects.equals(pageNo, page.pageNo) && Objects.equals(pageTotal, page.pageTotal) && Objects.equals(pageTotalCount, page.pageTotalCount) && Objects.equals(pageSize, page.pageSize) && Objects.equals(items, page.items) && Objects.equals(url, page.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageNo, pageTotal, pageTotalCount, pageSize, items, url);
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        /*数据边界扥有效检查*/
        if (pageNo<1){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
