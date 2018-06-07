package com.whut.fof;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FofJobTwo {
	public static void main(String[] args) throws Exception{
		Configuration conf=new Configuration();
		
		Job job=Job.getInstance();
		
		job.setJarByClass(FofJobTwo.class);
		
		job.setMapperClass(FofMapperTwo.class);
		job.setMapOutputKeyClass(Friend.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setReducerClass(FofReducerTwo.class);
		job.setSortComparatorClass(FofSort.class);
		job.setGroupingComparatorClass(FofGroup.class);
		
		FileInputFormat.addInputPath(job, new Path(""));
		
		
		Path outpath=new Path("");
		FileSystem fs=FileSystem.get(conf);
		
		if(fs.exists(outpath)){
			fs.delete(outpath,true);
		}
		
		
		FileOutputFormat.setOutputPath(job, outpath);
		
		boolean flag=job.waitForCompletion(true);
		if(flag){
			System.out.println("job success");
		}
	}
}
