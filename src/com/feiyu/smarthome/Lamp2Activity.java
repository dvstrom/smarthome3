package com.feiyu.smarthome;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


/**
 * 启动的主界面
 * @author maylian7700@126.com
 *
 */
public class Lamp2Activity extends Activity implements OnSeekBarChangeListener {
	private ToneLayer mToneLayer;
	private ImageView mImageView;
	private Bitmap mBitmap;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lamp2);
        
        init();
    }
    
    private void init()
    {
    	mToneLayer = new ToneLayer(this);
        
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.device_dimen_normal);
        mImageView = (ImageView) findViewById(R.id.img_view);
        mImageView.setImageBitmap(mBitmap);
        ((LinearLayout) findViewById(R.id.tone_view)).addView(mToneLayer.getParentView());
        Drawable progressDrawable= this.getResources().getDrawable(R.drawable.seekbar_define_color); 
        //progressDrawable.setBounds(0, 5, 806, 30);
        ArrayList<SeekBar> seekBars = mToneLayer.getSeekBars();
        for (int i = 0, size = seekBars.size(); i < size; i++)
        {
        	seekBars.get(i).setOnSeekBarChangeListener(this);
        	seekBars.get(i).setProgressDrawable(progressDrawable);
        }
    }

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		int flag = (Integer) seekBar.getTag();
		switch (flag)
		{
		case ToneLayer.FLAG_SATURATION:
			mToneLayer.setSaturation(progress);
			break;
		case ToneLayer.FLAG_LUM:
			mToneLayer.setLum(progress);
			break;
		case ToneLayer.FLAG_HUE:
			mToneLayer.setHue(progress);
			break;
		}
		
		mImageView.setImageBitmap(mToneLayer.handleImage(mBitmap, flag));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}
}