package com.hadoop.mr.averageValue;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * //Id='8189677' PostId='6881722' Text='have you looked at hadoop?' CreationDate='2011-07030T07:29:33.343' UserId='831878'
 * Created by shaolei on 2015/11/19 23:49.
 */
public class AverageMapper extends Mapper<Object, Text, IntWritable, CountAverageTuple> {

    private IntWritable k2 = new IntWritable();
    private CountAverageTuple v2 = new CountAverageTuple();
    private final SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] splited = line.split("\t");
        String text = splited[2];   //获取评论
        String strDate = splited[3];    //获取评论的时间
        Date creationDate = null;
        if (strDate == null || text == null) {
            // 跳过记录
            return;
        }
        try {
            creationDate = frmt.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        k2.set(creationDate.getHours());
        v2.setCount(1);
        v2.setAverage(text.length());
        context.write(k2,v2);
    }
}
