package average;

import java.util.Date;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class AverageCountTuple implements Writable {

    private long amount = 0;
    private long lengthAverage = 0;

    public long getAmount() {
        return this.amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getLengthAverage() {
        return this.lengthAverage;
    }

    public void setLengthAverage(long lengthAverage) {
        this.lengthAverage = lengthAverage;
    }

    public void readFields(DataInput in) throws IOException {
        this.amount = in.readLong();
        this.lengthAverage = in.readLong();
    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(this.amount);
        out.writeLong(this.lengthAverage);
    }

    @Override
    public String toString() {
        return this.amount + "\t" + this.lengthAverage;
    }
}