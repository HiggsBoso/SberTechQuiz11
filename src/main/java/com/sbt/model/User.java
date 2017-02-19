package com.sbt.model;
import java.util.List;
import javax.persistence.*;

/**
 * Created by Someone on 18.02.2017.
 */
@Entity
@Table(name = "user_table")
public class User extends BaseEntity{
    @Column
    private String name;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Item> itemList;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Bid> bid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    /*public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }*/
}
