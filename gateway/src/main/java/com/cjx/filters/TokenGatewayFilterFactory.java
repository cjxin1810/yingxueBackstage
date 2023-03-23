package com.cjx.filters;

import com.cjx.constants.RedisPrefix;
import com.cjx.exception.IllegalTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class TokenGatewayFilterFactory extends AbstractGatewayFilterFactory<TokenGatewayFilterFactory.Config> {
    public TokenGatewayFilterFactory() {
        super(TokenGatewayFilterFactory.Config.class);
    }
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if(config.requiredToken){
                    String token = exchange.getRequest().getQueryParams().getFirst("token");
                    if(token==null){
                        throw new IllegalTokenException("非法令牌");
                    }
                    if(!redisTemplate.hasKey(RedisPrefix.TOKEN_KEY+token)){
                        throw new IllegalTokenException("令牌失效");
                    }
                }
                return chain.filter(exchange);
            }
        };
    }

    //用来配置将使用filter时指定值赋值给Config中哪个属性
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("requiredToken");
    }
    //自定义配置类，用来接收配置文件中传递过来的数据
    public static class Config {
        private boolean requiredToken;

        public boolean isRequiredToken() {
            return requiredToken;
        }

        public void setRequiredToken(boolean requiredToken) {
            this.requiredToken = requiredToken;
        }
    }
}
