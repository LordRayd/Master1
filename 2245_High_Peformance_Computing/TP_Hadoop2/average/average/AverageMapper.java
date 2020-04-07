package average;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;


public class AverageMapper extends Mapper<Object, Text, IntWritable, AverageCountTuple> {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public void map(Object inputKey, Text inputValue, Context context)
            throws IOException, InterruptedException {

        Map<String, String> map = XmlUtilsOld.transformXmlToMap(inputValue.toString());

        if (map.containsKey("CreationDate") && map.containsKey("Text")) {

            AverageCountTuple outputValue = new AverageCountTuple();
            Calendar commentDate = Calendar.getInstance();

            try {
                if (!map.get("CreationDate").isEmpty() && !map.get("Text").isEmpty()) {
                    commentDate.setTime(sdf.parse(map.get("CreationDate")));
                    IntWritable outputKey = new IntWritable(commentDate.get(Calendar.HOUR_OF_DAY));

                    outputValue.setAmount(1);
                    outputValue.setLengthAverage(map.get("Text").length());
                    context.write(outputKey, outputValue);
                }
            } catch (ParseException e) {
                System.out.println(e);
            }
        }
    }
}