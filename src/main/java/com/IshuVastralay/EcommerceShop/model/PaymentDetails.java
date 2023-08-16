package com.IshuVastralay.EcommerceShop.model;

public class PaymentDetails {
    private String paymentMethod;
    private String paymentStatus;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferencedId;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;

    public PaymentDetails() {}

    public PaymentDetails(String paymentMethod, String paymentStatus, String paymentId, String razorpayPaymentLinkId, String razorpayPaymentLinkReferencedId, String razorpayPaymentLinkStatus, String razorpayPaymentId) {
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentId = paymentId;
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
        this.razorpayPaymentLinkReferencedId = razorpayPaymentLinkReferencedId;
        this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorpayPaymentLinkId() {
        return razorpayPaymentLinkId;
    }

    public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
        this.razorpayPaymentLinkId = razorpayPaymentLinkId;
    }

    public String getRazorpayPaymentLinkReferencedId() {
        return razorpayPaymentLinkReferencedId;
    }

    public void setRazorpayPaymentLinkReferencedId(String razorpayPaymentLinkReferencedId) {
        this.razorpayPaymentLinkReferencedId = razorpayPaymentLinkReferencedId;
    }

    public String getRazorpayPaymentLinkStatus() {
        return razorpayPaymentLinkStatus;
    }

    public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
        this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }
}
