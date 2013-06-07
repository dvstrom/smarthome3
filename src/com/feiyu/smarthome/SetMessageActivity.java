package com.feiyu.smarthome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.hikvision.netsdk.*;

import org.MediaPlayer.PlayM4.*;


public class SetMessageActivity extends Activity{
	
	private ImageView home=null;
	private EditText id=null;
	private Button login=null;
	private int m_iLogID=-1;
	
	
	private NET_DVR_DEVICEINFO_V30 m_oNetDvrDeviceInfoV30 = null;
	private Player 			m_oPlayerSDK			= null;
	private HCNetSDK		m_oHCNetSDK				= null;
	private	NET_DVR_NETCFG_V30 NetCfg = new NET_DVR_NETCFG_V30();
	
	private final String 	TAG						= "DemoActivity";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        
        home=(ImageView)this.findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(SetMessageActivity.this, "OOOKKK", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent (SetMessageActivity.this,HomeActivity.class);	
				startActivity(intent);
				SetMessageActivity.this.finish();
			}
		});
        
        
        initeSdk();
        
        id=(EditText)this.findViewById(R.id.editText_oldPwd);
        login=(Button)this.findViewById(R.id.button_sure);
        
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 //TODO Auto-generated method stub
				Toast.makeText(SetMessageActivity.this,"这是第张图片",3).show();
				try
				{
					if(m_iLogID < 0)
					{
						// login on the device
						m_iLogID = loginDevice();
						if (m_iLogID < 0)
						{
							Log.e(TAG, "This device logins failed!");
							return;
						}
						// get instance of exception callback and set
						ExceptionCallBack oexceptionCbf = getExceptiongCbf();
						if (oexceptionCbf == null)
						{
						    Log.e(TAG, "ExceptionCallBack object is failed!");
						    return ;
						}
						
						if (!m_oHCNetSDK.NET_DVR_SetExceptionCallBack(oexceptionCbf))
					    {
					        Log.e(TAG, "NET_DVR_SetExceptionCallBack is failed!");
					        return;
					    }
						
						Log.i(TAG, "Login sucess ****************************1***************************");
					}
					else
					{
						// whether we have logout
						if (!m_oHCNetSDK.NET_DVR_Logout_V30(m_iLogID))
						{
							Log.e(TAG, " NET_DVR_Logout is failed!");
							return;
						}
						m_iLogID = -1;
					}		
				} 
				catch (Exception err)
				{
					Log.e(TAG, "error: " + err.toString());
			    }
			}
        });
	}
	private int loginDevice()
	{
		// get instance
		m_oNetDvrDeviceInfoV30 = new NET_DVR_DEVICEINFO_V30();
		if (null == m_oNetDvrDeviceInfoV30)
		{
			Log.e(TAG, "HKNetDvrDeviceInfoV30 new is failed!");
			return -1;
		}
	//	String strIP = m_oIPAddr.getText().toString();
//		int	nPort = Integer.parseInt(m_oPort.getText().toString());
//		String strUser = m_oUser.getText().toString();
//		String strPsd = m_oPsd.getText().toString();
		// call NET_DVR_Login_v30 to login on, port 8000 as default
		//int iLogID = m_oHCNetSDK.NET_DVR_Login_V30("192.168.1.150", 8000, "admin", "12345", m_oNetDvrDeviceInfoV30);
		int iLogID = m_oHCNetSDK.NET_DVR_Login_V30(id.getText().toString(), 8000, "admin", "12345", m_oNetDvrDeviceInfoV30);
		if (iLogID < 0)
		{
			Log.e(TAG, "NET_DVR_Login is failed!Err:" + m_oHCNetSDK.NET_DVR_GetLastError());
			Log.e(TAG, "NET_DVR_Login is failed!Err:++++没上啊");
			return -1;
		}
		
		Log.i(TAG, "NET_DVR_Login is Successful!");
		
		return iLogID;
	}
	
    private boolean initeSdk()
	{
		// get an instance and init net sdk
		m_oHCNetSDK = new HCNetSDK();
    	if (null == m_oHCNetSDK)
    	{
    		Log.e(TAG, "m_oHCNetSDK new is failed!");
    		return false;
    	}
    	
    	if (!m_oHCNetSDK.NET_DVR_Init())
    	{
    		Log.e(TAG, "HCNetSDK init is failed!");
    		return false;
    	}
    	
    	// init player
    	m_oPlayerSDK = Player.getInstance();
    	if (m_oPlayerSDK == null)
    	{
    		Log.e(TAG,"PlayCtrl getInstance failed!");
    		return false;
    	}
    	
    	return true;
	}
	private ExceptionCallBack getExceptiongCbf()
	{
	    ExceptionCallBack oExceptionCbf = new ExceptionCallBack()
        {
            public void fExceptionCallBack(int iType, int iUserID, int iHandle)
            {
            	;// you can add process here
            }
        };
        return oExceptionCbf;
	}
}