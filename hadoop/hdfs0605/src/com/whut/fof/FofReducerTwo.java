package com.whut.fof;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FofReducerTwo extends Reducer<Friend,IntWritable,Text,NullWritable>{
@Override
protected void reduce(Friend arg0, Iterable<IntWritable> arg1,
		Reducer<Friend, IntWritable, Text, NullWritable>.Context arg2) throws IOException, InterruptedException {
     for(IntWritable i:arg1){
    	 String msg=arg0.getFriend1()+"-"+arg0.getFriend2()+";"+i.get();
    	 arg2.write(new Text(msg), NullWritable.get());
     }
}
}
