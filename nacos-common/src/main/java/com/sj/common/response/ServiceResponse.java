package com.sj.common.response;

import java.io.Serializable;

/**
 * @author sijia
 * @desc ...
 * @date 2021-01-07 15:37:29
 */
public class ServiceResponse<T> implements Serializable {
    T obj;
    int code = 200;
    String msg = "";
    long count;
    int pageSize;
    int pageNo;
    long pages;
    int insertLastId;

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public int getInsertLastId() {
        return insertLastId;
    }

    public void setInsertLastId(int insertLastId) {
        this.insertLastId = insertLastId;
    }
}
