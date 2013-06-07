package com.feiyu.connect;

import com.feiyu.util.BytearrayBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-24
 * Time: ÉÏÎç10:56
 * To change this template use File | Settings | File Templates.
 */
public class CommandMessage extends AbstractMessage implements Message{

    public int command;
    public int data;

    public CommandMessage(int id, int type, int command, int data ) {
        super(id, type);
        this.bytearrayBuilder.write(command).write(2).write(data);

    }


    public int getMessageLength() {
        return bytearrayBuilder.returncount();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMessageType() {
        return this.type;

        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void parse(byte[] b) throws ParseException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}