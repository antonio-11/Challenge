package com.gbm.invest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class TestOperationError {

	private String resposponseString;
	private String payloadString;
	private static final String urlApi = "http://localhost:8080/v2/invest";
	
	@Test
	public void testInsufficientBalance() throws IOException {	
		dataTestInsufficientBalance();
		String response = getResponse();
        System.out.println( response );
        assertEquals(resposponseString, response, "todo bien");
	}
	
	@Test
	public void testInsufficientStock() throws IOException {	
		dataTestInsufficientStock();
		String response = getResponse();
        System.out.println( response );
        assertEquals(resposponseString, response, "todo bien");
	}
	
	@Test
	public void testMarketClose() throws IOException {	
		dataTestMarketClose();
		String response = getResponse();
        System.out.println( response );
        assertEquals(resposponseString, response, "todo bien");
	}
	
	@Test
	public void testDuplicatedOperation() throws IOException {	
		dataTestDuplicated();
		String response = getResponse();
        System.out.println( response );
        assertEquals(resposponseString, response, "todo bien");
	}
	
	@Test
	public void testInvalidOperation() throws IOException {	
		dataTestInvalidOperation();
		String response = getResponse();
        System.out.println( response );
        assertEquals(resposponseString, response, "todo bien");
	}
	
	private String getResponse() {
		StringBuffer jsonString = new StringBuffer();
		String requestUrl = urlApi;
		String payload = payloadString;
		try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(payload);
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                jsonString.append(line);
            }
            br.close();
            connection.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
		return jsonString.toString();
	}
	
	private void dataTestInsufficientBalance() {	
		payloadString = "{\"initialBalances\": {\"cash\": 10, \"issuers\": []},\"orders\":[{\"timestamp\": 1571325625, \"operation\": \"BUY\", \"IssuerName\": \"GBM\", \"TotalShares\": 5, \"SharePrice\": 100}]}";
		resposponseString = "{\"currentBalance\":{\"cash\":10.0,\"issuers\":[]},\"bussinessErrors\":[\"INSUFFICIENT_BALANCE\"]}";
	}
	
	private void dataTestInsufficientStock() {	
		payloadString = "{\"initialBalances\": {\"cash\": 1000, \"issuers\": [{\"issuerName\": \"GBM\", \"totalShares\": 10, \"sharePrice\": 10}]},\"orders\":[{\"timestamp\": 1571325625, \"operation\": \"SELL\", \"IssuerName\": \"GBM\", \"TotalShares\": 100, \"SharePrice\": 10}]}";
		resposponseString = "{\"currentBalance\":{\"cash\":1000.0,\"issuers\":[{\"issuerName\":\"GBM\",\"totalShares\":10,\"sharePrice\":10.0}]},\"bussinessErrors\":[\"INSUFFICIENT_STOCKS\"]}";
	}
	
	private void dataTestMarketClose() {	
		payloadString = "{\"initialBalances\": {\"cash\": 1000, \"issuers\": []}, \"orders\":[{\"timestamp\": 1571350755, \"operation\": \"BUY\", \"IssuerName\": \"GBM\", \"TotalShares\": 5, \"SharePrice\": 100}]}";
		resposponseString = "{\"currentBalance\":{\"cash\":1000.0,\"issuers\":[]},\"bussinessErrors\":[\"INVALID_OPERATION\"]}";
	}
	
	private void dataTestDuplicated() {	
		payloadString = "{\"initialBalances\": {\"cash\": 1000, \"issuers\": [{\"issuerName\": \"GBM\", \"totalShares\": 100, \"sharePrice\": 10}]}"
								+ ",\"orders\":[{\"timestamp\": 1571325625,\"operation\": \"SELL\", \"IssuerName\": \"GBM\", \"TotalShares\": 10, \"SharePrice\": 10}"
							 	            + ",{\"timestamp\": 1571325627, \"operation\": \"SELL\", \"IssuerName\": \"GBM\", \"TotalShares\": 10, \"SharePrice\": 10}]}";
		resposponseString = "{\"currentBalance\":{\"cash\":1100.0,\"issuers\":[{\"issuerName\":\"GBM\",\"totalShares\":90,\"sharePrice\":10.0}]},\"bussinessErrors\":[\"DUPLICATED_OPERATION\"]}";
	}
	
	private void dataTestInvalidOperation() {	
		payloadString = "{\"initialBalances\": {\"cash\": 1000, \"issuers\": []},\"orders\":[{\"timestamp\": 1571325625, \"operation\": \"BUY\", \"IssuerName\": \"GBM\", \"TotalShares\": -5, \"SharePrice\": 100}]}";
		resposponseString = "{\"currentBalance\":{\"cash\":1000.0,\"issuers\":[]},\"bussinessErrors\":[\"INVALID_OPERATION\"]}";
	}

}
