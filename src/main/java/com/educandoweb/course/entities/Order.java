package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.educandoweb.course.entities.enums.OrderSatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
      timezone = "GMT")
  private Instant moment; // classe melhor que o DATE

  private Integer orderSatus;

  @ManyToOne
  @JoinColumn(name = "client_id") // nome da chave estrangeira que será criada na coluna da tabela
                                  // "tb_order"
  private User client;

  @OneToMany(mappedBy = "id.order")
  private Set<OrderItem> items = new HashSet<>();

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // cascade = CascadeType.ALL mapeando
                                                           // para ter o mesmo Id
  private Payment payment;


  public Order() {

  }

  public Order(Long id, Instant moment, OrderSatus orderSatus, User client) {
    super();
    this.id = id;
    this.moment = moment;
    setOrderSatus(orderSatus);
    this.client = client;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getMoment() {
    return moment;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public OrderSatus getOrderSatus() {
    return OrderSatus.valueOf(orderSatus);
  }

  public void setOrderSatus(OrderSatus orderSatus) {
    if (orderSatus != null) {
      this.orderSatus = orderSatus.getCode();
    }
  }

  public User getClient() {
    return client;
  }

  public void setClient(User client) {
    this.client = client;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }


  public Double getTotal() {
    double sum = 0.0;
    for (OrderItem x : items) {
      sum += x.getSubTotal();
    }
    return sum;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Order other = (Order) obj;
    return Objects.equals(id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}