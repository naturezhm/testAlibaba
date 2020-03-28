package org.alibaba.test.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.util.Iterator;

//import sun.misc.Unsafe;
//import java.lang.reflect.Field;

/**
 * (input | output) stream tool
 * @author wangzihao
 *  2018/8/11/011
 */
public class IOUtil {

    public static boolean FORCE_META_DATA = false;
    public static boolean skipWrite = false;

    /**
     * writeFile
     * @param data data
     * @param targetPath targetPath
     * @param targetFileName targetFileName
     * @param append Whether to concatenate old data
     * @throws IOException IOException
     */
    public static void writeFile(byte[] data, String targetPath, String targetFileName, boolean append)  {
        if(skipWrite){
            return;
        }
        try {
            writeFile(new Iterator<ByteBuffer>() {
                ByteBuffer buffer = ByteBuffer.wrap(data);
                @Override
                public boolean hasNext() {
                    return buffer != null;
                }

                @Override
                public ByteBuffer next() {
                    ByteBuffer tempBuffer = this.buffer;
                    this.buffer = null;
                    return tempBuffer;
                }
            },targetPath,targetFileName,append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * writeFile
     * @param dataIterator data
     * @param targetPath targetPath
     * @param targetFileName targetFileName
     * @param append Whether to concatenate old data
     * @throws IOException IOException
     * @return File
     */
    public static File writeFile(Iterator<ByteBuffer> dataIterator, String targetPath, String targetFileName, boolean append) throws IOException {
        if(targetPath == null){
            targetPath = "";
        }
        new File(targetPath).mkdirs();
        File outFile = new File(targetPath.concat(File.separator).concat(targetFileName));
        if(!outFile.exists()){
            outFile.createNewFile();
        }
        FileChannel outChannel = new FileOutputStream(outFile, append).getChannel();
        long writeBeginIndex = append? outChannel.size() : 0L;
        FileLock lock = outChannel.lock(writeBeginIndex, Long.MAX_VALUE - writeBeginIndex, false);
        try{
            long position = writeBeginIndex;
            while (dataIterator.hasNext()){
                ByteBuffer buffer = dataIterator.next();
                if(buffer == null) {
                    continue;
                }
                if(!buffer.hasRemaining()){
                    buffer.flip();
                }

                int remaining = buffer.remaining();
                position += outChannel.transferFrom(new ReadableByteChannel() {
                    @Override
                    public boolean isOpen() {
                        return true;
                    }

                    @Override
                    public void close() throws IOException {}

                    @Override
                    public int read(ByteBuffer dst) throws IOException {
                        dst.put(buffer);
                        return remaining;
                    }
                },position,Long.MAX_VALUE);
            }
        }finally {
            lock.release();
            outChannel.force(FORCE_META_DATA);
            outChannel.close();
        }
        return outFile;
    }

}
