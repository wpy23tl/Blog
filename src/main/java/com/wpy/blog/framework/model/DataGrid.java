package com.wpy.blog.framework.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 架构层数据表格包装类
 */
public class DataGrid {
    //总记录数
    private int total;
    //数据
    private List<?> rows = new ArrayList<Object>();// 结果集

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public DataGrid() {
    }

    public DataGrid(int total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }
}
