package com.cjx.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse response, Object o, Exception e) {
        //自定义异常信息，把信息封装到map集合里面，然后把map转换为json响应给浏览器
        Map<String,String> resultMap= new HashMap<>();
        resultMap.put("msg",e.getMessage());
        //这个类可以帮我们把java对象转换为json串。
        ObjectMapper mapper = new ObjectMapper();
        //String resultJson = mapper.writeValueAsString(resultMap);
        //告知浏览器我们响应的是一个json串
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        //设置响应的状态码
        if(e instanceof IllegalPasswordException){
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }else{
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        //response.getWriter().print(resultJson);把resultmap的信息放入到repsone里面。
        mapper.writeValue(response.getWriter(),resultMap);
        return new ModelAndView();
    }
}
