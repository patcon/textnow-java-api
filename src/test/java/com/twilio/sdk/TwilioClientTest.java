package com.twilio.sdk;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


// TODO: Auto-generated Javadoc
/**
 * The Class TwilioClientTest.
 */
public class TwilioClientTest {

	/**
	 * Test twilio rest client string string.
	 */
	@Test
	public void testTwilioRestClientStringString() {

		// Should fail with invallid auth and token
		try {
			TwilioClient bad_client = new TwilioRestClient("fake sid",
					"fake auth token");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

		// Should construct with valid looking account sid and auth token
		TwilioClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");
	}

	/**
	 * Test request.
	 *
	 * @throws TwilioRestException the twilio rest exception
	 */
	@Test
	public void testRequest() throws TwilioRestException {
		TwilioClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");

		// Auth required
		TwilioRestResponse response = client.request("/api2.0/users", "GET", (Map) null);
		assertEquals(404, response.getHttpStatus());

		// Auth not required
		response = client.request("/api2.0", "GET", (Map) null);
		assertEquals(404, response.getHttpStatus());

		// 404'd
		response = client.request("/asfhrhewhwejrkasyrey", "GET", (Map) null);
		assertEquals(404, response.getHttpStatus());

		response = client.request("/", "GET", (Map) null);
		assertEquals(404, response.getHttpStatus());
	}

	/**
	 * Test get.
	 *
	 * @throws TwilioRestException the twilio rest exception
	 */
	@Test
	public void testGet() throws TwilioRestException {
		TwilioClient client = new TwilioRestClient(
				"ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
				"0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");

		// Auth required
		TwilioRestResponse response = client.get("https://api.twilio.com");
		assertEquals(200, response.getHttpStatus());
	}

}
