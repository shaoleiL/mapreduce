package com.hadoop.mr.InvertedIndex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 倒排索引mapper
 * Created by shaolei on 2015/11/22 17:18.
 */
public class InvertedIndexMapper extends Mapper<Object, Text, Text, Text> {

    private Text k2 = new Text();
    private Text v2 = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer stk = new StringTokenizer(value.toString());
        //获取<key value>对所属的FileSplit对象
        FileSplit split = (FileSplit) context.getInputSplit();
        while (stk.hasMoreElements()) {
            //key值由（单词:URI）组成
            k2.set(stk.nextToken() + ":" + split.getPath().toString());
            //词频
            v2.set("1");
            context.write(k2, v2);
        }
    }
}
