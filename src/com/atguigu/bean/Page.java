package com.atguigu.bean;

import java.util.List;

/**
 * <p>
 * Title: Page.java
 * </p>
 * <p>
 * Description: 分析模型对象
 * </p>
 * <p>
 * Company: www.atguigu.com
 * </p>
 * 
 * @author 尚硅谷.wzg
 * @date 2017年5月5日 下午5:12:39
 * @version V1.0
 */
public class Page<T> {

	public static final int PAGE_SIZE = 4;

	// 当前页码
	private int pageNo;
	// 总页码
	private int pageTotal;
	// 总的记录数
	private int pageTotalCount;
	// 每页显示的记录数
	private int pageSize = PAGE_SIZE;
	// 当前页需要显示的数据
	private List<T> items;
	// 这个分页的请求地址
	private String url;

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page(int pageNo, int pageTotal, int pageTotalCount, int pageSize, List<T> items) {
		super();
		this.pageNo = pageNo;
		this.pageTotal = pageTotal;
		this.pageTotalCount = pageTotalCount;
		this.pageSize = pageSize;
		this.items = items;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		// 判断数据的有效边境
		if (pageNo < 1) {
			pageNo = 1;
		} else if (pageNo > pageTotal) {
			pageNo = pageTotal;
		}

		this.pageNo = pageNo;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageTotal=" + pageTotal + ", pageTotalCount="
				+ pageTotalCount + ", pageSize=" + pageSize + ", items=" + items + "]";
	}

}
