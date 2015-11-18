package com.hadoop.mr.SecondarySort;

/**
 * 二级排序
 * Created by shaolei on 2015/11/16 23:24.
 */

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SecondarySortApp {

    /**
     * 分组函数类。只要first相同就属于同一个组。
     */
    /*//第一种方法，实现接口RawComparator
    public static class GroupingComparator implements RawComparator<IntPair> {
        @Override
        public int compare(IntPair o1, IntPair o2) {
            int l = o1.getFirst();
            int r = o2.getFirst();
            return l == r ? 0 : (l < r ? -1 : 1);
        }
        @Override
        //一个字节一个字节的比，直到找到一个不相同的字节，然后比这个字节的大小作为两个字节流的大小比较结果。
        public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2){
            // TODO Auto-generated method stub
             return WritableComparator.compareBytes(b1, s1, Integer.SIZE/8,
                     b2, s2, Integer.SIZE/8);
        }
    }*/
    //第二种方法，继承WritableComparator


    // 自定义map


    // 自定义reduce
    //


    /**
     * @param args
     */
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // TODO Auto-generated method stub
        // 读取hadoop配置
        Configuration conf = new Configuration();
        // 实例化一道作业
        Job job = Job.getInstance(conf, SecondarySortApp.class.getSimpleName());
        job.setJarByClass(SecondarySortApp.class);
        // Mapper类型
        job.setMapperClass(SecondarySortMap.class);
        // 不再需要Combiner类型，因为Combiner的输出类型<Text, IntWritable>对Reduce的输入类型<IntPair, IntWritable>不适用
        //job.setCombinerClass(SecondarySortReduce.class);
        // Reducer类型
        job.setReducerClass(SecondarySortReduce.class);
        // 分区函数
        job.setPartitionerClass(FirstPartitioner.class);
        // 分组函数
        job.setGroupingComparatorClass(GroupingComparator.class);

        // map 输出Key的类型
        job.setMapOutputKeyClass(IntPair.class);
        // map输出Value的类型
        job.setMapOutputValueClass(IntWritable.class);
        // rduce输出Key的类型，是Text，因为使用的OutputFormatClass是TextOutputFormat
        job.setOutputKeyClass(Text.class);
        // rduce输出Value的类型
        job.setOutputValueClass(IntWritable.class);

        // 将输入的数据集分割成小数据块splites，同时提供一个RecordReder的实现。
        job.setInputFormatClass(TextInputFormat.class);
        // 提供一个RecordWriter的实现，负责数据输出。
        job.setOutputFormatClass(TextOutputFormat.class);

        // 输入hdfs路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        // 输出hdfs路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // 提交job
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
