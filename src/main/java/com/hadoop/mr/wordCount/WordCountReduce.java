package com.hadoop.mr.wordCount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * WordCountReduce
 * Created by shaolei on 2015/11/14.
 */
public class WordCountReduce extends Reducer<Text, LongWritable, Text, LongWritable>{

    Text k3 = new Text();
    LongWritable v3 = new LongWritable();
    @Override
    protected void reduce(Text key, Iterable<LongWritable> v2s, Context context) throws IOException, InterruptedException {
        long sum = 0L;
        for(LongWritable v2 : v2s){
            sum += v2.get();
        }
        k3.set(key);
        v3.set(sum);
        context.write(k3, v3);
    }
}
