package computetotal;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

public class CommentCountMapper extends Mapper<Object, Text, IntWritable, IntWritable> {

    public void map(Object inputKey, Text inputValue, Context context) throws IOException, InterruptedException {

        Map<String, String> map = XmlUtils.getAttributesMap(inputValue.toString());

        if (map.containsKey("UserId")) {

            if (!map.get("UserId").isEmpty()) {
                IntWritable outputKey = new IntWritable(Integer.parseInt(map.get("UserId")));
                IntWritable outputValue = new IntWritable(1);

                context.write(outputKey, outputValue);
            }
        }
    }
}