package me.junbin.commons.page;

import java.util.List;
import java.util.Objects;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/5/26 15:31
 * @description : {@link #pageRequest} 分页处理优先级高于 {@link #pageOffset}、{@link #pageSize}
 * 和 {@link #pagingNavigationNum}。
 */
public final class PageBuilder<T> {

    private int pageOffset;
    private int pageSize;
    private int pagingNavigationNum = PageRequest.DEFAULT_NAVIGATION_NUM;
    private PageRequest pageRequest;
    private long totalElements;
    private List<T> content;

    public static <T> PageBuilder<T> custom(List<T> content) {
        return new PageBuilder<>(content);
    }

    public PageBuilder(final List<T> content) {
        this.content = content;
    }

    public PageBuilder<T> pageOffset(final int pageOffset) {
        this.pageOffset = pageOffset;
        return this;
    }

    public PageBuilder<T> pageSize(final int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageBuilder<T> pagingNavigationNum(final int pagingNavigationNum) {
        this.pagingNavigationNum = pagingNavigationNum;
        return this;
    }

    public PageBuilder<T> pageRequest(final PageRequest pageRequest) {
        this.pageRequest = Objects.requireNonNull(pageRequest);
        return this;
    }

    public PageBuilder<T> totalElements(final long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public PageBuilder<T> content(List<T> content) {
        this.content = Objects.requireNonNull(content);
        return this;
    }

    public Page<T> build() {
        Objects.requireNonNull(content);
        int contentSize = content.size();
        if (totalElements == 0) {
            totalElements = contentSize;
        }
        if (Objects.nonNull(pageRequest)) {
            return new Page.Builder<>(pageRequest, totalElements, content).build();
        }
        if (this.pageSize == 0) {
            this.pageSize = contentSize;
        }
        return new Page.Builder<>(pageOffset, pageSize, pagingNavigationNum, totalElements, content).build();
    }

}
