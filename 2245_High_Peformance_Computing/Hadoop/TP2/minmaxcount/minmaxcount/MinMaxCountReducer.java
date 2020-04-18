package minmaxcount.minmaxcount;

import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MinMaxCountReducer extends Reducer<IntWritable, MinMaxCountTuple, IntWritable, MinMaxCountTuple> {

    @Override
    public void reduce(IntWritable inputKey, Iterable<MinMaxCountTuple> inputValues, Context context) throws IOException, InterruptedException {

        Date min = new Date();
        Date max = new Date();
        long count = 0;

        for(MinMaxCountTuple tuple : inputValues){

            if(min.compareTo(tuple.getMin()) > 0){
                min = tuple.getMin();
            }

            if(max.compareTo(tuple.getMax()) < 0){
                max = tuple.getMax();
            }

            count += tuple.getCount();
        }

        MinMaxCountTuple outputValue = new MinMaxCountTuple();
        outputValue.setMin(min);
        outputValue.setMax(max);
        outputValue.setCount(count);
        context.write(inputKey, outputValue);
    }
}