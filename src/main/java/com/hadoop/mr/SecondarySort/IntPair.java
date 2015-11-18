package com.hadoop.mr.SecondarySort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自己定义的key类应该实现WritableComparable接口
 * Created by shaolei on 2015/11/18 14:24.
 */
public class IntPair implements WritableComparable<IntPair> {
    int first;
    int second;

    /**
     * Set the left and right values.
     */
    public void set(int left, int right) {
        first = left;
        second = right;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    //反序列化，从流中的二进制转换成IntPair
    public void readFields(DataInput in) throws IOException {
        // TODO Auto-generated method stub
        first = in.readInt();
        second = in.readInt();
    }

    //序列化，将IntPair转化成使用流传送的二进制
    public void write(DataOutput out) throws IOException {
        // TODO Auto-generated method stub
        out.writeInt(first);
        out.writeInt(second);
    }

    //key的比较
    public int compareTo(IntPair o) {
        // TODO Auto-generated method stub
        if (first != o.first) {
            return first < o.first ? -1 : 1;
        } else if (second != o.second) {
            return second < o.second ? -1 : 1;
        } else {
            return 0;
        }
    }

    //新定义类应该重写的两个方法
    @Override
    //The hashCode() method is used by the HashPartitioner (the default partitioner in MapReduce)
    public int hashCode() {
        return first * 157 + second;
    }

    @Override
    public boolean equals(Object right) {
        if (right == null)
            return false;
        if (this == right)
            return true;
        if (right instanceof IntPair) {
            IntPair r = (IntPair) right;
            return r.first == first && r.second == second;
        } else {
            return false;
        }
    }
}
