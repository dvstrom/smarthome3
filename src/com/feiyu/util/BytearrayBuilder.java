package com.feiyu.util;
import java.io.ByteArrayOutputStream;
/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-20
 * Time: 上午11:04
 * To change this template use File | Settings | File Templates.
 */
public class BytearrayBuilder {
    public int count=0;

    public ByteArrayOutputStream output=new ByteArrayOutputStream(Const.MessageHeaderLength);;

    public BytearrayBuilder(){

    }
    public BytearrayBuilder(int capacity){
        output=new ByteArrayOutputStream(capacity);
    }


    /**
     *  在消息后追加数
     * @param i 追加的数
     * @return
     */

    public BytearrayBuilder write(int i) {
        this.output.write(i);
        return this;

    }


    public BytearrayBuilder write(byte [] b, int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) ||
                ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return this;
        }
        output.write(b, off, len);
        return this;
    }


    //只保留低八位
    public static  byte IntToByte(int i)
    {
        byte b;
        b=(byte)(i&0x00ff);
        return  b;
    }
    public static  int ByteToint(byte b)
    {

        int i=0;
        i=i+b;
        return  i;
    }

    public int returncount(){
        count=output.size();
        return count;
    }
}