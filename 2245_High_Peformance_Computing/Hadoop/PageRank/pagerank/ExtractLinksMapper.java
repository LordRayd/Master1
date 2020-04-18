package pagerank;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ExtractLinksMapper extends Mapper<Text, Text, Text, TupleWritable> {

    public static enum MapCounters {
        PAGE_CNT,
        LINK_CNT
    };
    private static Pattern pattern = Pattern.compile("\\[\\[.+\\]\\]");
    private static Matcher matcher;

    @Override
    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        context.getCounter(MapCounters.PAGE_CNT).increment(1);
        LinkedList<String> list = new LinkedList<String>();
        list.add((new Integer(1)).toString());
        String body = value.toString();
        matcher = pattern.matcher(body);
        while (matcher.find()) {
            context.getCounter(MapCounters.LINK_CNT).increment(1);
            list.add(body.substring(matcher.start(),matcher.end()));
        }
        context.write(key,new TupleWritable(list));
    }
}