package com.demo.thymeleaf;

import com.google.gson.reflect.TypeToken;
import com.util.Jsons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ThymeleafTextExample1 {
    public static void main(String[] args) {
        // 1. 初始化引擎并设置为文本模式
        TemplateEngine templateEngine = new TemplateEngine();
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT); // **关键：设置为文本模式**
        templateEngine.setTemplateResolver(templateResolver);

        // 2. 定义模板 (使用类似XML的语法)
        // String template = "你好, [[${userName}]]!\n" +
        //         "[# th:if='${isVip && isTest}']欢迎您成为我们的VIP会员。\n[/]" +
        //         "[# th:if='${#lists.isEmpty(items)}']" +
        //         "您当前没有待办事项。" +
        //         "[/]" +
        //         "[# th:unless='${#lists.isEmpty(items)}']" +
        //         "您的待办事项:\n" +
        //             "[# th:each='item : ${items}']" +
        //             "- [[${item}]]\n" +
        //             "[/]" +
        //         "[/]";

String template = "\uD83C\uDFC6 您今日各航司 KA 排名如下：\n" +
        "[# th:each='item ,iterStat: ${agentKaItems}']" +
        "[# th:if='${item.is_ka == true}']" +
        "([[${iterStat.index+1}]])[[${item.carrier}]]目前[[${item.my_paiming}]]名，Ka需[[${item.ranklevel}]]名，差[[${item.ka_cha}]]分\n" +
        "[/]" +
        "[/]";


        // 3. 准备数据 (Context)
        Context context = getContext();

        // 4. 渲染
        String output = templateEngine.process(template, context);
        System.out.println(output);

        // String escaped = StringEscapeUtils.escapeJava(template);
        // System.out.println("转义后: " + escaped);

        System.out.print("Template content:");
        System.out.println(template.replace("\n", "\\n").replace("\t", "\\t"));
    }

    private static Context getContext() {

        Context context = new Context();
        // 质检违约金
        List<AgentKaInfoBean> agentKaItems = Arrays.asList(
                new AgentKaInfoBean("MU", "MU", "abc", "否", 2, 10, 1, 10.0, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                new AgentKaInfoBean("CA", "CA", "abc", "否", 1, 10, 1, 10.0, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                new AgentKaInfoBean("HU", "HU", "abc", "是", 1, 10, 1, 10.0, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        );
        context.setVariable("agentKaItems", agentKaItems);

        return context;

    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AgentKaInfoBean {
        private String carrier;
        private String carrier_name;
        private String agent_domain;
        private String is_ka;
        private int ka_cha;
        private int reward_ticket_num;
        private int ka_num;
        private double zhanbi;
        private int my_paiming;
        private int before_paiming_cha;
        private int last_paiming_cha;
        private int ranklevel;
        private int all_tkt_num;
        private int caigou_num;
        private int paiming;
        private int domain_tnum;
        private int tkt_num;
    }

}