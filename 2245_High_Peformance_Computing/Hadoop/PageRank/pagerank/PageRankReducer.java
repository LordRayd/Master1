package pagerank;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageRankReducer extends Reducer<Text, TupleWritable, Text, TupleWritable> {

    public static enum ReduceCounters {
        PAGE_RANK
    };

    @Override
    public void reduce(Text key, Iterable<TupleWritable> values, Context context) throws IOException, InterruptedException{
        double sum = 0.0;
        int nb = 0;
        LinkedList<String> list = new LinkedList<String>();
        for(TupleWritable tuple : values){
            list.add(tuple.get(0));
            sum += Integer.parseInt(tuple.get(1)) / Integer.parseInt(tuple.get(2));
            nb++;
        }
        Double res = new Double(((1 - PageRankMR.D_FACTOR) / nb) + sum) ;
        context.getCounter(ReduceCounters.PAGE_RANK).increment(res.longValue());
        list.addFirst(res.toString());
        context.write(key, new TupleWritable(list));
    }
}