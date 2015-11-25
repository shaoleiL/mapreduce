package com.hadoop.mr.countNumberUsersByState;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by shaolei on 2015/11/24 22:56.
 */
public class CountNumberUsersByStateMapper extends Mapper<Object, Text, NullWritable, NullWritable> {

    public static final String STATE_COUNTER_GROUP = "State";
    public static final String UNKNOWN_COUNTER = "Unknown";
    public static final String NULL_OR_EMPTY_COUNTER = "Null or Empty";
    private String[] statesArray = new String[]{"AL", "AK", "AZ", "AR",
            "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
            "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS",
            "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND",
            "OH", "OK", "OR", "PA", "RI", "SC", "SF", "TN", "TX", "UT",
            "VT", "VA", "WA", "WV", "WI", "WY"};
    private HashSet<String> states = new HashSet<String>(
            Arrays.asList(statesArray));

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] splited = line.split("\t");
        String location = splited[2];

        // 判断获取的是否为空值
        if (location != null && !location.isEmpty()) {
            //把location的值转为大写，用空格分割
            String[] tokens = location.toUpperCase().split("\\s");
            boolean unknown = true;
            for (String state : tokens) {
                //states是否包含state
                if (states.contains(state)) {
                    context.getCounter(STATE_COUNTER_GROUP, state).increment(1);
                    unknown = false;
                    break;
                }
            }
            if (unknown) {
                context.getCounter(STATE_COUNTER_GROUP, UNKNOWN_COUNTER).increment(1);
            }

        } else {
            context.getCounter(STATE_COUNTER_GROUP, NULL_OR_EMPTY_COUNTER).increment(1);
        }
    }
}
