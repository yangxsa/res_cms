package com.yaa.cms.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    //当前页
    private int currPage = 1;
    //每页显示数
    public static int pageSize = 10;
    //总记录数
    private int totalRecord = 0;
    //是否可点击首页
    private boolean hasTopIndex = false;
    //是否可点击尾页
    private boolean hasEndIndex = false;
    //显示页数
    private List<Integer> navigatePageNums;
    //尾页数
    private int totalPage = 1;

    private final int numsLimit = 5;

    public PageUtil(){
    }

    public PageUtil(Integer currPage,Integer totalRecord) {
//        （总记录数 + 每页数据大小  - 1） / 每页数据大小
        int totalPage = (totalRecord + pageSize - 1) / this.pageSize;
        if (currPage >= 1) {
            this.currPage = currPage;
        }
        if (totalRecord >= 0) {
            this.totalRecord = totalRecord;
        }
        if (totalPage > 0) {
            this.totalPage = totalPage;
        }
        if (currPage < totalPage) {
            this.hasEndIndex = true;
        }
        if (currPage > 1) {
            this.hasEndIndex = true;
        }
        navigatePageNums = new ArrayList<>();
        //总页数小于numsLimit
        if (totalPage <= numsLimit) {
            for (int i = 1; i <= totalPage; i++) {
                navigatePageNums.add(i);
            }
        }
        //总页数大于numsLimit
        if (totalPage > numsLimit) {
            if ((currPage - 2) > 0 && (currPage + 2) <= totalPage) {
                navigatePageNums.add(currPage - 2);
                navigatePageNums.add(currPage - 1);
                navigatePageNums.add(currPage);
                navigatePageNums.add(currPage + 1);
                navigatePageNums.add(currPage + 2);
            } else if ((currPage - 2) <= 0) {
                for (int i = 1; i <= numsLimit; i++) {
                    navigatePageNums.add(i);
                }
            } else if ((currPage + 2) >= totalPage) {
                for (int i = totalPage - numsLimit; i <= totalPage; i++) {
                    navigatePageNums.add(i);
                }
            }
        }
    }

    public static int getOffset(int page){
        return (page - 1) * pageSize;
    }

    public static int getLimit(){
        return pageSize;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public boolean isHasTopIndex() {
        return hasTopIndex;
    }

    public void setHasTopIndex(boolean hasTopIndex) {
        this.hasTopIndex = hasTopIndex;
    }

    public boolean isHasEndIndex() {
        return hasEndIndex;
    }

    public void setHasEndIndex(boolean hasEndIndex) {
        this.hasEndIndex = hasEndIndex;
    }

    public List<Integer> getNavigatePageNums() {
        return navigatePageNums;
    }

    public void setNavigatePageNums(List<Integer> navigatePageNums) {
        this.navigatePageNums = navigatePageNums;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
