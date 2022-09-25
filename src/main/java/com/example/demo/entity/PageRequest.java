package com.example.demo.entity;

/**
 * 分页查询的封装类
 */
public class PageRequest {
    private int pageNum;
    private int pageSize;

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
