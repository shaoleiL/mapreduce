package com.hadoop.mr.SecondarySort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 分区函数类。根据first确定Partition。
 * Created by shaolei on 2015/11/18 14:27.
 */
public class FirstPartitioner extends Partitioner<IntPair, IntWritable> {
    @Override
    public int getPartition(IntPair key, IntWritable value, int numPartitions) {
        return Math.abs(key.getFirst() * 127) % numPartitions;
    }
}
