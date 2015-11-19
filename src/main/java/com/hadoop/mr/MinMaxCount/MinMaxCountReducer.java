package com.hadoop.mr.MinMaxCount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 最小值，最大值与计数的MinMaxCountReducer
 * Created by shaolei on 2015/11/18 23:21.
 */
public class MinMaxCountReducer extends Reducer<Text, MinMaxCountTuple, Text, MinMaxCountTuple> {

    private Text k3 = new Text();
    private MinMaxCountTuple v3 = new MinMaxCountTuple();

    @Override
    protected void reduce(Text key, Iterable<MinMaxCountTuple> values, Context context) throws IOException, InterruptedException {
        k3 = key;
        v3.setMax(null);
        v3.setMin(null);
        v3.setCount(0);
        int sum = 0;
        for (MinMaxCountTuple v2 : values) {
            if (v3.getMin() == null || v2.getMin().compareTo(v3.getMin()) < 0) {
                v3.setMin(v2.getMin());
            }
            if(v3.getMax() == null || v2.getMax().compareTo(v3.getMax()) > 0){
                v3.setMax(v2.getMax());
            }
            sum += v2.getCount();
        }
        v3.setCount(sum);
        context.write(k3, v3);

    }
}
