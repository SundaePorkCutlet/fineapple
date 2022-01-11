package kr.or.fineapple.domain.common;

public class Search {
	
	
	///Field
		public int currentPage;
		public int searchCondition = 0;
		public String searchKeyword = "";
		public int pageSize;
		//==> 리스트화면 currentPage에 해당하는 회원정보를 ROWNUM 사용 SELECT 위해 추가된 Field 
		//==> UserMapper.xml 의 
		//==> <select  id="getUserList"  parameterType="search"	resultMap="userSelectMap">
		//==> 참조
		public int endRowNum;
		public int startRowNum;
		
		public int startNum;
		public int endNum;
		
		///Constructor
		public Search() {
		}
		
		///Method
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int paseSize) {
			this.pageSize = paseSize;
		}
		
		public int getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getSearchCondition() {
			return searchCondition;
		}
		public void setSearchCondition(int searchCondition) {
			System.out.println("setSearchCondition 전");
			this.searchCondition = searchCondition;
			System.out.println("setSearchCondition 후");
		}
		
		public String getSearchKeyword() {
			return searchKeyword;
		}
		public void setSearchKeyword(String searchKeyword) {
			this.searchKeyword = searchKeyword;
		}
		
		//==> Select Query 시 ROWNUM 마지막 값 
		public int getEndRowNum() {
			return getCurrentPage()*getPageSize();
		}
		//==> Select Query 시 ROWNUM 시작 값
		public int getStartRowNum() {
			return (getCurrentPage()-1)*getPageSize()+1;
		}
		
		
		
		

		public int getStartNum() {
			return startNum;
		}

		public void setStartNum(int startNum) {
			this.startNum = startNum;
		}

		public int getEndNum() {
			return endNum;
		}

		public void setEndNum(int endNum) {
			this.endNum = endNum;
		}

		public void setEndRowNum(int endRowNum) {
			this.endRowNum = endRowNum;
		}

		public void setStartRowNum(int startRowNum) {
			this.startRowNum = startRowNum;
		}

		@Override
		public String toString() {
			return "Search [currentPage=" + currentPage + ", searchCondition="
					+ searchCondition + ", searchKeyword=" + searchKeyword
					+ ", pageSize=" + pageSize + ", endRowNum=" + endRowNum
					+ ", startRowNum=" + startRowNum + "]";
		}
	
}
