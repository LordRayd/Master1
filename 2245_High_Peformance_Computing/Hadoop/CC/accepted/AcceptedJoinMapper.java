package accepted;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;

public class AcceptedJoinMapper extends Mapper<Object, Text, IntWritable, Text> {
    public static enum  MapCounters{
        /* valeurs  obtenues  sur  Posts−100K. xml / sur  Posts−1M. xml*/
        QUESTIONSNB(0),    /*valeur =  /*/
        RESPONSESNB(0),    /*valeur =  /*/
        UNKNOWPOSTTYPE(0), /*valeur =  /*/
        INVALIDPOSTTYPE(0),/*valeur =  /*/
        UNKNOWANSWERID(0), /*valeur =  /*/
        UNKNOWID(0),      /*valeur =  /*/
        UNKNOWOWNERID(0);  /*valeur =  /*/
        public int number;
        private MapCounters(int number){
            this.number = number;
        }
    };

    private IntWritable outputKey;
    private Text outputValue;

    @Override
    public void map(Object inputKey, Text inputValue, Context context) throws IOException, InterruptedException {

        Map<String, String> parsedInput = XmlUtils.getAttributesMap(inputValue.toString());
        
        if (parsedInput.containsKey("AcceptedAnswerId")) {

            MapCounters.RESPONSESNB.number++;
            if (!parsedInput.get("AcceptedAnswerId").isEmpty()) { //Champ vide

                outputKey = new IntWritable(Integer.parseInt(parsedInput.get("AcceptedAnswerId")));
                outputValue = new Text("A\t" + parsedInput.get("AcceptedAnswerId"));

                context.write(outputKey, outputValue);
            }else{
                MapCounters.UNKNOWANSWERID.number++;
            }
        } else if (parsedInput.containsKey("Id")){
            if(parsedInput.containsKey("OwnerUserId")) {
                MapCounters.QUESTIONSNB.number++;

                if (!parsedInput.get("Id").isEmpty() && !parsedInput.get("OwnerUserId").isEmpty()
                        && !parsedInput.get("PostTypeId").isEmpty()) {

                    if (parsedInput.get("PostTypeId").equals("2")) {

                        outputKey = new IntWritable(Integer.parseInt(parsedInput.get("Id")));
                        outputValue = new Text("B\t" + parsedInput.get("Id") + "\t" + parsedInput.get("OwnerUserId"));
                        
                        context.write(outputKey, outputValue);
                    }else {
                        MapCounters.INVALIDPOSTTYPE.number ++;
                    }
                }else{
                    MapCounters.UNKNOWPOSTTYPE.number++;
                }
            }else{
                MapCounters.UNKNOWOWNERID.number++;
            }
        }else{
            MapCounters.UNKNOWID.number++;
        }
    }

}