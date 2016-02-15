package com.my.mytest;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TestFragement extends Fragment{
	public static List<TestTask> mTests = new ArrayList<TestTask>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initTasks();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_main, container,false);
		Button bt = (Button)rootView.findViewById(R.id.begin_test);
		bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				initTasks();
				mTests.get(0).startTest();
			}
		});
		//LinearLayout lay = (LinearLayout)rootView.findViewById(R.id.test_layout);
		//lay.removeAllViews();
		//for(TestTask tt:mTests){
			//if(tt.getView().getParent()!=null)  ((ViewGroup)tt.getView().getParent()).removeView(tt.getView());
			//lay.addView(tt.getView());
			//Log.i("my","-----tt----");
		//}
		return rootView;
	}
	
	private void initTasks(){
		mTests.clear();
		((ViewGroup)getView().findViewById(R.id.test_layout)).removeAllViews();
		new TestSample(getActivity(),(ViewGroup)getView().findViewById(R.id.test_layout),"test 1.").inti();
		new TestSample(getActivity(),(ViewGroup)getView().findViewById(R.id.test_layout),"test 22").inti();
		new TestSample(getActivity(),(ViewGroup)getView().findViewById(R.id.test_layout),"test 333").inti();
		new TestSample(getActivity(),(ViewGroup)getView().findViewById(R.id.test_layout),"test 666").inti();
	}
}
