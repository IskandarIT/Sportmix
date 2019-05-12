package com.example.demo.models;



import javax.persistence.*;
import java.util.Date;


@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int Qty;
    private  int sum;
    private Date date = new Date();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "productId")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders(int qty) {
        Qty = qty;
    }
    public Orders() {
    }

    public Date getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinTable(name = "OrdersSklad",
//            joinColumns = @JoinColumn(name = "Ordersid"),
//            inverseJoinColumns = @JoinColumn(name = "Skladid")
//    )
//    private List<Sklad> sklads = new ArrayList<>();


