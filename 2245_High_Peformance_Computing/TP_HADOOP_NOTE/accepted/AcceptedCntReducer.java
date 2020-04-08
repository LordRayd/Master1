package accepted;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AcceptedCntReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

    // recopier  dans  AcceptedCntReducer 
    public static enum  ReduceCounters{
        /*valeurs  obtenues  sur  Posts−100K. xml / sur  Posts−1M. xml*/
        USERCNT(0),/*valeur =  /*/
        SUMCNT(0);/*valeur =  /*/
        public int number;
        private ReduceCounters(int n){
            this.number = n;
        }
    };
    @Override
    public void reduce(IntWritable inputKey, Iterable<IntWritable> inputValues, Context context)
            throws IOException, InterruptedException {

        int count = 0;
                
        for (IntWritable value : inputValues) {
            count += value.get();
        }

        IntWritable outputValue = new IntWritable(count);
        ReduceCounters.USERCNT.number++;
        ReduceCounters.SUMCNT.number += count;
        context.write(inputKey, outputValue);
    }
}