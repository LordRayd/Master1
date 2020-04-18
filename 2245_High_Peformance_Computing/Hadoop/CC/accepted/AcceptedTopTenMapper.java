package accepted;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

public class AcceptedTopTenMapper extends Mapper<Text, Text, NullWritable, Text> {

    private TreeMap<Integer, Text> sortedMap = new TreeMap<Integer, Text>();

    @Override
    public void map(Text inputKey, Text inputValue, Context context)
            throws IOException, InterruptedException, IllegalStateException {

        String inKey = inputKey.toString();
        String inValue = inputValue.toString();

        if (inKey.length() > 0 && inValue.length() > 0) {
            sortedMap.put(Integer.valueOf(inValue), new Text(inKey + "\t" + inValue));

            if (sortedMap.size() > 10) {
                sortedMap.remove(sortedMap.firstKey());
            }
        }

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        for (Text t : sortedMap.values()) {
            context.write(NullWritable.get(), t);
        }
    }
}