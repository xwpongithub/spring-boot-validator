package cn.xwplay.validator.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class MessageUtil {

  public static String getMessage(String key,Object ...values) {
    MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
    Locale locale = LocaleContextHolder.getLocale();
    if (values.length>0) {
      return messageSource.getMessage(key,values, locale);
    }
    return messageSource.getMessage(key,null, locale);
  }

}
