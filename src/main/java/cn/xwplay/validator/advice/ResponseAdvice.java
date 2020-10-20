package cn.xwplay.validator.advice;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.xwplay.validator.annotation.IgnoreResponseSerializable;
import cn.xwplay.validator.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xiao wenpeng
 */
@RestControllerAdvice
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return !(
                methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseSerializable.class) ||
                        (methodParameter.getMethod() !=null && methodParameter.getMethod().isAnnotationPresent(IgnoreResponseSerializable.class))
                        || StrUtil.containsAny(methodParameter.getDeclaringClass().getName(),"WebMvcEndpointHandlerMapping")
        );
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest req, ServerHttpResponse res) {
        HttpHeaders headers = res.getHeaders();
        if (body == null) {
            return Response.ok();
        } else if (body instanceof Response) {
            return body;
        } else {
            Response r= Response.ok().put("data", body);
            // 如果返回值为string则要特殊处理
            if(body instanceof String) {
                headers.setContentType(MediaType.APPLICATION_JSON);
                return JSONUtil.toJsonStr(r);
            } else if (body instanceof LinkedHashMap){
                // 如果返回值是500或者404
                Map<String,Object> errorResult = (Map<String,Object>)body;
                String statusKey = "status";
                String errorKey ="error";
                if (errorResult.containsKey(statusKey)&& errorResult.containsKey(errorKey)
                        && errorResult.containsKey("path")&&errorResult.containsKey("timestamp")){
                    int status = (int)errorResult.get(statusKey);
                    String msg = "";
                    if (status == HttpStatus.NOT_FOUND.value()) {
                        msg="404";
                    } else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                        msg = "服务器异常";
                    }
                    return Response.error(status,msg);
                }
            }
            return r;
        }
    }

}
