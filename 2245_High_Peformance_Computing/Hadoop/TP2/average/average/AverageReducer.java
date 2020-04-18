package average;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageReducer extends Reducer<IntWritable, AverageCountTuple, IntWritable, AverageCountTuple> {

    @Override
    public void reduce(IntWritable inputKey, Iterable<AverageCountTuple> inputValues, Context context)
            throws IOException, InterruptedException {

        long amount = 0;
        long avg = 0;

        for (AverageCountTuple tuple : inputValues) {
            avg += tuple.getLengthAverage() * tuple.getAmount();
            amount += tuple.getAmount();
        }
        avg = Math.round(avg / (double)amount);

        AverageCountTuple outputValue = new AverageCountTuple();
        outputValue.setAmount(amount);
        outputValue.setLengthAverage(avg);

        context.write(inputKey, outputValue);
    }
}