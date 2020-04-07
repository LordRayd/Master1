package computeacceptedid;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;

public class ComputeAcceptedId {
    public static void main(String[] args)
            throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {

        Configuration conf = new Configuration();
        conf.setInt("mapred.reduce.tasks", 1);
        conf.setInt("mapred.map.max.attempts", 1);
        conf.setInt("mapred.reduce.max.attempts", 1);
        conf.setBoolean("mapred.map.tasks.speculative.execution", true);
        conf.setBoolean("mapred.reduce.tasks.speculative.execution", true);

        Job job = Job.getInstance(conf, "ComputeAcceptedId");
        job.getConfiguration().set("join.type", "inner");
        job.setJarByClass(ComputeAcceptedId.class);

        job.setMapperClass(AcceptedIdMapper.class);
        //job.setCombinerClass(ComputeAcceptedIdReducer.class);
        job.setReducerClass(ComputeAcceptedIdReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        // mapper
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
 
        // reducer
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("/data/stackoverflow/Posts.xml"));

        FileSystem hdfs = FileSystem.get(new URI("hdfs://hnn:9000"), conf);

        Path output_path = new Path("output-acceptedIds");
        FileOutputFormat.setOutputPath(job, output_path);

        hdfs.delete(output_path, true);

        job.waitForCompletion(true);
    }
}