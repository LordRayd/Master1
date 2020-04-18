package computeacceptedid;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

public class AcceptedIdMapper extends Mapper<Object, Text, IntWritable, Text> {

    private IntWritable outputKey;
    private Text outputValue;

    @Override
    public void map(Object inputKey, Text inputValue, Context context) throws IOException, InterruptedException {

        Map<String, String> parsedInput = XmlUtils.getAttributesMap(inputValue.toString());
        if (parsedInput.containsKey("AcceptedAnswerId")) {
            if (!parsedInput.get("AcceptedAnswerId").isEmpty()) {
                outputKey = new IntWritable(Integer.parseInt(parsedInput.get("AcceptedAnswerId")));
                outputValue = new Text("A\t" + parsedInput.get("AcceptedAnswerId"));
                context.write(outputKey, outputValue);
            }
        } else if (parsedInput.containsKey("Id") && parsedInput.containsKey("OwnerUserId")) {
            if (!parsedInput.get("Id").isEmpty() && !parsedInput.get("OwnerUserId").isEmpty()
                    && !parsedInput.get("PostTypeId").isEmpty()) {
                if (parsedInput.get("PostTypeId").equals("2")) {
                    outputKey = new IntWritable(Integer.parseInt(parsedInput.get("Id")));
                    outputValue = new Text("B\t" + parsedInput.get("Id") + "\t" + parsedInput.get("OwnerUserId"));
                    context.write(outputKey, outputValue);
                }
            }
        }
    }

}