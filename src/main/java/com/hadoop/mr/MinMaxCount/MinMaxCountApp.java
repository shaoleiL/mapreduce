package com.hadoop.mr.MinMaxCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 最大值、最小值与计数
 * Created by shaolei on 2015/11/18 23:42.
 */
public class MinMaxCountApp {
    public static void main(String[] args) throws Exception {
        Job job = Job.getInstance(new Configuration(), MinMaxCountApp.class.getSimpleName());
        job.setJarByClass(MinMaxCountApp.class);
        FileInputFormat.setInputPaths(job, args[0]);

        job.setMapperClass(MinMaxCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(MinMaxCountTuple.class);

        job.setCombinerClass(MinMaxCountReducer.class); //使用combiner

        job.setReducerClass(MinMaxCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(MinMaxCountTuple.class);

        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
