package com.wang.yan.mvc.bitstamp;

/**
 * Created by ywang on 06.07.17.
 */
public class BitstampFactory {
	static {
		ResteasyProviderFactory factory = ResteasyProviderFactory.getInstance();
		factory.registerProviderInstance(new JacksonJsonProvider());
		RegisterBuiltin.register(factory);
	}

	public static BitStamp createResteasyEndpoint() {
		ResteasyClient client = new ResteasyClient();
		ResteasyWebTarget target = client.target("https://www.bitstamp.net/");
		return target.proxy(BitStamp.class);
	}
}