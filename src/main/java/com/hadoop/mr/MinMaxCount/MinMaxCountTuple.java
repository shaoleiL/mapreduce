package com.hadoop.mr.MinMaxCount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

/**
 * 最大值、最小值与计数
 * Created by shaolei on 2015/11/18 22:44.
 */
public class MinMaxCountTuple implements Writable {


    private Date min = new Date();
    private Date max = new Date();
    private long count = 0;


    public void write(DataOutput out) throws IOException {
        out.writeLong(min.getTime());
        out.writeLong(max.getTime());
        out.writeLong(count);
    }

    public void readFields(DataInput in) throws IOException {
        min = new Date(in.readLong());
        max = new Date(in.readLong());
        count = in.readLong();
    }

    public Date getMin() {
        return min;
    }

    public void setMin(Date min) {
        this.min = min;
    }

    public Date getMax() {
        return max;
    }

    public void setMax(Date max) {
        this.max = max;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
