package com.feiyu.connect;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-24
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
public interface Message {
    /**
     * 获取数据包的总长度（包括包头加包体的长度）
     * @return数据包的总长度
     */
    public abstract int getMessageLength();
    /**
     * 获取数据包的类型
     * @return数据包的类型
     */
    public abstract int getMessageType();
    /**
     * 解析数据包的内容。使字节流转换成类对象。该方法主要完成对包体的解析过程。
     * @param b 字节数组（字节流）
     * @throws  当解析时发生异常时抛出 ParseException
     */
    public abstract void parse(byte [] b) throws ParseException;
    /**
     * 将类对象的内容转换成字节数组
     * @return 类对象对应的字节数组
     */
    // public abstract byte [] getBytes();
}

