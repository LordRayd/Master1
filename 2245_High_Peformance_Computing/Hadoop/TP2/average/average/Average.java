package average;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;

public class Average {
    public static void main(String[] args)
            throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
        Configuration conf = new Configuration();
        conf.setInt("mapred.reduce.tasks", 1);
        conf.setInt("mapred.map.max.attempts", 1);
        conf.setInt("mapred.reduce.max.attempts", 1);
        conf.setBoolean("mapred.map.tasks.speculative.execution", true);
        conf.setBoolean("mapred.reduce.tasks.speculative.execution", true);

        Job job = Job.getInstance(conf, "Average");
        job.setJarByClass(Average.class);
        job.setMapperClass(AverageMapper.class);
        job.setCombinerClass(AverageReducer.class);
        job.setReducerClass(AverageReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        // mapper
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(AverageCountTuple.class);

        // reducer
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(AverageCountTuple.class);

        FileInputFormat.setInputPaths(job, new Path("/data/stackoverflow/Comments.xml"));
        FileSystem hdfs = FileSystem.get(new URI("hdfs://hnn:9000"), conf);

        Path output_path = new Path("output-average");
        FileOutputFormat.setOutputPath(job, output_path);

        hdfs.delete(output_path, true);

        job.waitForCompletion(true);
    }
}