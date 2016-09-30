package smart.sys.platform.utils;

import java.util.List;

import javax.servlet.ServletRequest;

public class SmartPage {
	private int pageNum=1;
	private int pageSize=10;
	//private String sql;
	//private String hql;
	
	private int pageCount;
	
	private List<? extends Object> dataList;
	private int dataCount;
	
	private int start=0;		//sql����е���ʼλ�� ������ֵ
	private int end=10;			//sql����еĽ�βλ�� С�ڵ��ڴ�ֵ
	
	public SmartPage(int pageNum,int pageSize){
		this.pageNum=pageNum;
		this.pageSize=pageSize;
		init();
	}
	public SmartPage(ServletRequest request,String paramName_pageNum,String paramName_pageSize){
		String pageNumStr = request.getParameter(paramName_pageNum);
		String pageSizeStr = request.getParameter(paramName_pageSize);
		if(pageNumStr!=null && !"".equals(pageNumStr)){
			this.pageNum=Integer.parseInt(pageNumStr);
		}
		if(pageSizeStr!=null && !"".equals(pageSizeStr)){
			this.pageSize=Integer.parseInt(pageSizeStr); 
		}
		init();
	}
	private void init(){
		this.start=(pageNum-1)*pageSize;
		this.end=pageNum*pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum=pageNum;
		init();
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize=pageSize;
		init();
	}
	public int getPageCount() {
		return pageCount;
	}
	
	public List<? extends Object> getDataList() {
		return dataList;
	}
	public void setDataList(List<? extends Object> dataList) {
		this.dataList = dataList;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
		this.pageCount = (dataCount+pageSize-1)/pageSize;
		
		if(this.pageCount == 0){
			this.pageCount = 1;
		}
	}
	/**
	 * ��¼��ʼλ��(��һҳΪ0) sql��rownumӦ���ڴ�ֵ
	 * @return
	 */
	public int getStart() {
		return start;
	}
	/**
	 * ��¼����λ�� sql��rownumӦС�ڵ��ڴ�ֵ
	 * @return
	 */
	public int getEnd() {
		return end;
	}
	
}
