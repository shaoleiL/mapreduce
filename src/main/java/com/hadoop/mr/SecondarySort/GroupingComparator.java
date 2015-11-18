package com.hadoop.mr.SecondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 继承WritableComparator
 * Created by shaolei on 2015/11/18 14:29.
 */
public class GroupingComparator extends WritableComparator {
    protected GroupingComparator() {
        super(IntPair.class, true);
    }

    @Override
    //Compare two WritableComparables.
    public int compare(WritableComparable w1, WritableComparable w2) {
        IntPair ip1 = (IntPair) w1;
        IntPair ip2 = (IntPair) w2;
        int l = ip1.getFirst();
        int r = ip2.getFirst();
        return l == r ? 0 : (l < r ? -1 : 1);
    }
}
