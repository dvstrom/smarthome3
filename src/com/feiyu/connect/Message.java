package com.feiyu.connect;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-24
 * Time: ����10:17
 * To change this template use File | Settings | File Templates.
 */
public interface Message {
    /**
     * ��ȡ���ݰ����ܳ��ȣ�������ͷ�Ӱ���ĳ��ȣ�
     * @return���ݰ����ܳ���
     */
    public abstract int getMessageLength();
    /**
     * ��ȡ���ݰ�������
     * @return���ݰ�������
     */
    public abstract int getMessageType();
    /**
     * �������ݰ������ݡ�ʹ�ֽ���ת��������󡣸÷�����Ҫ��ɶ԰���Ľ������̡�
     * @param b �ֽ����飨�ֽ�����
     * @throws  ������ʱ�����쳣ʱ�׳� ParseException
     */
    public abstract void parse(byte [] b) throws ParseException;
    /**
     * ������������ת�����ֽ�����
     * @return ������Ӧ���ֽ�����
     */
    // public abstract byte [] getBytes();
}

