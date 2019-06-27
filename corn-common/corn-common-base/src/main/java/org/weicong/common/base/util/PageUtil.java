package org.weicong.common.base.util;

import org.weicong.common.base.page.PageDTO;
import org.weicong.common.base.page.PageVO;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;



/**
 * @description
 * @author weicong
 * @date 2019年6月11日 
 * @version 1.0
 */
public abstract class PageUtil {

	// 默认每页记录数
	private static final Long DEFAULT_PAGE_SIZE = 10L;

	/**
	 * 将 分页请求对象 转换为 MyBatis Plus 分页对象
	 * 
	 * @param pageVO 分页请求对象
	 * @return MyBatis Plus 分页对象
	 */
	public static Page<?> toMybatisPage(PageVO pageVO) {
		Page<?> page = new Page<>();
		if (null == pageVO) {
			return page;
		}
		page.setOptimizeCountSql(true);
		page.setSearchCount(true);
		page.setSize(pageVO.getSize() == 0L ? DEFAULT_PAGE_SIZE : pageVO.getSize());
		page.setCurrent(pageVO.getCurrent());
		if (!StringUtil.isBlank(pageVO.getSortRule())) {
			String[] sort = pageVO.getSortRule().split("\\.");
			String[] col = sort[1].split(",");
			switch (sort[0].toLowerCase()) {
			case "asc":
				page.setAsc(col);
				break;
			case "desc":
				page.setDesc(col);
				break;
			default:
				break;
			}
		}
		return page;
	}
	
	
	/**
	 * 将 MyBatis Plus 分页对象 转换为 分页返回对象
	 * 
	 * @param page MyBatis Plus 分页对象
	 * @return 分页返回对象
	 */
	public static PageDTO toPage(Page<?> page) {
		PageDTO dtoPage =  new PageDTO();
		if (null == page) {
			return dtoPage;
		}
		dtoPage.setCurrent(page.getCurrent());
		dtoPage.setPages(page.getPages());
		dtoPage.setSize(page.getSize());
		dtoPage.setTotal(page.getTotal());
		return dtoPage;
	}

}
