import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemPut {
    public static void main(String[] args) throws Exception{
        String localStr = args[0];
        String hdfsStr = args[1];
        Configuration conf = new Configuration();
        FileSystem local = FileSystem.getLocal(conf);
        FileSystem hdfs = FileSystem.get(URI.create(hdfsStr), conf);
        Path localFile = new Path(localStr);
        Path hdfsFile = new Path(hdfsStr);
        FSDataInputStream in = local.open(localFile);
        FSDataOutputStream out = hdfs.create(hdfsFile);
        IOUtils.copyBytes(in, out, 4096, true);
    }
}
