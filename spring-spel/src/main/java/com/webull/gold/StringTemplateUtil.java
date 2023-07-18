package com.webull.gold;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@UtilityClass
public class StringTemplateUtil {
    /**
     * 字母+数字
     */
    private static final Pattern FIELD_NAME_PATTERN = Pattern.compile("\\{#[a-zA-Z0-9]+#}");
    /**
     * 可以中英混合
     */
    private static final Pattern FIELD_DESC_PATTERN = Pattern.compile("\\{#[\u4e00-\u9fa5_a-zA-Z0-9]+#}");
    private static final Pattern URL_PATTERN = Pattern.compile("\\[\\$[\\s]*(https?)://[-A-Za-z0-9+&@#/%?=~_|!:,.;\\s]+[-A-Za-z0-9+&@#/%=~_\\s|][\\s]*\\$]");
    /**
     * 如果要识别不符合地址规则的内容需要非贪婪匹配
     */
    private static final Pattern URL_PATTERN2 = Pattern.compile("\\[\\$.*?\\$]");

    public String replaceUrl(String template, Map<String, String> urlMap) {
        if (template == null || urlMap == null) {
            return template;
        }
        StringBuffer sb = new StringBuffer();
        Matcher m = URL_PATTERN.matcher(template);
        while (m.find()) {
            String param = m.group();
            final String longUrl = StringUtils.strip(param.substring(2, param.length() - 2));
            String shortUrl = urlMap.get(longUrl);
            m.appendReplacement(sb, shortUrl == null ? param : shortUrl);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public Set<String> resolveUrls(String template) {
        if (template == null) {
            return Collections.emptySet();
        }
        Matcher m = URL_PATTERN.matcher(template);
        final Set<String> urls = new HashSet<>();
        while (m.find()) {
            String param = m.group();
            final String longUrl = StringUtils.strip(param.substring(2, param.length() - 2));
            urls.add(longUrl);
        }
        return urls;
    }

    /**
     * key 字段描述
     * value 字段值
     * 将中文的字段描述 转换 文英文字段名
     */
    public String store(String template, Map<String, String> descNameMap) {
        return resolveTemplate(FIELD_DESC_PATTERN, template, descNameMap);
    }

    /**
     * key 字段值
     * value 字段描述
     * 将字段名转换成字段描述
     */
    public String display(String template, Map<String, String> nameDescMap) {
        return resolveTemplate(FIELD_NAME_PATTERN, template, nameDescMap);
    }

    public String process(String template, Map<String, String> params) {
        if (template == null || params == null) {
            return template;
        }
        StringBuffer sb = new StringBuffer();
        Matcher m = FIELD_NAME_PATTERN.matcher(template);
        while (m.find()) {
            String param = m.group();
            String value = params.get(param.substring(2, param.length() - 2));
            m.appendReplacement(sb, value == null ? "null" : value);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public String resolveTemplate(Pattern pattern, String template, Map<String, String> params) {
        if (template == null || params == null) {
            return template;
        }
        StringBuffer sb = new StringBuffer();
        Matcher m = pattern.matcher(template);
        while (m.find()) {
            String param = m.group();
            String value = params.get(param.substring(2, param.length() - 2));
            m.appendReplacement(sb, value == null ? param : "{#" + value + "#}");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
