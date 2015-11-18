package com.hadoop.mr.SecondarySort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 自定义mapper
 * Created by shaolei on 2015/11/18 14:30.
 */
public class SecondarySortMap extends Mapper<LongWritable, Text, IntPair, IntWritable> {
    private final IntPair intkey = new IntPair();
    private final IntWritable intvalue = new IntWritable();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        int left = 0;
        int right = 0;
        if (tokenizer.hasMoreTokens()) {
            left = Integer.parseInt(tokenizer.nextToken());
            if (tokenizer.hasMoreTokens())
                right = Integer.parseInt(tokenizer.nextToken());
            intkey.set(left, right);
            intvalue.set(right);
            context.write(intkey, intvalue);
        }
    }
}
