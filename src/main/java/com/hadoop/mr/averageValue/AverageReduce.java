package com.hadoop.mr.averageValue;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by shaolei on 2015/11/20 0:22.
 */
public class AverageReduce extends Reducer<IntWritable, CountAverageTuple,IntWritable, CountAverageTuple> {

    private IntWritable k3 = new IntWritable();
    private CountAverageTuple v3 = new CountAverageTuple();
    @Override
    protected void reduce(IntWritable key, Iterable<CountAverageTuple> values, Context context) throws IOException, InterruptedException {

        float sum = 0;
        float count = 0;
        for (CountAverageTuple countAverageTuple : values) {
            sum += countAverageTuple.getCount() * countAverageTuple.getAverage();
            count += countAverageTuple.getCount();
        }
        k3 = key;
        v3.setCount(count);
        v3.setAverage(sum / count);
        context.write(k3,v3);

    }
}
