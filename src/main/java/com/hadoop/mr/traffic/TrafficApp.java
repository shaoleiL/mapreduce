package com.hadoop.mr.traffic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by shaolei on 2015/11/11.
 */
public class TrafficApp {

    public static void main(String[] args) throws Exception {

        Job job = Job.getInstance(new Configuration(), TrafficApp.class.getSimpleName());
        job.setJarByClass(TrafficApp.class);

        FileInputFormat.setInputPaths(job, args[0]);

        job.setMapperClass(TrafficMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(TrafficWritable.class);

        job.setReducerClass(TrafficReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(TrafficWritable.class);

        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }


}
