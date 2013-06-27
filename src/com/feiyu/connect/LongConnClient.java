package com.feiyu.connect;

/**客户端长连接k
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-17
 * Time: 上午11:07
 * To change this template use File | Settings | File Templates.
 */
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * socket 长连接类
 * @author yf
 * @version 1.0
 */
/*
 * @author Administrator
 *
 */
public class LongConnClient extends Thread {

    public String ip=null;//连接服务器的IP
    public Integer port=null;//连接服务器的端口
    private Socket socket=null;//套节字对象
    private boolean close = false; // 关闭连接标志位，true表示关闭，false表示连接
    private Integer sotimeout=1*1*10;//超时时间，以毫秒为单位
    private static OutputStream out;
    private static InputStream in;

    public LongConnClient(){
        init();
    }
    public LongConnClient(String ip,Integer port){
        setIp(ip);
        setPort(port);
        init();
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Integer getPort() {
        return port;
    }
    public void setPort(Integer port) {
        this.port = port;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public boolean isClose() {
        return close;
    }
    public void setClose(boolean close) {
        this.close = close;
    }
    public Integer getSotimeout() {
        return sotimeout;
    }
    public void setSotimeout(Integer sotimeout) {
        this.sotimeout = sotimeout;
    }

    /**
     * 初始化socket对象
     */
    public void init(){
        try {
            InetAddress address = InetAddress.getByName(getIp());
            socket = new Socket(address,getPort());
            socket.setKeepAlive(true);//开启保持活动状态的套接字
            socket.setSoTimeout(sotimeout);//设置超时时间
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //this.bos=new ByteArrayOutputStream();
            out=socket.getOutputStream();
            in=socket.getInputStream();
            close=!Send(socket,"2");//发送初始数据，发送成功则表示已经连接上，发送失败表示已经断开
        }catch(UnknownHostException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 读数据线程
     */
    public void run() {
        while(true){
            //---------读数据---------------------------
            //close = isServerClose(socket);//判断是否断开
            if(!close){//没有断开，开始读数据
                byte[] receive = ReadByte(socket);
                int readcount=0;
                if(receive!=null && receive.length>0){
                      readcount=receive.length;
                      for (int i=0;i<readcount; i++){
                        System.out.println("监听读取"+receive[i]);
                    }
                }
                if (readcount>4){
                    Message m=new CommandMessage();
                    if (m != null) {
                        try {
                            m.parse(receive);
                        } catch (ParseException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                    }
                }

            }

            //---------创建连接-------------------------
            while(close){//已经断开，重新建立连接
                try{
                    System.out.println("重新建立连接："+getIp()+":"+getPort());
                    InetAddress address = InetAddress.getByName(getIp());
                    socket = new Socket(address,getPort());
                    socket.setKeepAlive(true);
                    socket.setSoTimeout(sotimeout);
                    close = !Send(socket,"2");
                    System.out.println("建立连接成功："+getIp()+":"+getPort());
                }catch(Exception se){
                    System.out.println("创建连接失败:"+getIp()+":"+getPort());
                    close=true;
                }
            }
        }
    }


    /**
     * 发送数据，发送失败返回false,发送成功返回true
     * @param csocket
     * @param bos
     * @return
     */
    public Boolean Send(Socket csocket,ByteArrayOutputStream bos){
        try{
            System.out.println("C: sending...");
            for (byte bs:bos.toByteArray()){
                System.out.println(bs);
            }
            System.out.println("发送的数据长度为："+ bos.size());
            out.write(bos.toByteArray());
            out.flush();
            return true;
        }catch(Exception se){
            se.printStackTrace();
            return false;
        }
    }

    public Boolean Send(Socket csocket, AbstractMessage message){
        Boolean bool;
        bool=Send(csocket,message.bytearrayBuilder.output);
        return bool;
    }
    public Boolean Send( AbstractMessage message){
        Boolean bool;
        bool=Send(this.socket,message);
        return bool;
    }
    public Boolean Send(Socket csocket, String message){
        try{
            System.out.println("C: sending...");
            PrintWriter Pout = new PrintWriter(socket.getOutputStream(), true);
            Pout.println(message);
            System.out.println(new String(message.getBytes()));
            // mBufferedWriter.write((new String(message.getBytes()))+"\n");
            // mBufferedWriter.flush();

            return true;
        }catch(Exception se){
            se.printStackTrace();
            return false;
        }
    }
    /**
     * 读取数据，返回字符串类型
     * @param csocket
     * @return
     */
    public byte[] ReadByte(Socket csocket){
        try{
       //     csocket.setSoTimeout(sotimeout);

            byte[] bn = new byte[32];
            int readcount=in.read(bn);
            //byte socketreceive []=java.util.Arrays.copyOf(bn,readcount);

            System.out.println("C: Received: '" +  "'");
            byte[] socketreceive = new byte[readcount];
            System.arraycopy(bn,0,socketreceive,0,readcount);
            if (readcount>=0){
                for (int i=0;i<readcount; i++){
                System.out.println(socketreceive[i]);
            }
                System.out.println("接受缓冲区数据数为："+ readcount);
                return socketreceive;
            }
            else {
                System.out.println("client is closing");
                 return null;
            }


          //  byte[] rn=new byte[j];
           // System.arraycopy(bn,0, rn,0,j);


            //return bn;
        }catch(IOException se){
            return null;
        }
    }

    /**
     * 判断是否断开连接，断开返回true,没有返回false
     * @param socket
     * @return
     */
    public Boolean isServerClose(Socket socket){
        try{
            socket.sendUrgentData(2);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        }catch(Exception se){
            return true;
        }
    }

    /** 关闭连接
     * @return
     * @throws IOException
     */
    public Boolean closesocket() throws IOException{
        try{
            this.out.close();
            this.in.close();
            this.socket.close();
            return true;

        }catch(IOException e){
            return false;
        }

    }

}
