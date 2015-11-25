package com.hadoop.mr.countNumberUsersByState;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 计算每个州的用户数，只有mapper，没有reducer
 * Created by shaolei on 2015/11/24 22:52.
 */
public class CountNumberUsersByStateApp {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Path input = new Path(args[0]);
        Path outputDir = new Path(args[1]);
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, CountNumberUsersByStateApp.class.getSimpleName());
        job.setJarByClass(CountNumberUsersByStateApp.class);

        job.setMapperClass(CountNumberUsersByStateMapper.class);
        job.setNumReduceTasks(0);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job, input);
        FileOutputFormat.setOutputPath(job, outputDir);

        int code = job.waitForCompletion(true) ? 0 : 1;

        if (code == 0) {
            for (Counter counter : job.getCounters().getGroup(
                    CountNumberUsersByStateMapper.STATE_COUNTER_GROUP)) {
                System.out.println(counter.getDisplayName() + "\t"
                        + counter.getValue());
            }
        }

        // Clean up empty output directory
        FileSystem.get(conf).delete(outputDir, true);

        System.exit(code);
        job.waitForCompletion(true);
    }
}
