package xyz.liangwh.simplelogger4j.core;

import java.io.RandomAccessFile;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileRegostrant {


    private Map<String,RandomAccessFile> list = new ConcurrentHashMap<>();

    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();


    public RandomAccessFile getFile(String filename){
        rw.readLock().lock();
        try{
            return   list.get(filename);
        }finally {
            rw.readLock().unlock();
        }
    }

    public RandomAccessFile addFile(String filename){
        rw.writeLock().lock();
        try {
            if(list.containsKey(filename)){
                return list.get(filename);
            }else{
                RandomAccessFile raf    = new RandomAccessFile(filename, "rw");
                list.put(filename,raf);
                return raf;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rw.writeLock().unlock();
        }
        return null;
    }
}
