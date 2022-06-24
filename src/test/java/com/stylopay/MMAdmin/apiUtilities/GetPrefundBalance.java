package com.stylopay.MMAdmin.apiUtilities;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.stylopay.MMAdmin.genericUtilities.ReadAPIUrlFromPropertiesFile;

public class GetPrefundBalance {

	public static String getDashbrdPrefundBal() throws UnirestException, IOException {

		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest
				.get(ReadAPIUrlFromPropertiesFile.readAPIUrlFromPropertiesFile().getProperty("GetPrefundBalanceUrl"))
				.asString();		
		
		JSONArray jsonResponseArray = new JSONArray(response.getBody().toString());		
		String currentBal = jsonResponseArray.getJSONObject(0).getString("cur_balance");
								
		return currentBal;
	}

}
