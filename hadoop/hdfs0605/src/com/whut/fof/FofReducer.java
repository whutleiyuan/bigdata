package com.whut.fof;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FofReducer extends Reducer<Text,IntWritable,Text,NullWritable>{
@Override
protected void reduce(Text arg0, Iterable<IntWritable> arg1,
		Reducer<Text, IntWritable, Text, NullWritable>.Context arg2) throws IOException, InterruptedException {
	//Ç×ÃÜ¶È
	int sum=0;
	boolean flag=true;
	for(IntWritable i:arg1){
		if(i.get()==0){
			flag=false;
			break;
		}
		sum+=i.get();
	}
	if(flag){
		String msg=arg0.toString()+"-"+sum;
		arg2.write(new Text(msg), NullWritable.get());
	}
	
}
}
