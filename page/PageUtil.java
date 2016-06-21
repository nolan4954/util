package com.best.web.htyt.util.page;

public class PageUtil {
   public static int defaultPageSize = 50;
    /**
     * 根据页码和每页显示数量计算第一个对象的index
     * @param so
     * @return
     */
    static public Integer getFirstResult(BbbPageSO so){
        Integer result = (so.getPageNumber() - 1 ) * so.getObjectsPerPage();
        if(result < 0){
            result = 0;
        }
        return result;
    }
    /**
     * 根据开始记录的index和每页显示条数来计算当前页码
     * @param start
     * @param limit
     * @return
     */
    static public Integer getPageNumFromStartAndLimit(int start, int limit){
        Integer result = start/limit + 1;
        if(result < 0){
            result = 1;
        }
        return result;
    }
}
