package com.hadoop.mr.averageValue;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/**
 * 平均值，计算一天的每个小时中，网上新增多少条评论，并计算这些评论的平均长度
 * //Id='8189677' PostId='6881722' Text='have you looked at hadoop?' CreationDate='2011-07030T07:29:33.343' UserId='831878'
 * Created by shaolei on 2015/11/19 23:48.
 */
public class AverageApp {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, AverageApp.class.getSimpleName());
        //判断传的参数的个数
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: AverageDriver <in> <out>");
            System.exit(2);
        }

        job.setJarByClass(AverageApp.class);

        job.setMapperClass(AverageMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(CountAverageTuple.class);

        job.setCombinerClass(AverageReduce.class);  //设置reduce为combiner

        job.setReducerClass(AverageReduce.class);
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(CountAverageTuple.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
