package com.best.web.htyt.util.page;

import java.util.List;


public class PageList<T> {
    /**
     * 每页的列表
     */
    private List<T> list;
    
    //private Map<String,Object> resultMap = new HashMap<String, Object>();

    /**
     * 总记录数
     */
    private int fullListSize = 0;

    /**
     * 当前页码
     */
    private int pageNumber = 1;

    /**
     * 每页记录数 page size
     */
    private int objectsPerPage = PageUtil.defaultPageSize;

    private String sortCriterion;


    private String searchId;
    
    public PageList(int pageNumber,int objectsPerPage) {
	        this.pageNumber = pageNumber;
	        this.objectsPerPage = objectsPerPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getObjectsPerPage() {
        return objectsPerPage;
    }

    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    public int getFullListSize() {
        return fullListSize;
    }

    public void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    public String getSortCriterion() {
        return sortCriterion;
    }

    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }


    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public PageList(BbbPageSO so) {
    	if(so != null){
	        this.pageNumber = so.getPageNumber();
	        this.objectsPerPage = so.getObjectsPerPage();
    	}
    }

    @SuppressWarnings("unchecked")
    public PageList(PageList pageList) {
        this.fullListSize = pageList.getFullListSize();

        this.pageNumber = pageList.getPageNumber();

        this.objectsPerPage = pageList.getObjectsPerPage();

        this.sortCriterion = pageList.getSortCriterion();


        this.searchId = pageList.getSearchId();

    }

    public PageList() {

    }

    /**
     * 获取页数
     * 
     * @return
     */
    public int getPages() {
        return (int) Math.ceil((double) fullListSize / objectsPerPage);
    }

    /**
     * 取得当前页显示的项的起始序号。
     * 
     * @return 起始序号
     */
    public int getBeginIndex() {
        if (pageNumber > 0) {
            return (objectsPerPage * (pageNumber - 1)) + 1;
        } else {
            return 0;
        }
    }

    /**
     * 取得当前页显示的末项序号 (1-based)。
     * 
     * @return 末项序号
     */
    public int getEndIndex() {
        if (pageNumber > 0) {
            return Math.min(pageNumber * objectsPerPage, fullListSize);
        } else {
            return 0;
        }
    }

    /**
     * 计算页数，但不改变当前页。
     * 
     * @param page
     *            页码
     * 
     * @return 返回正确的页码
     */
    protected int calcPage(int page) {
        int pages = getPages();
        if (pages > 0) {
            return (page < 1) ? 1 : ((page > pages) ? pages : page);
        }
        return 0;
    }
    /**
     * 取得首页页码。
     *
     * @return 首页页码
     */
    public int getFirstPage() {
        return calcPage(1);
    }

    /**
     * 取得末页页码。
     *
     * @return 末页页码
     */
    public int getLastPage() {
        return calcPage(getPages());
    }

    /**
     * 取得前一页页码。
     *
     * @return 前一页页码
     */
    public int getPreviousPage() {
        return calcPage(pageNumber - 1);
    }

    /**
     * 取得后一页页码。
     *
     * @return 后一页页码
     */
    public int getNextPage() {
        return calcPage(pageNumber + 1);
    }

}
