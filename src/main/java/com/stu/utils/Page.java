package com.stu.utils;

import com.stu.entity.StuData;

import java.util.List;

/**
 * @author kevin zhao
 * @date 2018/7/19
 */
public class Page {
    private int first;
    private int curr;
    private int pre;
    private int next;
    private int last;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getCurr() {
        return curr;
    }

    public void setCurr(int curr) {
        this.curr = curr;
    }

    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "Page{" +
                "first=" + first +
                ", curr=" + curr +
                ", pre=" + pre +
                ", next=" + next +
                ", last=" + last +
                '}';
    }
}
