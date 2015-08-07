package com.meitwo.menudrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.meitwo.menudrawer.LocusPassWordView.OnCompleteListener;
import com.meitwo.menudrawer.StringUtil;

public class SetPasswordActivity extends Activity
{
	private LocusPassWordView lpwv;
	private String password;
	private boolean needverify = true;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setpassword_activity);
		setTitle("��������");
		lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);
		lpwv.setOnCompleteListener(new OnCompleteListener()
		{
			@Override
			public void onComplete(String mPassword)
			{
				password = mPassword;
				if (needverify)
				{
					if (lpwv.verifyPassword(mPassword))
					{
						showDialog("����������ȷ,������������!");
						needverify = false;
					}
					else
					{
						showDialog("���������,����������!");
						password = "";
					}
				}
			}
		});

		OnClickListener mOnClickListener = new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				switch (v.getId())
				{
					case R.id.tvSave:
					   if (StringUtil.isNotEmpty(password))
					   {
						   lpwv.resetPassWord(password);
						   showDialog("�����޸ĳɹ�,���ס����.");

				    	}
					   else
					   {
						   showDialog("���벻��Ϊ��,����������.");
					   }
					break;
					case R.id.tvReset:
					   lpwv.clearPassword();
					   break;
					case R.id.Reset:
						showDialog("���ص�¼����");
					   Intent intent=new Intent(SetPasswordActivity.this,LoginActivity.class);
						// ���µ�Activity
						startActivity(intent);
						SetPasswordActivity.this.finish();
					   break;


				}
			}
		};
		TextView buttonSave = (TextView) this.findViewById(R.id.tvSave);
		buttonSave.setOnClickListener(mOnClickListener);
		TextView tvReset = (TextView) this.findViewById(R.id.tvReset);
		tvReset.setOnClickListener(mOnClickListener);
		TextView tbReset = (TextView) this.findViewById(R.id.Reset);
		tbReset.setOnClickListener(mOnClickListener);
		// �������Ϊ��,ֱ����������
		if (lpwv.isPasswordEmpty())
		{
			this.needverify = false;
			Toast.makeText(SetPasswordActivity.this, "����������", Toast.LENGTH_SHORT).show();
		}
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

	private void showDialog(String title)
	{
		Toast.makeText(SetPasswordActivity.this, title, Toast.LENGTH_SHORT).show();
	}
}
