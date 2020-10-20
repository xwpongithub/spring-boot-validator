package cn.xwplay.validator.entity.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AddressBO {

    @NotBlank
    private String province;
    private String city;
    private String district;
    private String street;
    private String detail;

}
