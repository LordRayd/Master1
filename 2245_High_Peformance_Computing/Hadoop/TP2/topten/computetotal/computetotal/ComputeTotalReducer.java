package computetotal;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ComputeTotalReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

    @Override
    public void reduce(IntWritable inputKey, Iterable<IntWritable> inputValues, Context context)
            throws IOException, InterruptedException {

        int amount = 0;
                
        for (IntWritable value : inputValues) {
            amount += value.get();
        }

        IntWritable outputValue = new IntWritable(amount);

        context.write(inputKey, outputValue);
    }
}