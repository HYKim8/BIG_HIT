package com.bighit.on.cmn;

public class Search extends DTO {
/**
1.검색구분:전체,아이디(10),이름(20)
2.검색어:
3.페이지 사이즈
4.페이지 넘
 */
    /**검색구분:전체,아이디(10),이름(20) */
	private String searchDiv;
	
	/**검색어*/
	private String searchWord;
	
	/**페이지 사이즈 */
	private int pageSize;
	
	/**페이지 넘 */
	private int pageNum;
	
	public Search() {}
	
	public Search(String searchDiv, String searchWord) {
		super();
		this.pageSize = 10;
		this.pageNum = 1;
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
	}

	public Search(String searchDiv, String searchWord, int pageSize, int pageNum) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override    
	public String toString() {
		return "Search [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", pageSize=" + pageSize + ", pageNum="
				+ pageNum + ", toString()=" + super.toString() + "]";
	}

	public boolean nonValueDiv() {
		return searchDiv == null || searchDiv.equals("");
	}
	public boolean nonValueWord() {
		return searchWord == null || searchWord.equals("");
	}
}
