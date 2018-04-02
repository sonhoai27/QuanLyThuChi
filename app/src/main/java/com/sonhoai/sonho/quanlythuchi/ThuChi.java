package com.sonhoai.sonho.quanlythuchi;

import java.io.Serializable;

public class ThuChi implements Serializable {
    private int id;
    private String content;
    private int amount;
    private int type;

    public ThuChi(int id, String content, int amount, int type) {
        this.id = id;
        this.content = content;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
