package com.feiyu.smarthome;

/*import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;

public class LogoActivity extends Activity{

	ImageView imageView_logo=null;
	int []image={R.drawable.op_001,R.drawable.op_002,R.drawable.op_003,R.drawable.op_004,R.drawable.op_005};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		imageView_logo=(ImageView)this.findViewById(R.id.imageView_logo);
		
			new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){
			Intent intent = new Intent (LogoActivity.this,LoginActivity.class);
			startActivity(intent);			
			LogoActivity.this.finish();
		}
	}, 1000);
		
	}

}*/
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import com.feiyu.Sevice.SmartService;

public class LogoActivity extends Activity {
	private ImageView logo;
	private Handler handler;
	private Thread clockThread;
	private boolean isRunning=true;
    public static  SmartService mService;
    public static boolean mBound = false;
    public static SmartService.LocalBinder binder;

	private int[] bg=new int[]{
			R.drawable.op_001,R.drawable.op_002,R.drawable.op_003,
			R.drawable.op_004,R.drawable.op_005
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		logo=(ImageView)findViewById(R.id.imageView_logo);

        Intent intent = new Intent(this, SmartService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

		handler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.what){
				case 0:logo.setBackgroundResource(bg[(Integer) msg.obj]);
				}
			}
		};
		clockThread=new Thread(){
			@Override
			public void run(){
				int timer=0;
				while(isRunning){
					try{
						Thread.currentThread().sleep(1000);
						
						if(timer==4){
							isRunning=false;
							Intent intent = new Intent (LogoActivity.this,LoginActivity.class);
							startActivity(intent);			
							LogoActivity.this.finish();
						}	
						Message msg=new Message();
						msg.obj=timer;
						msg.what=0;
						handler.sendMessage(msg);
						timer++;
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};
		clockThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logo, menu);
		return true;
	}
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.

    }

    @Override
    protected void onDestroy() {
            //To change body of overridden methods use File | Settings | File Templates.
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
            super.onDestroy();
        }
    }


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // 已经绑定了LocalService，强转IBinder对象，调用方法得到LocalService对象
            binder = (SmartService.LocalBinder)service;
            mService=binder.getService();
            mBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };
}
