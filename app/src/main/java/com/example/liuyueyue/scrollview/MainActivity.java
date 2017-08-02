package com.example.liuyueyue.scrollview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
        private TextView tv;
    private ScrollView scroll;
    private Button up_btn,down_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //文本内容
        tv = (TextView) findViewById(R.id.content);
        tv.setText(getResources().getString(R.string.content));
        //滚动条
        scroll = (ScrollView) findViewById(R.id.scroll);
        //控制按钮初始化
        up_btn= (Button) findViewById(R.id.up);
        down_btn = (Button) findViewById(R.id.down);
        //实现接口
        up_btn.setOnClickListener(this);
        down_btn.setOnClickListener(this);

        scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               switch (event.getAction()){
                   case MotionEvent.ACTION_MOVE:
                   {
                       /*
                       * 1.getScrollY()--滚动条滑动的距离
                       * 2.getMeasuredHeight()
                       * 3.getHeight()*/
                       //顶部状态
                       if(scroll.getScaleY()<=0){
                          Log.i("Main","滑动到顶部");
                       }
                       //底部状态
                       //TextView的总高度<=屏幕的高度+滚动条的滚动距离
                       if(scroll.getChildAt(0).getMeasuredHeight()
                               <=scroll.getHeight()+scroll.getScrollY()){
                           Log.i("Main","滑动到底部");
                           Log.i("Main",
                                   "scroll.getChildAt(0).getMeasuredHeight()=" +scroll.getChildAt(0).getMeasuredHeight()+
                                   "scroll.getHeight()="+scroll.getHeight()+
                                   "scroll.getScrollY()="+scroll.getScrollY());
                       tv.append(getResources().getString(R.string.content));
                       }
                       break;
                   }
               }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        //scrollTo:以滚动视图起始位置开始计算
        //scrollBy:以相对前一次的位置去滚动
        switch (v.getId()){
            case R.id.up:{
                scroll.scrollBy(0,-100);
                break;
            }
            case R.id.down:{
                scroll.scrollBy(0,100);
                break;
            }
        }
    }
}
