package com.example.orderquick;

public class OrderModel {
    public int OrderId;
    public int CustomerId;
    public String OrderList;
    public int Pending;
    public int Seen;

    public OrderModel(int orderId, int customerId, String orderList, int pending, int seen) {
        OrderId = orderId;
        CustomerId = customerId;
        OrderList = orderList;
        Pending = pending;
        Seen = seen;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getOrderList() {
        return OrderList;
    }

    public void setOrderList(String orderList) {
        OrderList = orderList;
    }

    public int getPending() {
        return Pending;
    }

    public void setPending(int pending) {
        Pending = pending;
    }

    public int getSeen() {
        return Seen;
    }

    public void setSeen(int seen) {
        Seen = seen;
    }
}
