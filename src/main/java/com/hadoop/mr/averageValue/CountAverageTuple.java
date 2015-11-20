package com.hadoop.mr.averageValue;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by shaolei on 2015/11/19 23:52.
 */
public class CountAverageTuple implements Writable{

    private float count = 0f;
    private float average = 0f;

    public void write(DataOutput out) throws IOException {
        out.writeFloat(count);
        out.writeFloat(average);
    }

    public void readFields(DataInput in) throws IOException {
        this.count = in.readFloat();
        this.average = in.readFloat();
    }

    @Override
    public String toString() {
        return count + "\t" + average;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
