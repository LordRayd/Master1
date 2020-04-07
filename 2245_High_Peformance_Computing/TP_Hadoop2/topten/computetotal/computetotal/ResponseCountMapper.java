package computetotal;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

public class ResponseCountMapper extends Mapper<Object, Text, IntWritable, IntWritable> {

    public void map(Object inputKey, Text inputValue, Context context) throws IOException, InterruptedException {

        Map<String, String> map = XmlUtils.getAttributesMap(inputValue.toString());

        if (map.containsKey("OwnerUserId") && map.containsKey("PostTypeId")) {

            if (!map.get("OwnerUserId").isEmpty() && !map.get("PostTypeId").isEmpty()) {
                if (Integer.parseInt(map.get("PostTypeId")) == 2) {
                    IntWritable outputKey = new IntWritable(Integer.parseInt(map.get("OwnerUserId")));
                    IntWritable outputValue = new IntWritable(1);
                    context.write(outputKey, outputValue);
                }
            }
        }
    }
}