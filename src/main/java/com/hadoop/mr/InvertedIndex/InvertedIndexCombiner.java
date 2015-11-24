package com.hadoop.mr.InvertedIndex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * combiner
 * Created by shaolei on 2015/11/22 18:06.
 */
public class InvertedIndexCombiner extends Reducer<Text,Text,Text,Text> {
    Text info = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (Text value : values) {
            sum += Integer.parseInt(value.toString());
        }
        int splitIndex = key.toString().indexOf(":");
        //重新设置value值由（URI+:词频组成）
        info.set(key.toString().substring(splitIndex+1) +":"+ sum);
        //重新设置key值为单词
        key.set(key.toString().substring(0,splitIndex));
        context.write(key, info);
    }
}
