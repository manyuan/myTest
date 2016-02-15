package com.my.mytest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class TestTask extends AsyncTask<String, Integer, Integer>{
	public final static int TEST_RESULT_OK = 1;
	public final static int TEST_RESULT_FAIL = 2;

	private View mView;
	private String mTestInfo = "";
	private Context mContext;
	TestTask(Context cntx,ViewGroup rootview,String title){
		mContext = cntx;
		LayoutInflater lay = (LayoutInflater)cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = lay.inflate(R.layout.test_item_view, null);
		((TextView)mView.findViewById(R.id.item_title)).setText(title);
		((TextView)mView.findViewById(R.id.item_title)).setClickable(true);
		((TextView)mView.findViewById(R.id.item_title)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(mTestInfo);
			}
		});
		rootview.addView(mView);
		rootview.invalidate();
	}
	
	@Override  
    protected void onPreExecute() {
        Log.i("my", "onPreExecute() called");
        ((TextView)mView.findViewById(R.id.item_result)).setText("Testting...");
        mView.setBackgroundColor(0xFF00FFFF);
    }
	
	
	@Override  
    protected void onProgressUpdate(Integer... progresses) {
        Log.i("my", "onProgressUpdate(Progress... progresses) called");
    }
    @Override  
    protected void onPostExecute(Integer result) {
        Log.i("my", "onPostExecute(Result result) called, result:"+result);
        
        if(result.equals(TEST_RESULT_OK)){
        	 ((TextView)mView.findViewById(R.id.item_result)).setText("Test ok.");
             ((TextView)mView.findViewById(R.id.item_result)).setBackgroundColor(0xFF00FF00);
             mView.setBackgroundColor(0xFFFFFFFF);
             int index = TestFragement.mTests.indexOf(this);
             if(index <0) return;
             if(TestFragement.mTests.size() > (index+1)){
             	TestFragement.mTests.get(index+1).startTest();
             }
        }else if(result.equals(TEST_RESULT_FAIL)){
        	((TextView)mView.findViewById(R.id.item_result)).setText("Test fail.");
            ((TextView)mView.findViewById(R.id.item_result)).setBackgroundColor(0xFFFF0000);
            mView.setBackgroundColor(0xFFFFFFFF);
        }
       
    }
    @Override  
    protected void onCancelled() {
        Log.i("my", "onCancelled() called");
    }
	
	public View getView(){
		return mView;
	}
	
	public void startTest(){
		execute(null,null,null);
	}
	public void stopTest(){
		cancel(true);
	}
	public void inti(){
		TestFragement.mTests.add(this);
	}
	public void setinfo(String info){
		mTestInfo += info + "\n";
	}
	public void showDialog(String msg){
		 AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		 builder.setMessage(msg);
		 builder.setTitle("提示");
		 builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			}
		 });
		/*builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			}
		});*/
		builder.create().show();
	}

}
