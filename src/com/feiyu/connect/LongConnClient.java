package com.feiyu.connect;

/**�ͻ��˳�����k
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-17
 * Time: ����11:07
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
 * socket ��������
 * @author yf
 * @version 1.0
 */
/*
 * @author Administrator
 *
 */
public class LongConnClient extends Thread {

    public String ip=null;//���ӷ�������IP
    public Integer port=null;//���ӷ������Ķ˿�
    private Socket socket=null;//�׽��ֶ���
    private boolean close = false; // �ر����ӱ�־λ��true��ʾ�رգ�false��ʾ����
    private Integer sotimeout=1*1*10;//��ʱʱ�䣬�Ժ���Ϊ��λ
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
     * ��ʼ��socket����
     */
    public void init(){
        try {
            InetAddress address = InetAddress.getByName(getIp());
            socket = new Socket(address,getPort());
            socket.setKeepAlive(true);//�������ֻ״̬���׽���
            socket.setSoTimeout(sotimeout);//���ó�ʱʱ��
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //this.bos=new ByteArrayOutputStream();
            out=socket.getOutputStream();
            in=socket.getInputStream();
            close=!Send(socket,"2");//���ͳ�ʼ���ݣ����ͳɹ����ʾ�Ѿ������ϣ�����ʧ�ܱ�ʾ�Ѿ��Ͽ�
        }catch(UnknownHostException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * �������߳�
     */
    public void run() {
        while(true){
            //---------������---------------------------
            close = isServerClose(socket);//�ж��Ƿ�Ͽ�
            if(!close){//û�жϿ�����ʼ������
                byte[] receive = ReadByte(socket);
                if(receive!=null && receive.length>0){
                    for (int i=0;i<receive.length; i++){
                        System.out.println(receive[i]);
                    }
                }
            }
            //---------��������-------------------------
            while(close){//�Ѿ��Ͽ������½�������
                try{
                    System.out.println("���½������ӣ�"+getIp()+":"+getPort());
                    InetAddress address = InetAddress.getByName(getIp());
                    socket = new Socket(address,getPort());
                    socket.setKeepAlive(true);
                    socket.setSoTimeout(sotimeout);
                    close = !Send(socket,"2");
                    System.out.println("�������ӳɹ���"+getIp()+":"+getPort());
                }catch(Exception se){
                    System.out.println("��������ʧ��:"+getIp()+":"+getPort());
                    close=true;
                }
            }
        }
    }


    /**
     * �������ݣ�����ʧ�ܷ���false,���ͳɹ�����true
     * @param csocket
     * @param message
     * @return
     */
    public Boolean Send(Socket csocket,ByteArrayOutputStream bos){
        try{
            System.out.println("C: sending...");
            for (byte bs:bos.toByteArray()){
                System.out.println(bs);
            }
            System.out.println("���͵����ݳ���Ϊ��"+ bos.size());
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
     * ��ȡ���ݣ������ַ�������
     * @param csocket
     * @return
     */
    public byte[] ReadByte(Socket csocket){
        try{
            csocket.setSoTimeout(sotimeout);

            byte[] bn = new byte[1024];
            int j=in.read(bn);
            System.out.println("C: Received: '" +  "'");
            for (int i=0;i<j; i++){
                System.out.println(bn[i]);
            }
            System.out.println("����������Ϊ��"+ j);


            byte[] rn=new byte[j];
            System.arraycopy(bn,0, rn,0,j);

            return rn;
        }catch(IOException se){
            return null;
        }
    }

    /**
     * �ж��Ƿ�Ͽ����ӣ��Ͽ�����true,û�з���false
     * @param socket
     * @return
     */
    public Boolean isServerClose(Socket socket){
        try{
            socket.sendUrgentData(0);//����1���ֽڵĽ������ݣ�Ĭ������£���������û�п����������ݴ�����Ӱ������ͨ��
            return false;
        }catch(Exception se){
            return true;
        }
    }

    /** �ر�����
     * @return
     * @throws IOException
     */
    public Boolean closesocket() throws IOException{
        try{
            this.socket.close();
            return true;
        }catch(IOException e){
            return false;
        }

    }
}