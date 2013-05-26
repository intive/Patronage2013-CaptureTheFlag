package com.blstream.ctf1.gps;

import android.location.GpsStatus;
import android.os.SystemClock;

/**
 * @author Adrian Swarcewicz
 */
class GpsStatusListener implements GpsStatus.Listener {

	private static long GPS_FIX_CHECK_IN_MILIS = 10000;
	GpsLocationListener mGpsLocationListener;
	boolean fix;

	public GpsStatusListener(GpsLocationListener gpsLocationListener) {
		this.mGpsLocationListener = gpsLocationListener;
	}

	@Override
	public void onGpsStatusChanged(int event) {
		if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS && isFixChange()) {
			if (fix == true) {
				mGpsLocationListener.onFoundFix();
			} else {
				mGpsLocationListener.onLostFix();
			}
		}
	}

	private boolean isFixChange() {
		boolean result = false;
		boolean currentFix = SystemClock.elapsedRealtime() - mGpsLocationListener.getLastLocationChange() < GPS_FIX_CHECK_IN_MILIS;
		if (currentFix == true && fix == false) {
			fix = true;
			result = true;
		} else if (currentFix == false && fix == true) {
			fix = false;
			result = true;
		}
		return result;
	}
}
