package etf.nwt.listmicroservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long listID;

    @NotNull(message = "UserID cannot be null")
    private Long userID;

    @NotNull(message = "List title cannot be null")
    @NotBlank(message = "List title cannot be blank")
    @Size(min = 3, max = 500, message = "List title must be between 3 and 500 characters long")
    private String title;

    @NotNull(message = "Date cannot be null")
    private Date date;

    @JsonManagedReference
    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ListItem> itemsList = new ArrayList<ListItem>();

    public void addListItem(ListItem li) {
        this.itemsList.add(li);
        li.setLista(this);
    }

    public void removeListItem(ListItem li) {
        this.itemsList.remove(li);
        li.setLista(null);
    }

    public Lista() {}

    public Lista(Long userID, String title, Date date) {
        this.userID = userID;
        this.title = title;
        this.date = date;
    }

    public Long getListID() {
        return this.listID;
    }

    public void setListID(Long listID) {
        this.listID = listID;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ListItem> getItemsList() {
        return this.itemsList;
    }

    public void setItemsList(List<ListItem> itemsList) {
        this.itemsList = new ArrayList<ListItem>(itemsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listID);
    }

    @Override
	public String toString() {
	    return String.format(
	        "Lista[id=%d, title='%s', dateCreated='%s', userID='%s']",
	        this.listID, this.title, this.date, this.userID);
	}

}