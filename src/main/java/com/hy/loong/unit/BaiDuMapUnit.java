package com.hy.loong.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

public class BaiDuMapUnit {
	/**
	 * 输入地址返回经纬度坐标 key lng(经度),lat(纬度)
	 */
	public static List<String> getGeocoderLatitude(String address) {
		List<String> list = Lists.newArrayList();
		BufferedReader in = null;
		try {
			address = URLEncoder.encode(address, "UTF-8");
			URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak="
					+ "wws9Qu73jw4QkOL6osEyIsA9Yob2yYgR"+"&callback=showLocation");
			in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			String str = sb.toString();
			if (!StringUtils.isEmpty(str)) {
				int lngStart = str.indexOf("lng\":");
				int lngEnd = str.indexOf(",\"lat");
				int latEnd = str.indexOf("},\"precise");
				if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
					String lng = str.substring(lngStart + 5, lngEnd);
					String lat = str.substring(lngEnd + 7, latEnd);
					list.add(lng);
					list.add(lat);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 输入经纬度返回地址 key lng(经度),lat(纬度)
	 */
	public static String getPosition(String latitude, String longitude) throws MalformedURLException {
		String ads = null;
		BufferedReader in = null;
		URL tirc = new URL("http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + latitude + "," + longitude
				+ "&output=json&pois=1&ak=" + "wws9Qu73jw4QkOL6osEyIsA9Yob2yYgR");
		try {
			in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			String str = sb.toString();
			if (!StringUtils.isEmpty(str)) {
				int lngStart = str.indexOf("formatted_address\":\"");
				int lngEnd = str.indexOf("\",\"business");
				if (lngStart > 0 && lngEnd > 0 ) {
					ads = str.substring(lngStart + 20, lngEnd);
				}
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ads;
	}
}
