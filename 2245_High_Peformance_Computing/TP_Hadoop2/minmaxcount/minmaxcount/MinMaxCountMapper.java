package minmaxcount.minmaxcount;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MinMaxCountMapper extends Mapper<Object, Text, IntWritable, MinMaxCountTuple> {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public void map(Object inputKey, Text inputValue, Context context) throws IOException, InterruptedException {
        Map<String, String> map = XmlUtils.transformXmlToMap(inputValue.toString());

        if (map.containsKey("CreationDate") && map.containsKey("UserId")) {
            MinMaxCountTuple outputValue = new MinMaxCountTuple();

            try {
                Date commentDate = sdf.parse(map.get("CreationDate"));

                outputValue.setMin(commentDate);
                outputValue.setMin(commentDate);
                outputValue.setCount(1);
            } catch (ParseException e) {
                System.out.println(e);
            }

            if (!map.get("UserId").isEmpty()) {
                IntWritable outputKey = new IntWritable(Integer.parseInt(map.get("UserId")));
                context.write(outputKey, outputValue);
            }

        }
    }
}