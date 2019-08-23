package org.weicong.common.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.weicong.common.base.util.RedisUtil;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @description 
 * @author weicong
 * @date 2019年6月16日
 * @version 1.0
 */
@Configuration
public class RedisConfig {

	  @Bean("redisTemplateCustom")
	  public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
	    RedisTemplate<String,Object> template = new RedisTemplate <>();
	    template.setConnectionFactory(factory);

	    Jackson2JsonRedisSerializer<?> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
	    ObjectMapper om = new ObjectMapper();
	    om.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
	    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
	    jackson2JsonRedisSerializer.setObjectMapper(om);

	    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
	    // key采用String的序列化方式
	    template.setKeySerializer(stringRedisSerializer);
	    // hash的key也采用String的序列化方式
	    template.setHashKeySerializer(stringRedisSerializer);
	    // value序列化方式采用jackson
	    template.setValueSerializer(jackson2JsonRedisSerializer);
	    // hash的value序列化方式采用jackson
	    template.setHashValueSerializer(jackson2JsonRedisSerializer);
	    template.afterPropertiesSet();
	    return template;
	  }
	  
	  @Bean
	  public RedisUtil redisUtil() {
		  return new RedisUtil();
	  }
}
