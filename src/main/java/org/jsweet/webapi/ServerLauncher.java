package org.jsweet.webapi;

import java.util.Date;
import org.apache.log4j.Logger;

public class ServerLauncher {
	
	private final static Logger logger = Logger.getLogger(ServerLauncher.class);

	public static void main(String[] args) {
		System.out.println("ServerLauncher started at " + new Date());
		logger.info("starting server");
		Thread serverThread = new Thread() {
			@Override
			public void run() {
				try {
					Server server = new Server();
					logger.info("server=" + server);

					synchronized (this) {
						wait();
					}
				} catch (Exception ioe) {
					logger.error("server failed", ioe);
				}
			}
		};
		serverThread.setDaemon(false);
		serverThread.start();

		logger.info("exiting");
	}
}
