package computetotal;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;

public class ComputeTotal {
    public static void main(String[] args)
            throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {

        Configuration conf = new Configuration();
        conf.setInt("mapred.reduce.tasks", 1);
        conf.setInt("mapred.map.max.attempts", 1);
        conf.setInt("mapred.reduce.max.attempts", 1);
        conf.setBoolean("mapred.map.tasks.speculative.execution", true);
        conf.setBoolean("mapred.reduce.tasks.speculative.execution", true);

        Job job = Job.getInstance(conf, "TopTenQuantity");
        //job.getConfiguration().set("join.type", "inner");
        job.setJarByClass(ComputeTotal.class);

        //un seul mapper pour 2 fichiers car format identique grâce à maxCommentCount et maxResponseCount
        //job.setMapperClass(ComputeTotalMapper.class);
        job.setCombinerClass(ComputeTotalReducer.class);
        job.setReducerClass(ComputeTotalReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        // mapper
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        // reducer
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        MultipleInputs.addInputPath(job, new Path("/data/stackoverflow/Comments.xml"),TextInputFormat.class, CommentCountMapper.class);
        MultipleInputs.addInputPath(job, new Path("/data/stackoverflow/Posts.xml"), TextInputFormat.class, ResponseCountMapper.class);

        FileSystem hdfs = FileSystem.get(new URI("hdfs://hnn:9000"), conf);

        Path output_path = new Path("output-toptentotals");
        FileOutputFormat.setOutputPath(job, output_path);

        hdfs.delete(output_path, true);

        job.waitForCompletion(true);
    }
}