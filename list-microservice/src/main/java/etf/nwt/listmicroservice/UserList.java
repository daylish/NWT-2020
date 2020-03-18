package etf.nwt.listmicroservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
// items are added to this list with their respective statuses (determined by users)
public class UserList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long listID;
	
	@NotBlank
	@NotNull(message = "List user ID cannot be null.")
	private Long listUserID;
	
	@NotBlank
	@NotNull(message = "List title cannot be null.")
	private String listTitle;
	
	@NotNull(message = "Item count cannot be null.")
	private int itemCount;
	
	@ElementCollection
	private List<ListItem> items;
	
	protected UserList() {}
	
	public UserList(String listTitle, int itemCount) {
	    this.listTitle = listTitle;
	    this.itemCount = itemCount;
	    this.items = new ArrayList<ListItem>();
	}
	
	public Long getListID() {
	    return listID;
	}
	
	public String getListTitle() {
	    return listTitle;
	}
	
	public int getItemCount() {
	    return itemCount;
	}
}
