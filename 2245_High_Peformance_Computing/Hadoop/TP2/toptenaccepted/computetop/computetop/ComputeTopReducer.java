package computetop;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ComputeTopReducer extends Reducer<NullWritable, Text, NullWritable, Text> {

    private TreeMap<Integer, Text> sortedMap = new TreeMap<Integer, Text>();

    @Override
    public void reduce(NullWritable inputKey, Iterable<Text> inputValues, Context context)
            throws IOException, InterruptedException {

        for (Text val : inputValues) {
            String[] input = val.toString().split("\t");
            if (input.length == 2) {
                String inKey = input[0];
                String inValue = input[1];

                sortedMap.put(Integer.valueOf(inValue), new Text(val));

                if (sortedMap.size() > 10) {
                    sortedMap.remove(sortedMap.firstKey());
                }
            }
        }

        for (Text t : sortedMap.descendingMap().values()){
            context.write(NullWritable.get(), t);
        }
    }
}