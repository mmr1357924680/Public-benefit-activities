package com.mengxuegu.springboot.common;

import java.io.Serializable;
import java.util.List;

/**
 * The type Pager.
 *
 * @param <T> the type parameter
 */
public class Pager<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 默认每页记录数为10条
     */
    public static final int DEFAULT_PAGESIZE = 10;

    private String username;//模糊查询条件（用户名）
    private String name;
    private List<T> records; // 分页数据
    private int page = 1; // 当前页
    private int rows = 5; // 每页显示记录数
    private int pageOffset; // 当前页起始记录
    private String sort; // 排序字段
    private String order; // asc/desc
    private int totalPage; // 总页数
    private long totalCount; // 总记录数
    private int startPageIndex; // 开始页
    private int endPageIndex; // 结束页
    private int pageCode = 50; // 页码数量：翻页条工显示多少页的索引
    private int previewPage = 1; // 上一页
    private int nextPage = 1; // 下一页
    private Object sumQty;// 合计数据


    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Gets default pagesize.
     *
     * @return the default pagesize
     */
    public static int getDefaultPagesize() {
        return DEFAULT_PAGESIZE;
    }


    /**
     * Instantiates a new Pager.
     */
    public Pager() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Instantiates a new Pager.
     *
     * @param page 当前页
     * @param rows the rows
     * @rows 每页记录数大小
     */
    public Pager(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    /**
     * Instantiates a new Pager.
     *
     * @param page 当前页
     */
    public Pager(int page) {
        this.page = page;
        this.rows = DEFAULT_PAGESIZE;
    }

    /**
     * Gets page offset.
     *
     * @return the page offset
     */
    public int getPageOffset() {
        pageOffset = (page - 1) * rows;
        return pageOffset;
    }

    /**
     * Sets page offset.
     *
     * @param pageOffset the page offset
     */
    public void setPageOffset(int pageOffset) {
        this.pageOffset = pageOffset;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(int page) {
        if (page > 0) {
            this.page = page;
        }
    }

    /**
     * Gets rows.
     *
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets rows.
     *
     * @param rows the rows
     */
    public void setRows(int rows) {
        if (rows > 0) {
            this.rows = rows;
        }
    }

    /**
     * Gets sort.
     *
     * @return the sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * Sets sort.
     *
     * @param sort the sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * Gets total page.
     *
     * @return the total page
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * Sets total page.
     *
     * @param totalPage the total page
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;

        this.startPageIndex = this.page - (pageCode % 2 == 0 ? pageCode / 2 - 1 : pageCode / 2);
        this.endPageIndex = this.page + pageCode / 2;
        if (this.startPageIndex < 1) {
            this.startPageIndex = 1;
            if (totalPage >= pageCode) {
                this.endPageIndex = pageCode;
            } else {
                this.endPageIndex = totalPage;
            }
        }
        if (this.endPageIndex > totalPage) {
            this.endPageIndex = totalPage;
            if ((this.endPageIndex - pageCode) > 0) {
                this.startPageIndex = this.endPageIndex - pageCode + 1;
            } else {
                this.startPageIndex = 1;
            }
        }
        this.previewPage = this.page - 1;
        this.nextPage = this.page + 1;
        if (this.page <= 1) {
            this.previewPage = 1;
        }
        if (this.page >= this.totalPage) {
            this.nextPage = this.totalPage;
        }
        if (this.totalPage == 0) {
            this.startPageIndex = 0;
        }
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        if (rows != 0) {
            setTotalPage((int) (totalCount % rows == 0 ? totalCount / rows : (totalCount / rows + 1)));
        }
    }

    /**
     * Gets start page index.
     *
     * @return the start page index
     */
    public int getStartPageIndex() {
        return startPageIndex;
    }

    /**
     * Sets start page index.
     *
     * @param startPageIndex the start page index
     */
    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    /**
     * Gets end page index.
     *
     * @return the end page index
     */
    public int getEndPageIndex() {
        if (this.endPageIndex < this.startPageIndex) {
            this.endPageIndex = this.startPageIndex;
        }
        return endPageIndex;
    }

    /**
     * Sets end page index.
     *
     * @param endPageIndex the end page index
     */
    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    /**
     * Gets page code.
     *
     * @return the page code
     */
    public int getPageCode() {
        return pageCode;
    }

    /**
     * Sets page code.
     *
     * @param pageCode the page code
     */
    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    /**
     * Gets preview page.
     *
     * @return the preview page
     */
    public int getPreviewPage() {
        return previewPage;
    }

    /**
     * Sets preview page.
     *
     * @param previewPage the preview page
     */
    public void setPreviewPage(int previewPage) {
        this.previewPage = previewPage;
    }

    /**
     * Gets next page.
     *
     * @return the next page
     */
    public int getNextPage() {
        return nextPage;
    }

    /**
     * Sets next page.
     *
     * @param nextPage the next page
     */
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * Gets records.
     *
     * @param
     * @return the records
     */
    public List<T> getRecords() {
        return records;
    }

    /**
     * Sets records.
     *
     * @param records the records
     */
    public void setRecords(List<T> records) {
        this.records = records;
    }

    /**
     * Gets sum qty.
     *
     * @return the sum qty
     */
    public Object getSumQty() {
        return sumQty;
    }

    /**
     * Sets sum qty.
     *
     * @param sumQty the sum qty
     */
    public void setSumQty(Object sumQty) {
        this.sumQty = sumQty;
    }
}
