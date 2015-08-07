package com.meitwo.menudrawer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2015/8/4.
 */
public class SaoMiaoActivity extends Activity {

    private Button mScanBtn;
    private ProgressDialog mProgress;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saomiaolayout);
        Button saomiao=(Button)this.findViewById(R.id.saomiao);
        saomiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaoMiaoActivity.this, GeQu.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }



    public void onClick(View v) {
        if(v == mScanBtn) {
            mProgress.setMessage("ÕýÔÚÉ¨Ãè¸èÇú£¬ÇëÎðÍË³öÈí¼þ£¡");
            mProgress.setCancelable(false);
            mProgress.setCanceledOnTouchOutside(false);
            mProgress.show();
        }
    }
    }
