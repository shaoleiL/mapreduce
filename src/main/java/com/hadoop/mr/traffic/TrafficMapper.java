package com.hadoop.mr.traffic;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by shaolei on 2015/11/12.
 */
public class TrafficMapper extends Mapper<LongWritable, Text, Text, TrafficWritable> {
    Text k2 = new Text();
    TrafficWritable v2 = new TrafficWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] splited = line.split("\t");
        k2.set(splited[1]);
        v2.set(splited[6], splited[7], splited[8], splited[9]);
        context.write(k2, v2);
    }
}
