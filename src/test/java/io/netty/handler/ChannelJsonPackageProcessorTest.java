package io.netty.handler;

import io.netty.handler.ChannelJsonPackageProcessor;
import io.netty.handler.mapping.RequestPackageWrapper;
import io.netty.handler.mapping.ResponsePackage;
import io.netty.handler.timeout.auth.AuthoeizedChannel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import softserve.netty.server.mapping.request.SyncRequestPackage;

import com.google.gson.Gson;

public class ChannelJsonPackageProcessorTest {

	private AuthoeizedChannel ac = new AuthoeizedChannel() {
		boolean authorized = false;

		@Override
		public void setAuthorized(boolean authorized) {
			this.authorized = authorized;
		}

		@Override
		public boolean isAuthorized() {
			return authorized;
		}
	};

	@Before
	public void before() {
		ChannelJsonPackageProcessor.setControllersDirectory("softserve.netty.controller");
		ChannelJsonPackageProcessor.init();
	}

	@Test
	public void processTest() {
		try {
			ChannelJsonPackageProcessor.process(null, null);
			assertTrue("Should be exception. ", false);
		} catch (Exception e) {
			assertEquals("RequestPackageWrapper should not be null", e.getMessage());
		}

		String command = "/zseqsc";
		String data = new Gson().toJson(new SyncRequestPackage("123"));
		RequestPackageWrapper request = new RequestPackageWrapper(command, data);

		try {
			ChannelJsonPackageProcessor.process(request, null);
			assertTrue("Should be exception. ", false);
		} catch (Exception e) {
			assertEquals("AuthoeizedChannel should not be null", e.getMessage());
		}

		try {
			ResponsePackage response = ChannelJsonPackageProcessor.process(request, ac);

			assertEquals(999, response.getResultCode());
			assertEquals("No one mapped(/zseqsc) command found",
					String.format("No one mapped(%s) command found", command));
		} catch (Exception e) {
			assertNull(e);
		}


		command = "/sync";
		data = new Gson().toJson(new SyncRequestPackage("123"));
		request = new RequestPackageWrapper(command, data);
		ResponsePackage response = ChannelJsonPackageProcessor.process(request, ac);
		
		System.out.println(response);
	}
}
