package com.hadoop.mr.MinMaxCount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 最小值，最大值与计数的mapper
 * Created by shaolei on 2015/11/18 22:50.
 */
public class MinMaxCountMapper extends Mapper<Object, Text, Text, MinMaxCountTuple> {

    private Text k2 = new Text();
    private MinMaxCountTuple v2 = new MinMaxCountTuple();
    private final SimpleDateFormat frmt = new SimpleDateFormat("HH:mm:ss");

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        //Id='8189677' PostId='6881722' Text='have you looked at hadoop?' CreationDate='2011-07030T07:29:33.343' UserId='831878'
        String line = value.toString();
        String[] splited = line.split("\t");
        String userId = splited[1];
        String strDate = splited[0];
        Date creationDate = null;
        try {
            creationDate = frmt.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        v2.setMin(creationDate);
        v2.setMax(creationDate);
        v2.setCount(1);
        k2.set(userId);
        context.write(k2,v2);

    }
}
