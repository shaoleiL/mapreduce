package com.hadoop.mr.traffic;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by shaolei on 2015/11/14.
 */
public class TrafficReduce extends Reducer<Text, TrafficWritable, Text, TrafficWritable> {

    Text k3 = new Text();
    TrafficWritable v3 = new TrafficWritable();
    @Override
    protected void reduce(Text key, Iterable<TrafficWritable> v2s, Context context) throws IOException, InterruptedException {
        long t1 = 0L;
        long t2 = 0L;
        long t3 = 0L;
        long t4 = 0L;
        for (TrafficWritable v2 : v2s){
            t1 += v2.t1;
            t2 += v2.t2;
            t3 += v2.t3;
            t4 += v2.t4;
        }
        k3.set(key);
        v3.set(t1, t2, t3, t4);
    }
}
