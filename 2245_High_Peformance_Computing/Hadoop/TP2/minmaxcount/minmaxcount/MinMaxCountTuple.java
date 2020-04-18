package minmaxcount.minmaxcount;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.Writable;

public class MinMaxCountTuple implements Writable {

    private Date min = new Date();
    private Date max = new Date();
    private long count = 0;

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS");

    public Date getMin() {
        return this.min;
    }

    public void setMin(Date min) {
        this.min = min;
    }

    public Date getMax() {
        return this.max;
    }

    public void setMax(Date max) {
        this.max = max;
    }

    public long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void readFields(DataInput in) throws IOException {
        this.min = new Date(in.readLong());
        this.max = new Date(in.readLong());
        this.count = in.readLong();
    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(this.min.getTime());
        out.writeLong(this.max.getTime());
        out.writeLong(this.count);
    }

    @Override
    public String toString() {
        return sdf.format(this.min) + "\t" + sdf.format(this.max) + "\t" + this.count;
    }
}