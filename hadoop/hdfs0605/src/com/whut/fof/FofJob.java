package com.whut.fof;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FofJob {
	public static void main(String[] args) throws Exception{
		Configuration conf=new Configuration();
		
		Job job=Job.getInstance();
		
		job.setJarByClass(FofJob.class);
		
		job.setMapperClass(FofMapperOne.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setReducerClass(FofReducer.class);
		
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
