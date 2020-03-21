package etf.nwt.listmicroservice;

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
import javax.validation.constraints.NotNull;

@Entity
public class Lista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listID;

    @NotNull
    private Long userID;

    @NotNull
    private String title;

    @NotNull
    private Date date;

    @OneToMany(mappedBy = "lista", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ListItem> userList;


    public Lista() {
    }

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

    public List<ListItem> getUserList() {
        return this.userList;
    }

    public void setUserList(List<ListItem> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Lista)) {
            return false;
        }
        Lista lista = (Lista) o;
        return Objects.equals(listID, lista.listID) && Objects.equals(userID, lista.userID) && Objects.equals(title, lista.title) && Objects.equals(date, lista.date) && Objects.equals(userList, lista.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listID, userID, title, date, userList);
    }

    @Override
    public String toString() {
        return "{" +
            " listID='" + getListID() + "'" +
            ", userID='" + getUserID() + "'" +
            ", title='" + getTitle() + "'" +
            ", date='" + getDate() + "'" +
            ", userList='" + getUserList() + "'" +
            "}";
    }

}