package etf.nwt.listmicroservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

public class ListItem {
	
	private Long listItemID;
	private String listItemStatus;
	private String dateCreated;
	
	// not used anywhere
		protected ListItem() {}

		public ListItem(String listItemStatus) {
			this.listItemStatus = listItemStatus;
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			this.dateCreated = dateFormat.format(date);
		}
	
	@Override
	public String toString() {
	    return String.format(
	        "List item[id=%d, status='%s', dateCreated='%s']",
	        listItemID, listItemStatus, dateCreated);
	}
}
