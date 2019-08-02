package com.yangjie.config.error;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @author yangjie
 * @date 2019/3/27 19:39
 *
 * 配置error页面带有自定义参数，和classpath下的
 */
@Component
public class YangJieErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {

        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);

        //会给错误返回参数中带一个company字段
        map.put("company", "yangjie");

        //异常处理器携带的数据
        Map<String, Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", 0);

        map.put("ext", ext);

        return map;
    }
}
