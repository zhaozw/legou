package com.xqxy.lg.base;

import java.util.ArrayList;

import com.xqxy.lg.activity.product.Home;
import com.xqxy.lg.activity.product.ProductDetail;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;


//每秒刷新秒杀商品的倒计时时间
public class ChangeTime  implements Runnable{

	
	public static  ArrayList<Long> timeList;
	public static ArrayList<TextView> txtViewList;
	
	public static  long secKillTime=-1;
	public static boolean exit=true;
	public ChangeTime()
	{
		timeList=new ArrayList<Long>();
		txtViewList=new ArrayList<TextView>();
	}
	
	
	@Override
	public void run() {
		while(exit)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//秒杀商品详情页面倒计时文本框刷新
			if(secKillTime!=-1)
			{
				Bundle bundle=new Bundle();
				secKillTime--;
				String day = String.valueOf(secKillTime / 60 / 60 / 24);
				String hour = String.valueOf(secKillTime / 60 / 60 % 24);
				String min = String.valueOf(secKillTime / 60 % 60);
				String sec = String.valueOf(secKillTime % 60);
				String timeString = day + "天" + hour + "时" + min + "分" + sec + "秒";
				bundle.putLong("time", secKillTime);
				bundle.putString("timeString", timeString);
				if(ProductDetail.secKillHandler!=null)
				{
					Message msg=new Message();
					msg.setData(bundle);
					ProductDetail.secKillHandler.sendMessage(msg);
				}
			}
			
			//秒杀商品列表倒计时刷新
			if(timeList.size()>0)
			{
//				Log.i(MyApplication.TAG, "timeList-->"+timeList.size());
//				Log.i(MyApplication.TAG, "txtViewList-->"+txtViewList.size());
				for (int i = 0; i < timeList.size(); i++) {
					long time=timeList.get(i);
					Bundle bundle=new Bundle();
					if(time>0)
					{
						time--;
						String day = String.valueOf(time / 60 / 60 / 24);
						String hour = String.valueOf(time / 60 / 60 % 24);
						String min = String.valueOf(time / 60 % 60);
						String sec = String.valueOf(time % 60);
						String timeString = day + "天" + hour + "时" + min + "分" + sec + "秒";
						bundle.putLong("time", time);
						bundle.putString("timeString", timeString);
						bundle.putInt("index", i);
						timeList.remove(i);
						timeList.add(i, time);
						if(Home.homeHandler!=null)
						{
							Message msg=new Message();
							msg.setData(bundle);
							Home.homeHandler.sendMessage(msg);
						}
					}
				}
			}
			
		}
		
	}
	
	
	
}
