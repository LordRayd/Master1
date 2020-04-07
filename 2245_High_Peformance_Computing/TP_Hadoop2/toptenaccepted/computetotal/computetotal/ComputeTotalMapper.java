package computetotal;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

public class ComputeTotalMapper extends Mapper<Text, Text, IntWritable, IntWritable> {

    @Override
    public void map(Text inputKey, Text inputValue, Context context)
            throws IOException, InterruptedException, IllegalStateException {

        String inKey = inputKey.toString();
        String inValue = inputValue.toString();

        if (inKey.length() > 0 && inValue.length() > 0) {
            IntWritable outputKey = new IntWritable(Integer.parseInt(inValue));
            IntWritable outputValue = new IntWritable(1);

            context.write(outputKey, outputValue);
        }

    }
}