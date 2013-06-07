package com.feiyu.util;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-5-17
 * Time: ����10:45
 * To change this template use File | Settings | File Templates.
 */
public class Const {
    /**************************��ͷ����****************************/
    public static final int MessageHeaderLength = 5;
    /*******************************All device***************************/
    public static final int ALL_REQUEST=0X00;
    /******************************************************************

     /********************************* Temperature ************************/
    public static final int TEMP_REQUEST = 0x1;
    /********************************* Temperature ************************/

    /********************************* humidity************************/
    public static final int HUMIDITY_REQUEST =0x2;
    /********************************* humidity ************************/

    /********************************* Gas ************************/
    public static final int GAS_REQUEST = 0x3;
    /********************************* Gas ************************/

    /********************************* PM ************************/
    public static final int PM_REQUEST = 0x4;
    /********************************* PM************************/

    /********************************* smoke transducer *********************/
    public static final int SMOKE_REQUEST = 0x5;
    /********************************* smoke transducer *******************/

    /********************************* Light sensor ************************/
    public static final int Lightsensor_REQUEST = 0x6;
    /********************************* Light sensor ************************/

    /********************************* step motor ************************/
    // public static final int MOTOR_REQUEST = 0x7;
    /********************************* step motor ************************/

    /*********************************  ELECTRIC CURRENT SENSOR  ************************/
    public static final int ELECTRIC_REQUEST = 0x8;
    /*********************************  ELECTRIC CURRENT SENSOR  ************************/

    /*********************************  DEVICE COMMON COMMAND  ************************/
    public static final int DEVICE_ON=0xFF;
    public static final int DEVICE_OFF=0x00;
    public static final int DEVICE_RESTART=0xF0;

    /*********************************  DEVICE COMMON COMMAND  ************************/


    /*********************************  JACK����Դ��  ************************/
    public static final int JACK_REQUEST=0x21;
    /*********************************  JACK  ************************/

    /********************************* ON-OFF(���أ�  ************************/
    public static  final int ONOFF_REQUEST=0x22;
    /********************************* ON-OFF  ************************/

    /*********************************TV  ************************/
    public static  final int TV_REQUEST=0x23;
    public static  final int CHANGETV_CHANNAL_UP=0X01;
    public static  final int CHANGETV_CHANNAL_DOWN=0X02;
    public static  final int SELECTTV_CHANNAL=0X05;
    public static  final int CHANGETV_VOICE_UP=0X03;
    public static  final int CHANGETV_VOICE_DOWN=0X04;
    /*********************************TV  ************************/
    /********************************* air conditioner��AC-�յ���)  ************************/
    public static  final int AC_REQUEST=0X24;
    public static  final int SELECTAC_MODE=0X01;  //AC-command
    public static  final int AC_REFRIGERATION_MODE =0X01;     //����
    public static  final int AC_HEATING_MODE =0X02;
    /********************************* air conditioner  ************************/

    /********************************* acoustic (���죩 ************************/
    public static final int ACOUSTIC_REQUEST=0X25;
    /********************************* acoustic  ************************/

    /*********************************LAMP  ************************/
    public static final int LAMP_REQUEST=0X26;
    public static final int STRENGTHLAMP_UP=0X01;
    public static final int STRENGTHLAMP_DOWN=0X02;
    /*********************************LAMP  ************************/

    /*********************************MOTOR ************************/
    public static final int MOTOR_REQUEST=0X27;
    public static final int MOTOR_SPEED=0X01;
    public static final int MOTOR_NORMAL=0X02;   //����
    public static final int MOTOR_REVERSE=0X03;  //��
    /*********************************MOTOR ************************/

    /*********************************PROJECTOR(ͶӰ�ǣ� ************************/
    public static final int PROJECTOR_REQUEST=0x28;

    /*********************************PROJECTORͶӰ�ǣ� ************************/

    /*********************************DOOR ************************/
    public static final byte DOOR_REQUEST=(BytearrayBuilder.IntToByte(29));
    public static final byte DOOR_OPEN=(BytearrayBuilder.IntToByte(0x01));
    public static final byte DOOR_CLOSE=(BytearrayBuilder.IntToByte(0x02));

}                                                                                    /*********************************PROJECTOR(ͶӰ�ǣ� ************************/


