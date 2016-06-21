package com.best.web.htyt.util.orika;

import java.util.ArrayList;
import java.util.List;

import com.best.web.htyt.util.page.PageList;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class OrikaBeanMapper {

	private List<String> mappingFiles;

	private final MapperFactory factory = new DefaultMapperFactory.Builder().build();

	public List<String> getMappingFiles() {
		return mappingFiles;
	}

	public void setMappingFiles(List<String> mappingFiles) {
		this.mappingFiles = mappingFiles;
	}

	public <V, P> P convert(V base, final Class<P> target) {

		if (base != null) {
			return factory.getMapperFacade().map(base, target);

		} else {
			return null;
		}
	}

	public <V, P> List<P> convertList(List<V> baseList, Class<P> target) {

		if (baseList == null) {
			return null;
		} else {
			List<P> targetList = new ArrayList<P>();
			for (V vo : baseList) {

				targetList.add(convert(vo, target));
			}
			return targetList;
		}
	}

	@SuppressWarnings("unchecked")
	public <V, P> PageList<P> convertPageList(PageList<V> basePageList, Class<P> target) {
		if (basePageList == null) {
			return null;
		} else {
			List<P> targetList = null;
			List<V> baseList = basePageList.getList();
			if (baseList != null) {
				targetList = new ArrayList<P>();
				for (V vo : baseList) {
					targetList.add(convert(vo, target));
				}
			}
			PageList<P> result = new PageList<P>();
			result = convert(basePageList, result.getClass());
			result.setList(targetList);
			return result;
		}
	}

}
