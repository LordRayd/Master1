package accepted;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AcceptedJoinReducer extends Reducer<IntWritable, Text, IntWritable, IntWritable> {

    private ArrayList<Text> listA;
    private ArrayList<Text> listB;

    @Override
    public void reduce(IntWritable inputKey, Iterable<Text> inputValues, Context context)
            throws IOException, InterruptedException {
        listA = new ArrayList<Text>();
        listB = new ArrayList<Text>();

        for (Text in : inputValues) {
            if (in.charAt(0) == 'A') {
                listA.add(new Text("\0"));
            } else if (in.charAt(0) == 'B') {
                listB.add(new Text(in.toString().substring(2)));
            } else {
                throw new IOException("First letter of value received by reducer is wrong");
            }
        }
        if (!listA.isEmpty() && !listB.isEmpty()) {
            for (Text a : listA){
                for (Text b : listB){
                    String[] data = b.toString().split("\t");
                    context.write(new IntWritable(Integer.parseInt(data[0])), new IntWritable(Integer.parseInt(data[1])));
                }
            }
        }
    }
}