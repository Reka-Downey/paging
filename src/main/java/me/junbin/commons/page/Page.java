package me.junbin.commons.page;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2017/1/26 10:04
 * @description :
 */
public class Page<T> implements Serializable {

    /**
     * 当前查询实际拥有的数据量
     */
    private final long totalElements;

    /**
     * 当前查询总共拥有的页数
     */
    private final int totalPages;

    /**
     * 当前请求的页码偏移量
     */
    private final int reqPageOffset;

    /**
     * 当前请求的页面数据量，即要求每页显示多少条数据
     */
    private final int reqPageSize;

    /**
     * 当前页的页码偏移量，0 代表 第一页，1 代表第二页，请求正常的情况下该值必然与 {@link #reqPageOffset} 一致
     */
    private final int curPageOffset;

    /**
     * 当前页的页面数据量，在尾页的时候，该值可能与 {@link #reqPageSize} 不等
     */
    private final int curPageSize;

    /**
     * 当前请求的页码导航数量，即可以导航到前/后多少条
     */
    private final int reqPagingNavigationNum;

    /**
     * 当前页是否有上一页
     */
    private final boolean hasPreviousPage;

    /**
     * 当前页是否有下一页
     */
    private final boolean hasNextPage;

    /**
     * 当前页是否是首页
     */
    private final boolean isFirstPage;

    /**
     * 当前页是否是尾页
     */
    private final boolean isLastPage;

    /**
     * 在导航栏中，符合 {@link #reqPagingNavigationNum} 约束下，当前页的前面能够存在多少个导航页码。
     * <pre>
     * 　　例如：当前{@link #reqPagingNavigationNum} 为 10，{@link #totalPages} 为 20，那么：
     * 假设当前为第 5 页，导航栏中将只有 1、2、3、4 共 4 条前置分页导航页码；
     * 而若当前为第 15 页，导航栏中将只有 5、6、7、8、9、10、11、12、13、14 共 10 条前置分页导航页码；
     * </pre>
     */
    private final int[] previousNavigation;

    /**
     * 在导航栏中，符合 {@link #reqPagingNavigationNum} 约束下，当前页的后面能够存在多少个导航页码。
     * <pre>
     * 　　例如：当前{@link #reqPagingNavigationNum} 为 10，{@link #totalPages} 为 20，那么：
     * 假设当前为第 5 页，导航栏中将只有 6、7、8、9、10、11、12、13、14、15 共 10 条后置分页导航页码；
     * 而若当前为第 15 页，导航栏中将只有 16、17、18、19、20 共 5 条后置分页导航页码；
     * </pre>
     */
    private final int[] nextNavigation;

    /**
     * 当前页的页面数据
     */
    private final List<T> content;

    public Page(long totalElements, int totalPages,
                int reqPageOffset, int reqPageSize, int reqPagingNavigationNum,
                int curPageOffset, int curPageSize,
                boolean hasPreviousPage, boolean hasNextPage,
                boolean isFirstPage, boolean isLastPage,
                int[] previousNavigation, int[] nextNavigation,
                List<T> content) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.reqPageOffset = reqPageOffset;
        this.reqPageSize = reqPageSize;
        this.reqPagingNavigationNum = reqPagingNavigationNum;
        this.curPageOffset = curPageOffset;
        this.curPageSize = curPageSize;
        this.previousNavigation = previousNavigation;
        this.nextNavigation = nextNavigation;
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", reqPageOffset=" + reqPageOffset +
                ", reqPagingNavigationNum=" + reqPagingNavigationNum +
                ", reqPageSize=" + reqPageSize +
                ", curPageOffset=" + curPageOffset +
                ", curPageSize=" + curPageSize +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", previousNavigation=" + Arrays.toString(previousNavigation) +
                ", nextNavigation=" + Arrays.toString(nextNavigation) +
                ", content=" + content +
                '}';
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getReqPageOffset() {
        return reqPageOffset;
    }

    public int getReqPagingNavigationNum() {
        return reqPagingNavigationNum;
    }

    public int getReqPageSize() {
        return reqPageSize;
    }

    public int getCurPageOffset() {
        return curPageOffset;
    }

    public int getCurPageSize() {
        return curPageSize;
    }

    public int[] getPreviousNavigation() {
        return previousNavigation;
    }

    public int[] getNextNavigation() {
        return nextNavigation;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public boolean hasPreviousPage() {
        return isHasPreviousPage();
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public boolean hasNextPage() {
        return isHasNextPage();
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public List<T> getContent() {
        return content;
    }

    public static class Builder<T> {

        private long totalElements;
        private int totalPages;
        private int reqPageOffset;
        private int reqPageSize;
        private int reqPagingNavigationNum = PageRequest.DEFAULT_NAVIGATION_NUM;
        private int curPageOffset;
        private int curPageSize;
        private int[] previousNavigation;
        private int[] nextNavigation;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private List<T> content;

        /**
         * 仅仅提供了页面数据，此时构造而成的 {@link Page} 将直接根据该页面数据生成。
         * 即有分页请求：
         * 　　页码偏移量：0；
         * 　　页面数据量：{@link #content} 列表的长度；
         * 　　总数据量：{@link #content} 列表的长度。
         *
         * @param content 页面数据
         */
        public Builder(final List<T> content) {
            this(content.size(), content);
        }

        /**
         * 提供了总数据量以及页面数据，此时构成的 {@link Page} 有如下规则：
         * 　　页码偏移量：0；
         * 　　页面数据量：{@link #content} 列表的长度；
         * 　　总数据量：若 {@link #content} 列表的长度大于 {@link #totalElements}，
         * 则使用列表长度作为总数据量，否则使用 {@link #totalElements} 作为总数据量。
         *
         * @param totalElements 总数据量
         * @param content       页面数据
         */
        public Builder(final long totalElements, final List<T> content) {
            this(new PageRequest(0, content.size()), totalElements, content);
        }

        /**
         * @param pageRequest   分页请求参数
         * @param totalElements 总数据量
         * @param content       页面数据
         */
        public Builder(final PageRequest pageRequest, final long totalElements, final List<T> content) {
            Objects.requireNonNull(pageRequest);
            Objects.requireNonNull(content);
            int contentSize = content.size();
            this.reqPagingNavigationNum = pageRequest.getPagingNavigationNum();
            this.reqPageOffset = pageRequest.getPageOffset();
            this.reqPageSize = pageRequest.getPageSize();
            this.curPageOffset = reqPageOffset;
            this.curPageSize = contentSize;
            int previousPagesTotalSize = reqPageOffset * reqPageSize; // 当前页之前的所有记录总数
            this.totalElements = contentSize != 0 && previousPagesTotalSize + reqPageSize
                    > totalElements ? previousPagesTotalSize + contentSize : totalElements;
            this.content = content;
        }

        public Builder(final int pageOffset, final int pageSize, final long totalElements, final List<T> content) {
            this(pageOffset, pageSize, PageRequest.DEFAULT_NAVIGATION_NUM, totalElements, content);
        }

        public Builder(final int pageOffset, final int pageSize, final int pagingNavigationNum,
                       final long totalElements, final List<T> content) {
            Objects.requireNonNull(content);
            int contentSize = content.size();
            this.reqPagingNavigationNum = pagingNavigationNum;
            this.reqPageOffset = pageOffset;
            this.reqPageSize = pageSize;
            this.curPageOffset = reqPageOffset;
            this.curPageSize = contentSize;
            int previousPagesTotalSize = reqPageOffset * reqPageSize;
            this.totalElements = contentSize != 0 && previousPagesTotalSize + reqPageSize
                    > totalElements ? previousPagesTotalSize + contentSize : totalElements;
            this.content = content;
        }

        public Page<T> build() {
            return create();
        }

        public Page<T> create() {
            this.totalPages = reqPageSize == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) reqPageSize);
            this.hasPreviousPage = curPageOffset > 0;
            this.isFirstPage = !hasPreviousPage;
            this.hasNextPage = curPageOffset + 1 < +totalPages;
            this.isLastPage = !hasNextPage;

            if (hasPreviousPage) {
                int curPageNum = curPageOffset + 1;
                if (curPageNum > reqPagingNavigationNum) {
                    previousNavigation = new int[reqPagingNavigationNum];
                    for (int i = reqPagingNavigationNum - 1; i >= 0; i--) {
                        curPageNum--;
                        previousNavigation[i] = curPageNum;
                    }
                } else {
                    previousNavigation = new int[curPageNum - 1];
                    for (int i = previousNavigation.length - 1; i >= 0; i--) {
                        curPageNum--;
                        previousNavigation[i] = curPageNum;
                    }
                }
            } else {
                previousNavigation = new int[0];
            }

            if (hasNextPage) {
                int hasNextPageNum = totalPages - curPageOffset - 1;
                int curPageNum = curPageOffset + 1;
                if (hasNextPageNum > reqPagingNavigationNum) {
                    nextNavigation = new int[reqPagingNavigationNum];
                    for (int i = 0; i < reqPagingNavigationNum; i++) {
                        curPageNum++;
                        nextNavigation[i] = curPageNum;
                    }
                } else {
                    nextNavigation = new int[hasNextPageNum];
                    for (int i = 0; i < hasNextPageNum; i++) {
                        curPageNum++;
                        nextNavigation[i] = curPageNum;
                    }
                }
            } else {
                nextNavigation = new int[0];
            }

            return new Page<>(totalElements, totalPages,
                    reqPageOffset, reqPageSize, reqPagingNavigationNum,
                    curPageOffset, curPageSize,
                    hasPreviousPage, hasNextPage,
                    isFirstPage, isLastPage,
                    previousNavigation, nextNavigation,
                    content);
        }
    }


}
