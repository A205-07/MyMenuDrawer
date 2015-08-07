package com.meitwo.menudrawer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.meitwo.menudrawer.LocusPassWordView.OnCompleteListener;

public class LoginActivity extends Activity
{
	private LocusPassWordView lpwv;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		setTitle("ϵͳ��¼");
		lpwv = (LocusPassWordView) this.findViewById(R.id.mLocusPassWordView);
		lpwv.setOnCompleteListener(new OnCompleteListener()
		{
			@Override
			public void onComplete(String mPassword)
			{
				//���������ȷ,�������ҳ�档
				if (lpwv.verifyPassword(mPassword))
				{
					Toast.makeText(LoginActivity.this, "��½�ɹ���", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(LoginActivity.this, MyMenuDrawer.class);
					// ���µ�Activity
					startActivity(intent);
					LoginActivity.this.finish();
				}
				else
				{
					Toast.makeText(LoginActivity.this, "�����������,����������", Toast.LENGTH_SHORT).show();
					lpwv.clearPassword();
				}
			}
		});

	}

	@Override
	protected void onStart()
	{
		super.onStart();
		// �������Ϊ��,�������������Ľ���
		View noSetPassword = (View) this.findViewById(R.id.tvNoSetPassword);
		if (lpwv.isPasswordEmpty())
		{
			noSetPassword.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					Intent intent = new Intent(LoginActivity.this, SetPasswordActivity.class);
					// ���µ�Activity
					startActivity(intent);
				}

			});
			noSetPassword.setVisibility(View.VISIBLE);
		}
		else
		{
			noSetPassword.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onStop()
	{
		super.onStop();
	}

}