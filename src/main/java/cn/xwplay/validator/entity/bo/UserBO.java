package cn.xwplay.validator.entity.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class UserBO {

    @NotBlank(message = "名字不能为空")
    private String name;
    @NotNull(message = "年龄不能为空")
    private Integer age;
    @NotNull
    private Boolean status;
    @Past
    @NotNull
    private Date birthday;
    private String mobile;
    @NotEmpty
    @Valid
    private List<AddressBO> addressList;

}
