package cn.xwplay.validator.controller;

import cn.xwplay.validator.common.Response;
import cn.xwplay.validator.entity.bo.UserBO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@Validated
public class BeanValidatorController {

    // http://localhost:8080/body
    @PostMapping("body")
    public Response body(@Validated @RequestBody UserBO user) {
       return Response.ok();
    }

    // http://localhost:8080/list
    @PostMapping("list")
    public Response list(@RequestBody @NotEmpty List<UserBO> list) {
        return Response.ok();
    }
}
