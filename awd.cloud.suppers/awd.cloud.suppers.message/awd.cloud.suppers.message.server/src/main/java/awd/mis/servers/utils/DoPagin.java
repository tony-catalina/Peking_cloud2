package awd.mis.servers.utils;
import java.util.ArrayList;
import java.util.List;
 
 
public class DoPagin{
	
	public static PaginList<?> getPaginList(List<?> list,String pageIndex,String pageSize){
    	int total = list.size();
		try {
			int fromIndex = Integer.parseInt(pageIndex); 
			int toIndex = fromIndex + Integer.parseInt(pageSize);
			if (toIndex > total) {
				toIndex = total;
			}
			list = list.subList(fromIndex, toIndex);
		} catch (NumberFormatException e) {
			list = new ArrayList<>();
		}
    	PaginList<?> paginList = new PaginList<Object>();
    	paginList.setData(list);
    	paginList.setTotal(total);
    	return paginList;
    }
 
	public static class PaginList<T> {
		 
		private int total;
		private List<?> data;

		public PaginList() {
		}

		public PaginList(int total, List<?> data) {
			this.total = total;
			this.data = data;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public List<?> getData() {
			return data;
		}

		public void setData(List<?> data) {
			this.data = data;
		}

	}
	
}
