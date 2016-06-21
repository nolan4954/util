package com.best.web.htyt.util;

import java.util.ArrayList;
import java.util.List;

import com.best.web.htyt.common.entity.base.BaseEntity;

public class EntityUtil {
	public static List<Long> extractIds(List<? extends BaseEntity> source){
		List<Long> res=new ArrayList<>();
		for(int i=0;i<source.size();i++){
			res.add(source.get(i).getId());
		}
		return res;
	}
}
