/*
 * Copyright 2015 the original author or authors.
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

package org.springframework.cloud.stream.app.redis.sink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.outbound.RedisPublishingMessageHandler;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.integration.redis.outbound.RedisStoreWritingMessageHandler;
import org.springframework.messaging.MessageHandler;

/**
 * Creates a dedicated RedisConnectionFactory different from the one spring-boot autoconfigure
 * section that the bindings use in spring-cloud-stream.
 * <p/>
 * The configuration prefix is "spring.cloud.stream.module.redis" and contains the standard
 * properties to configure a redis connection, host, port, etc as well as the additional properties for
 * the sink, queue, key, etc.
 *
 * @author Eric Bottard
 * @author Mark Pollack
 */
@Configuration
@EnableConfigurationProperties(RedisSinkProperties.class)
public class RedisSinkConfiguration {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	private RedisSinkProperties redisSinkProperties;

	@Bean
	@Qualifier
	public MessageHandler redisSinkMessageHandler() {
		if (redisSinkProperties.isKey()) {
			RedisStoreWritingMessageHandler redisStoreWritingMessageHandler =
					new RedisStoreWritingMessageHandler(redisConnectionFactory);
			redisStoreWritingMessageHandler.setKeyExpression(redisSinkProperties.getKeyExpression());
			return redisStoreWritingMessageHandler;
		} else if (redisSinkProperties.isQueue()) {
			return new RedisQueueOutboundChannelAdapter(redisSinkProperties.getQueueExpression(), redisConnectionFactory);
		} else { // must be topic
			RedisPublishingMessageHandler redisPublishingMessageHandler =
					new RedisPublishingMessageHandler(redisConnectionFactory);
			redisPublishingMessageHandler.setTopicExpression(redisSinkProperties.getTopicExpression());
			return redisPublishingMessageHandler;
		}
	}
}