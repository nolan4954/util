package com.best.web.htyt.util.convert;


import java.util.ArrayList;
import java.util.List;


/**
 * 数据类型装换工具类
 * @author BL00064
 *
 */
public class ConvertUtil {
    /**
     * 把list中的对象转化成List<Long>类型
     * list中的对象可以为Number类型或者String类型 
     * @return
     *          
     */
    @SuppressWarnings({ "rawtypes" })
	static public List<Long> toLongs(List list){
    	List<Long> result = new ArrayList<Long>(list.size());
    	for(Object i:list){
    		if( i instanceof Number ){
    			result.add(((Number)i).longValue());
    		}else{
    			result.add(new Long(i.toString()));
    		}
    	}
        return result;
    }

}
