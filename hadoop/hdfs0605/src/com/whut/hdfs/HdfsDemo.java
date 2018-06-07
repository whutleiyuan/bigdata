package com.whut.hdfs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HdfsDemo {
	FileSystem fs;
	Configuration conf;
	@Before
	public void begin(){
		conf=new Configuration();
		try {
			fs=FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void end(){
		try {
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//创建文件
	@Test
	public void mkdir() throws Exception{
		Path path=new Path("/tmp");
		fs.mkdirs(path);
	}
	
	@Test
	public void upload() throws Exception{
		Path f=new Path("/tmp/test");
		FSDataOutputStream outputStream=fs.create(f);
		FileUtils.copyFile(new File("D://test.txt"), outputStream);
	}
	
	
	@Test
	public void list() throws Exception, IOException{
		Path path=new Path("/tmp");
		FileStatus[] fss=fs.listStatus(path);
		for(FileStatus s:fss){
			System.out.println(s.getPath()+"---"+s.getLen());
		}
	}
	
}
