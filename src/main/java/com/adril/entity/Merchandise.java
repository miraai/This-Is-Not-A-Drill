/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adril.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Mirai
 */
@Entity
@Table(name = "merchandise")
public class Merchandise implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @JoinColumn(name = "shopId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Shop shopId;
    
    @JoinColumn(name = "gameId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Game gameId;
    
    @JoinColumn(name = "hardwareId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hardware hardwareId;

    public Merchandise() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Shop getShopId() {
        return shopId;
    }

    public void setShopId(Shop shopId) {
        this.shopId = shopId;
    }

    public Game getGameId() {
        return gameId;
    }

    public void setGameId(Game gameId) {
        this.gameId = gameId;
    }

    public Hardware getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Hardware hardwareId) {
        this.hardwareId = hardwareId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Merchandise)) {
            return false;
        }
        Merchandise other = (Merchandise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Merchandise{" + "id=" + id + ", shopId=" + shopId + ", gameId=" + gameId + ", hardwareId=" + hardwareId + '}';
    }
    
}
