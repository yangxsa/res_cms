package com.yaa.cms.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    //当前页
    private int currPage = 1;
    //每页显示数
    private static int pageSize = 20;
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

    public PageUtil(Integer currPage,Integer totalRecord){
//        （总记录数 + 每页数据大小  - 1） / 每页数据大小
        int totalPage = (totalRecord + pageSize - 1) / this.pageSize;
        if(currPage >= 1){
            this.currPage = currPage;
        }
        if(totalRecord >= 0) {
            this.totalRecord = totalRecord;
        }
        if(totalPage > 0){
            this.totalPage = totalPage;
        }
        if(currPage < totalPage){
            this.hasEndIndex = true;
        }
        if(currPage > 1){
            this.hasEndIndex = true;
        }
        navigatePageNums = new ArrayList<>();
        if(numsLimit >= currPage){
            for(int i = 1;i<=currPage;i++){
                navigatePageNums.add(i);
            }
        }
        if(numsLimit < currPage){
            navigatePageNums.add(currPage + 1);
            navigatePageNums.add(currPage + 2);
            navigatePageNums.add(currPage);
            navigatePageNums.add(currPage - 2);
            navigatePageNums.add(currPage - 1);
        }

    }

    public static int getStartIndex(int page){
        return (page - 1) * pageSize;
    }

    public static int getEndIndex(int page){
        return page * pageSize;
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
