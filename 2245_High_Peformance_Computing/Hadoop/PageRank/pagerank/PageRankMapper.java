package pagerank;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageRankMapper extends Mapper<Text, TupleWritable, Text, TupleWritable> {
    public static enum MapCounters {
        PAGE_CNT
    };
    @Override
    public void map(Text key, TupleWritable value, Context context) throws IOException, InterruptedException {
        
        context.getCounter(MapCounters.PAGE_CNT).increment(1);
        for(int i=1; i< value.size(); i++){
            String tab[] = {key.toString(), value.get(0), (new Integer(value.size()-1)).toString()};
            context.write(new Text(value.get(i)),new TupleWritable(tab));
        }
    }

}