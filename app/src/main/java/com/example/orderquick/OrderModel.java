package com.example.orderquick;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderModel implements Parcelable {
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

    protected OrderModel(Parcel in) {
        OrderId = in.readInt();
        CustomerId = in.readInt();
        OrderList = in.readString();
        Pending = in.readInt();
        Seen = in.readInt();
    }

    public static final Creator<OrderModel> CREATOR = new Creator<OrderModel>() {
        @Override
        public OrderModel createFromParcel(Parcel in) {
            return new OrderModel(in);
        }

        @Override
        public OrderModel[] newArray(int size) {
            return new OrderModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(OrderId);
        dest.writeInt(CustomerId);
        dest.writeString(OrderList);
        dest.writeInt(Pending);
        dest.writeInt(Seen);
    }
}
