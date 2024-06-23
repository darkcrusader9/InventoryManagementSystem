package com.ims.order;

public class Invoice {
    double totalAmt;
    double taxAmt;
    public Invoice(double totalAmt){
        this.totalAmt = totalAmt;
        this.taxAmt = 0.2 * totalAmt;
    }

    public double getTotalBill(){
        return totalAmt + taxAmt;
    }
}
