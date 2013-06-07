package com.feiyu.connect;

import com.feiyu.util.BytearrayBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-24
 * Time: ����9:35
 * To change this template use File | Settings | File Templates.
 */
public class AbstractMessage {



    public BytearrayBuilder bytearrayBuilder =new BytearrayBuilder();
    public int type;

    public int id;
    public AbstractMessage(int id, int type) {
        this.id = id;
        this.type = type;
        setMessageHeader();
    }

    /**
     * ��ͷ���ȡ���Э����ö�����ͷ������Ϊ5)
     */

    public void  setMessageHeader(){
        int BOC=0x7E;
        int SEN=0x0;
        int PID=0x11;
        bytearrayBuilder.write(BOC).write(SEN).write(PID).write(type).write(id);
    }

    public byte[] getBytes() {
        return this.bytearrayBuilder.output.toByteArray();
    }

}