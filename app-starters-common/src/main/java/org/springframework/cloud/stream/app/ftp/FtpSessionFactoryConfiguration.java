/*
 * Copyright 2015-2016 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.app.ftp;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

/**
 * FTP Session factory configuration.
 *
 * @author David Turanski
 * @author Gary Russell
 */
@Configuration
@EnableConfigurationProperties(FtpSessionFactoryProperties.class)
public class FtpSessionFactoryConfiguration {

	@Bean
	public DefaultFtpSessionFactory ftpSessionFactory(FtpSessionFactoryProperties properties) {
		DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
		ftpSessionFactory.setHost(properties.getHost());
		ftpSessionFactory.setPort(properties.getPort());
		ftpSessionFactory.setUsername(properties.getUsername());
		ftpSessionFactory.setPassword(properties.getPassword());
		ftpSessionFactory.setClientMode(properties.getClientMode().getMode());
		return ftpSessionFactory;
	}

}
