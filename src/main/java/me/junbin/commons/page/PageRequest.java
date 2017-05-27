package me.junbin.commons.page;


/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2017/1/26 10:18
 * @description :
 */
public class PageRequest {

    public static final int DEFAULT_NAVIGATION_NUM = 10;

    /**
     * 当前请求的页码偏移量
     */
    private final int pageOffset;

    /**
     * 当前请求的页面数据量，即要求每页显示多少条数据
     */
    private final int pageSize;

    /**
     * 当前请求的页码导航数量，即可以导航到前/后多少条，
     * 之所以添加这个变量是因为避免当分页数量超过 10 万时，下拉框示的导航栏会导致客户端页面卡顿。
     */
    private final int pagingNavigationNum;

    public PageRequest(final int pageOffset, final int pageSize) {
        this(pageOffset, pageSize, DEFAULT_NAVIGATION_NUM);
    }

    public PageRequest(final int pageOffset, final int pageSize, final int pagingNavigationNum) {
        this.pageOffset = pageOffset;
        this.pageSize = pageSize;
        this.pagingNavigationNum = pagingNavigationNum;
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageOffset=" + pageOffset +
                ", pageSize=" + pageSize +
                ", pagingNavigationNum=" + pagingNavigationNum +
                '}';
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPagingNavigationNum() {
        return pagingNavigationNum;
    }

/*
    // JDK 7+
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageRequest that = (PageRequest) o;
        return pageOffset == that.pageOffset &&
                pageSize == that.pageSize &&
                pagingNavigationNum == that.pagingNavigationNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageOffset, pageSize, pagingNavigationNum);
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageRequest that = (PageRequest) o;

        return pageOffset == that.pageOffset
                && pageSize == that.pageSize
                && pagingNavigationNum == that.pagingNavigationNum;
    }

    @Override
    public int hashCode() {
        int result = pageOffset;
        result = 31 * result + pageSize;
        result = 31 * result + pagingNavigationNum;
        return result;
    }

}
