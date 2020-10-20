package cn.xwplay.validator.controller;

import cn.xwplay.validator.common.Response;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.List;

@RestController
@Validated
public class BaseValidatorController {

    // http://localhost:8080/notblank
    @GetMapping("notblank")
    public Response notBlank(@RequestParam String param) {
        System.out.println(LocaleContextHolder.getLocale());
        return Response.ok();
    }

    // http://localhost:8080/array/notempty?ids=1&ids=2
    @GetMapping("array/notempty")
    public Response arrayNotEmpty(@RequestParam int[] ids) {
       return Response.ok();
    }

    // http://localhost:8080/list/notempty?ids=1&ids=2
    @GetMapping("list/notempty")
    public Response listNotEmpty(@RequestParam List<String> ids) {
        return Response.ok();
    }

    // http://localhost:8080/pattern
    @GetMapping("pattern")
    public Response email(@RequestParam @Email String email) {
        return Response.ok();
    }

}
