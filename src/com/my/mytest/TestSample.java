package com.my.mytest;

import android.content.Context;
import android.view.ViewGroup;

public class TestSample extends TestTask{

	TestSample(Context cntx,ViewGroup rootview,String title) {
		super(cntx,rootview,title);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected Integer doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		setinfo("heihei...2..");
		try{Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		setinfo("heihei...1..");
		return TestTask.TEST_RESULT_OK;
	}

}
