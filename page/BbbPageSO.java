package com.best.web.htyt.util.page;

import java.util.List;

public class BbbPageSO {
	
	private Long userId;
    /**
     * 当前页码
     */
    private int pageNumber = 1;
    /**
     * 每页记录数 page size
     */
    private int objectsPerPage = PageUtil.defaultPageSize;
    
    /**
     * 排序字段
     */
    private List<OrderUnit> orders;
    
    
    /**
     * 是否需要取数据列表回来
     * 用来处理某些查询只需要返回记录总条数的情况
     */
    private boolean withoutListData = false;
    
    public BbbPageSO() {
    }
    public BbbPageSO(int start, int limit) {
        super();
        this.pageNumber = PageUtil.getPageNumFromStartAndLimit(start, limit);
        this.objectsPerPage = limit;
    }
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        if(pageNumber <= 0){
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
    }
    public int getObjectsPerPage() {
        return objectsPerPage;
    }
    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }
    
    public boolean getWithoutListData() {
        return withoutListData;
    }
    public void setWithoutListData(boolean withoutListData) {
        this.withoutListData = withoutListData;
    }
    
    public Integer getOffset(){
    	return (getPageNumber() - 1) * getObjectsPerPage();
    }
    
    public Integer getLimit(){
    	return getObjectsPerPage();
    }
}
