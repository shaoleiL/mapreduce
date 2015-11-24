package com.hadoop.mr.InvertedIndex;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 倒排索引的reduce
 * Created by shaolei on 2015/11/22 20:56.
 */
public class InvertedIndexReduce extends Reducer<Text, Text, Text, Text> {

    private Text result = new Text();
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        //生成文档列表
        String fileList = "";
        for (Text value : values) {
            fileList += value.toString()+";";
        }
        result.set(fileList);
        context.write(key, result);
    }
}
