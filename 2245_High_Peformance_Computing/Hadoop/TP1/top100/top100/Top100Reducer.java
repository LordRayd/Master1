package top100;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Top100Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {

  public static enum ReduceCounters {
    INDEXED_WORDS, // nombre de mots qui ont été indexés
  };

    public void reduce(Text key, Iterable<IntWritable> values, Context context) 
      throws IOException, InterruptedException{ 
      
      int sum = 0;
      for (IntWritable value : values) {
        sum += value.get();
      }
      context.write(key, new IntWritable(sum));
      context.getCounter(ReduceCounters.INDEXED_WORDS).increment(1);
    }
  }