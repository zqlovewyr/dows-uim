package org.dows.rbac;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询结果集
 */
@Data
public class PageBean<T> {
    private long pageNo;
    private long pageSize;
    private long totalPages;
    private long totalRecords;
    private List<T> records = new ArrayList();

    public PageBean() {
    }

    public PageBean(long pageNo, long pageSize, long totalPages, long totalRecords, List<T> records) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
        this.records = records;
    }
}