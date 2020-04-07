package wc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

  // private Pattern pattern_regexp;
  private HashSet stoppedWordsHashSet; // TODO Jules Audurier
  private static final Pattern pattern_word = Pattern.compile("\\w+");
  private static final IntWritable ONE = new IntWritable(1);

  public static enum MapCounters {
    //MATCHED_WORDS, // nombre de mots qui ont été trouvés
    REJECT_CNT, //TODO Jules Audurier
    ACCEPTED_CNT //TODO Jules Audurier
  };

  @Override
  public void setup(Context context) {
    stoppedWordsHashSet = new HashSet<>();
    try {
      if (context.getCacheFiles()[0] != null) {
        BufferedReader stoppedReader = new BufferedReader(new FileReader("stopWords"));
        String stoppedWord;
        while ((stoppedWord = stoppedReader.readLine()) != null) {
          stoppedWordsHashSet.add(stoppedWord);
        }
        stoppedReader.close();
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    // pattern_regexp = Pattern.compile(context.getConfiguration().get("regex"));
  }

  @Override
  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

    Matcher match = pattern_word.matcher(value.toString());
    while (match.find()) {
      String word = match.group().toLowerCase();
      if(!stoppedWordsHashSet.contains(word)) { //TODO Jules Audurier
        context.write(new Text(word), ONE);
        context.getCounter(MapCounters.ACCEPTED_CNT).increment(1);
      }else{
        context.getCounter(MapCounters.REJECT_CNT).increment(1);
      }
    }
  }

  @Override
  public void cleanup(Context context) {
  }

}