package app.openconnect.api;

import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.ErrorReporter;

import com.solid.vpn.R;
import com.solid.vpn.core.OpenVpnService;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;

public class GrantPermissionsActivity extends Activity {
	public static final String EXTRA_START_ACTIVITY = ".start_activity";
	public static final String EXTRA_UUID = ".UUID";

	private String mUUID;
	private String mStartActivity;

	private void reportBadRom(Exception e) {
		ACRAConfiguration cfg = ACRA.getConfig();
		cfg.setResDialogText(R.string.bad_rom_text);
		cfg.setResDialogCommentPrompt(R.string.bad_rom_comment_prompt);
		ACRA.setConfig(cfg);

		ErrorReporter er = ACRA.getErrorReporter();
		er.putCustomData("cause", "reportBadRom");
		er.handleException(e);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent myIntent = getIntent();
		mUUID = myIntent.getStringExtra(getPackageName() + EXTRA_UUID);
		if (mUUID == null) {
			finish();
			return;
		}
		mStartActivity = myIntent.getStringExtra(getPackageName() + EXTRA_START_ACTIVITY);

		Intent prepIntent;
		try {
			prepIntent = VpnService.prepare(this);
		} catch (Exception e) {
			reportBadRom(e);
			finish();
			return;
		}

		if (prepIntent != null) {
			try {
				startActivityForResult(prepIntent, 0);
			} catch (Exception e) {
				reportBadRom(e);
				finish();
				return;
			}
		} else {
			onActivityResult(0, RESULT_OK, null);
		}
	}

	/* Called by Android OS after user clicks "OK" on VpnService.prepare() dialog */ 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		setResult(resultCode);

		if (resultCode == RESULT_OK) {
	    	Intent intent = new Intent(getBaseContext(), OpenVpnService.class);
	    	intent.putExtra(OpenVpnService.EXTRA_UUID, mUUID);
	    	startService(intent);

//	    	if (mStartActivity != null) {
//	    		intent = new Intent();
//	    		intent.setClassName(this, mStartActivity);
//	    		startActivity(intent);
//	    	}
		}
		finish();
	}
}
