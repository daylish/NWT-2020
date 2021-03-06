package etf.nwt.listmicroservice.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ListItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long listItemID;

	@NotBlank(message = "Status cannot be blank")
	@NotNull(message = "Status cannot be null")
	@Size(min = 3, max = 500, message = "ListItems status must be between 3 and 500 characters long")
	private String listItemStatus;

	@NotNull(message = "Date cannot be null")
	private Date dateCreated;

	@NotNull(message = "ItemID can not be null")
	private Long itemId;

	@JsonBackReference
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "listaID")
	private Lista lista;
	
	
	@Override
	public String toString() {
	    return String.format(
	        "List item[id=%d, status='%s', dateCreated='%s']",
	        listItemID, listItemStatus, dateCreated);
	}

	public Long getListItemID() { return this.listItemID; }
	public String getListItemStatus() { return this.listItemStatus; }
	public Date getDateCreated() { return this.dateCreated; }
	public Long getItemId() { return this.itemId; }
	public Lista getLista() { return this.lista; }

	public void setListItemID(Long listItemID) { this.listItemID = listItemID; }
	public void setListItemStatus(String listItemStatus) { this.listItemStatus = listItemStatus; }
	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
	public void setItemId(Long itemId) { this.itemId = itemId; }
	public void setLista(Lista lista) { this.lista = lista; }

	public ListItem() {}

	public ListItem(String listItemStatus, Date dateCreated, Long itemId) {
		this.listItemStatus = listItemStatus;
		this.dateCreated = dateCreated;
		this.itemId = itemId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(listItemID);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof ListItem)) {
			return false;
		}
		ListItem listItem = (ListItem) o;
		return Objects.equals(listItemID, listItem.listItemID) && Objects.equals(listItemStatus, listItem.listItemStatus) && Objects.equals(dateCreated, listItem.dateCreated) && Objects.equals(itemId, listItem.itemId);
	}
}