package com.hadoop.mr.traffic;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by shaolei on 2015/11/12.
 */
public class TrafficWritable implements Writable {
    long t1;
    long t2;
    long t3;
    long t4;

    public TrafficWritable() {
    }

    public void set(String t1, String t2, String t3, String t4) {
        this.t1 = Long.parseLong(t1);
        this.t2 = Long.parseLong(t2);
        this.t3 = Long.parseLong(t3);
        this.t4 = Long.parseLong(t4);
    }

    public void set(long t1, long t2, long t3, long t4) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.t4 = t4;
    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(t1);
        out.writeLong(t2);
        out.writeLong(t3);
        out.writeLong(t4);
    }

    public void readFields(DataInput in) throws IOException {
        this.t1 = in.readLong();
        this.t2 = in.readLong();
        this.t3 = in.readLong();
        this.t4 = in.readLong();
    }

    @Override
    public String toString() {
        return this.t1 + "\t" + this.t2 + "\t" + this.t3 + "\t" + this.t4;
    }
}
