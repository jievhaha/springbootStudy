package com.jievhaha.springbootweb.componet;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        Locale locale = httpServletRequest.getLocale();
        String parameter = httpServletRequest.getParameter("l");
        if(!StringUtils.isEmpty(parameter)){
            String[] values = parameter.split("_");
            locale = new Locale(values[0],values[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
