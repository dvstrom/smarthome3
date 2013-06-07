package com.feiyu.smarthome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class AeraImageAdapter extends BaseAdapter {
	private ImageView[] mImages;		// ���浹ӰͼƬ������

	private Context mContext;
	public List<Map<String, Object>> list;
	

	public Integer[] imgs = { R.drawable.room_gallery_0, R.drawable.room_gallery_1, R.drawable.room_gallery_2,
							  R.drawable.room_gallery_3, R.drawable.room_gallery_4, R.drawable.room_gallery_5,
							  R.drawable.room_gallery_6, R.drawable.room_gallery_7, R.drawable.room_gallery_8,
							  R.drawable.room_gallery_9, R.drawable.room_gallery_10, R.drawable.room_gallery_11, 
							  R.drawable.room_gallery_12, R.drawable.room_gallery_13};
	public String[] titles = {"����1","����2","����3","����4","����5","����6","����7","��8","����10","����11","����12","����13","����14"};

	public AeraImageAdapter(Context c) {
		this.mContext = c;
		list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imgs.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("image", imgs[i]);
			list.add(map);
		}
		mImages = new ImageView[list.size()];
	}

	/** ���䵹Ӱ */
	public boolean createReflectedImages() {
		//final int reflectionGap = 4;
		final int Height = 160;
		int index = 0;
		for (Map<String, Object> map : list) {
			Integer id = (Integer) map.get("image");
			Bitmap originalImage = BitmapFactory.decodeResource(mContext.getResources(), id);	// ��ȡԭʼͼƬ
			int width = originalImage.getWidth();
			int height = originalImage.getHeight();
			float scale = Height / (float)height;
			
			Matrix sMatrix = new Matrix();
			sMatrix.postScale(scale, scale);
			Bitmap miniBitmap = Bitmap.createBitmap(originalImage, 0, 0,
					originalImage.getWidth(), originalImage.getHeight(), sMatrix, true);

			int mwidth = miniBitmap.getWidth();
			int mheight = miniBitmap.getHeight();
			Matrix matrix = new Matrix();
			matrix.preScale(1, -1);			// ͼƬ����任���ӵͲ��򶥲��ĵ�Ӱ��
			//Bitmap reflectionImage = Bitmap.createBitmap(miniBitmap, 0, mheight/2, mwidth, mheight/2, matrix, false);	// ��ȡԭͼ�°벿��
			Bitmap bitmapWithReflection = Bitmap.createBitmap(mwidth, mheight, Config.ARGB_8888);//Bitmap bitmapWithReflection = Bitmap.createBitmap(mwidth, (mheight + mheight / 2), Config.ARGB_8888);			// ������ӰͼƬ���߶�Ϊԭͼ3/2��

			Canvas canvas = new Canvas(bitmapWithReflection);	// ���Ƶ�Ӱͼ��ԭͼ + ��� + ��Ӱ��
			canvas.drawBitmap(miniBitmap, 0, 0, null);		// ����ԭͼ
			Paint paint = new Paint();
			//canvas.drawRect(0, mheight, mwidth, mheight + reflectionGap, paint);		// ����ԭͼ�뵹Ӱ�ļ��
			//canvas.drawBitmap(reflectionImage, 0, mheight + reflectionGap, null);	// ���Ƶ�Ӱͼ

			//paint = new Paint();
			//LinearGradient shader = new LinearGradient(0, miniBitmap.getHeight(), 0, bitmapWithReflection.getHeight()
					//+ reflectionGap, 0x70ffffff, 0x00ffffff, TileMode.CLAMP);
			//paint.setShader(shader);	// ���Խ���Ч��
			//paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));		// ��Ӱ����Ч��
			//canvas.drawRect(0, mheight, mwidth, bitmapWithReflection.getHeight() + reflectionGap, paint);		// ���Ƶ�Ӱ����ӰЧ��

			ImageView imageView = new ImageView(mContext);
			imageView.setImageBitmap(bitmapWithReflection);		// ���õ�ӰͼƬ
			imageView.setLayoutParams(new GalleryView.LayoutParams((int)(width * scale),
					(int)(mheight)));//(int)(mheight * 3 / 2.0 + reflectionGap)));
			imageView.setScaleType(ScaleType.MATRIX);
			mImages[index++] = imageView;
		}
		return true;
	}

	@Override
	public int getCount() {
		return imgs.length;
	}

	@Override
	public Object getItem(int position) {
		return mImages[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return mImages[position];		// ��ʾ��ӰͼƬ����ǰ��ȡ���㣩
	}

	public float getScale(boolean focused, int offset) {
		return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
	}

}
