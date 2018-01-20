package com.faye.utils;

import java.util.List;

public class PageBean<T> {
		private int currentPage = 1;
		private int pageCount = 6;
		private int totalCount;
		private int totalPage;
		private List<T> pageData;
		
		public int getTotalPage() {
			if (totalCount % pageCount == 0) {
				totalPage = totalCount / pageCount;
			} else {
				totalPage = totalCount / pageCount + 1;
			}
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}

		public int getCurrentPage() {
			return currentPage;
		}

		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		public int getPageCount() {
			return pageCount;
		}

		public void setPageCount(int pageCount) {
			this.pageCount = pageCount;
		}

		public int getTotalCount() {
			return totalCount;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public List<T> getPageData() {
			int startIndex = (currentPage - 1) * pageCount;
			if (currentPage == getTotalPage()) {
				return pageData.subList(startIndex, totalCount);
			}
			return pageData.subList(startIndex, startIndex + pageCount);
		}

		public void setPageData(List<T> pageData) {
			this.pageData = pageData;
		}

}
