package com.hadoop.mr.Filter.Grep;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 分布式grep
 * 使用Java中内置的正则表达式库， 如果某个文本行符合模式匹配的条件， 将其输出
 * 如果不匹配，怎么什么都不做，忽略
 * Created by v-shaoleili on 2015/11/27.
 */
public class GrepMapper extends Mapper<Object, Text, NullWritable, Text> {

    private String mapRegex = null;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        mapRegex = context.getConfiguration().get("mapregex");
    }

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        if (value.toString().matches(mapRegex)){
            context.write(NullWritable.get(), value);
        }
    }
}
