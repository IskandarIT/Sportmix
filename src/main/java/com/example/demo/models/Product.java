package com.example.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public String name;
    private Float price;
    private int kolvo;
    private boolean nalichie;
    private  Float skidka;
    private Float obshiy;
    private Float nachskid;
    @OneToMany(fetch=FetchType.LAZY, mappedBy ="product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders=new ArrayList<>();


    public List<Orders> getOrders() {
        return orders;
    }


    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public Product() {
    }



    public void addOrders(Orders order){
        orders.add(order);
        order.setProduct(this);
    }
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getKolvo() {
        return kolvo;
    }

    public void setKolvo(int kolvo) {
        this.kolvo = kolvo;
    }
    public void addKolvo(int kolvo) {
        this.kolvo = this.kolvo - kolvo;
    }

    public boolean isNalichie() {
        return nalichie;
    }

    public void setNalichie(boolean nalichie) {
        this.nalichie = nalichie;
    }

    public Float getSkidka() {
        return skidka;
    }

    public void setSkidka(Float skidka) {
        this.skidka = skidka;
    }
    public void skiddkaResh(Float skidka) {
         this.skidka= (skidka/100)*price;
    }
    public Float obshiySena(int kolvo){
        obshiy=kolvo*(price-skidka);
        return obshiy;
    }

    public Float getObshiy() {
        return obshiy;
    }

    public void setObshiy(Float obshiy) {
        this.obshiy = obshiy;
    }
}