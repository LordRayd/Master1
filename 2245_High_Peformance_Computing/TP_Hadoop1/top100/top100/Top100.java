/**
 * Exemple de programme MapReduce
 * Compte le nombre d'occurrences de mots recherchés
 * @author raimbaul
 */
package top100;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;//TODO Jules Audurier
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import top100.Top100Mapper.MapCounters;
import top100.Top100Reducer.ReduceCounters;

public class Top100 {
  
  //a executer par : yarn jar top100.jar top100.Top100
  //on peut récupérer les sortie sur stdout par: yarn logs -applicationId application_XXXXXXXXX -log_files=stdout

  public static void main(String[] args) 
      throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
        
    Configuration conf = new Configuration();  
    //conf.set("regex", "cow|dog"); 
    conf.setInt("mapred.reduce.tasks",1);
    conf.setInt("mapred.map.max.attempts",1);
    conf.setInt("mapred.reduce.max.attempts",1);
    conf.setBoolean("mapred.map.tasks.speculative.execution",true);
    conf.setBoolean("mapred.reduce.tasks.speculative.execution",true);

    Job job= Job.getInstance(conf,"Top100");

    //TODO Jules Audurier
    URI stopUri = new URI("/data/stop-words/stop-words-english4.txt"+"#stopWords");
    job.addCacheFile(stopUri);

    job.setJarByClass(Top100.class);
    FileInputFormat.setInputPaths(job, new Path("/data/Gutenberg"));
    job.setMapperClass(Top100Mapper.class);
    job.setInputFormatClass(CombineTextInputFormat.class);//ou CombineTextInputFormat pour un gd nb de petits fichiers
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    job.setCombinerClass(Top100Reducer.class); //TODO Jules Audurier
    job.setReducerClass(Top100Reducer.class);

    FileSystem hdfs= FileSystem.get(new URI("hdfs://hnn:9000"),conf);
    Path output_path= new Path("output-Top100");

    hdfs.delete(output_path, true); // supprime le répertoire de sortie
    FileOutputFormat.setOutputPath(job, output_path);
    job.setOutputFormatClass(TextOutputFormat.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.waitForCompletion(true);

    Counter match_counter=  job.getCounters().findCounter(MapCounters.ACCEPTED_CNT);
    System.out.println("number of accepted words= "+match_counter.getValue());
    Counter unmatch_counter=  job.getCounters().findCounter(MapCounters.REJECT_CNT);
    System.out.println("number of rejected words= "+unmatch_counter.getValue());

    Counter index_counter=  job.getCounters().findCounter(ReduceCounters.INDEXED_WORDS);
    System.out.println("number of indexed words= "+index_counter.getValue());
  }

}
