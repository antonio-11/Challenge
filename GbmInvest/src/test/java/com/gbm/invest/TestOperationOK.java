package com.gbm.invest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;

public class TestOperationOK {

	private String resposponseString;
	private String payloadString;
	private static final String urlApi = "http://localhost:8080/v2/invest";
	
	@Test
	public void testBuyOK() throws IOException {	
		dataTestBuyOK();
		String response = getResponse();
        System.out.println( response );
        assertEquals(resposponseString, response, "todo bien");
	}
	
	@Test
	public void testSellOK() throws IOException {	
		dataTestSellOK();
		String response = getResponse();
        System.out.println( response );
        assertEquals(resposponseString, response, "todo bien");
	}
	
	@Test
	public void testSellAndBuyOK() throws IOException {	
		dataTestSellAndBuyOK();
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
	
	private void dataTestBuyOK() {	
		payloadString = "{\"initialBalances\":{\"cash\":100,\"issuers\":[{\"issuerName\":\"GBM\",\"totalShares\":10,\"sharePrice\":10}]}"
					  + ",\"orders\":[{\"timestamp\":\"1571340125\",\"operation\":\"BUY\",\"IssuerName\":\"MMM\",\"TotalShares\":3,\"SharePrice\":10}]}";
		resposponseString = "{\"currentBalance\":{\"cash\":70.0,\"issuers\":["
				    + "{\"issuerName\":\"GBM\",\"totalShares\":10,\"sharePrice\":10.0}"
				   + ",{\"issuerName\":\"MMM\",\"totalShares\":3,\"sharePrice\":10.0}]},\"bussinessErrors\":[]}";
	}
	
	private void dataTestSellOK() {	
		payloadString = "{\"initialBalances\":{\"cash\":100,\"issuers\":[{\"issuerName\":\"GBM\",\"totalShares\":10,\"sharePrice\":10}]}"
				  + ",\"orders\":[{\"timestamp\":\"1571340125\",\"operation\":\"SELL\",\"IssuerName\":\"GBM\",\"TotalShares\":3,\"SharePrice\":10}]}";
		resposponseString = "{\"currentBalance\":{\"cash\":130.0,\"issuers\":[{\"issuerName\":\"GBM\",\"totalShares\":7,\"sharePrice\":10.0}]},\"bussinessErrors\":[]}" ;
	}
	
	private void dataTestSellAndBuyOK() {	
		payloadString = "{\"initialBalances\":{\"cash\":100,\"issuers\":[{\"issuerName\":\"GBM\",\"totalShares\":10,\"sharePrice\":10}"
				                                                    + " ,{\"issuerName\":\"MMM\",\"totalShares\":17,\"sharePrice\":10}]}"
				  + ",\"orders\":[{\"timestamp\":\"1571253725\",\"operation\":\"BUY\",\"IssuerName\":\"GBM\",\"TotalShares\":3,\"SharePrice\":10}"
				              + ",{\"timestamp\":\"1571239325\",\"operation\":\"SELL\",\"IssuerName\":\"MMM\",\"TotalShares\":8,\"SharePrice\":10}]}"; 				
		resposponseString = "{\"currentBalance\":{\"cash\":150.0,\"issuers\":[{\"issuerName\":\"GBM\",\"totalShares\":13,\"sharePrice\":10.0}"
				                                                          + ",{\"issuerName\":\"MMM\",\"totalShares\":9,\"sharePrice\":10.0}]},\"bussinessErrors\":[]}";
	}

}
