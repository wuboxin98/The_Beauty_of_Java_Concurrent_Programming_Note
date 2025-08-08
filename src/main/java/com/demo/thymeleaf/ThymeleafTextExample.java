package com.demo.thymeleaf;

import com.google.gson.reflect.TypeToken;
import com.util.Jsons;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ThymeleafTextExample {
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

        String template = "[# th:if='${orderLimitITriggered && !#lists.isEmpty(orderLimitItems) && deductPenaltyITriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
                "\uD83D\uDCE2 [[${shortDomain}]]重要通知：您有以下权益即将丢失！\n" +
                "截至今日[[${#temporals.format(triggerTime, 'yyyy-MM-dd HH:mm')}]]，您有以下预警，请尽快处理，以防影响业务\n" +
                "\n" +
                "*****\uD83D\uDD25积分余额不足预警*****\n" +
                "\uD83D\uDCA1 建议调整活动阈值：修改活动阈值\n" +
                "**等级降级预警**\n" +
                "❗\uFE0F 截至目前，仅新增XX分（今日保级积分XX分）\n" +
                "⚠\uFE0F 未达到保级积分，明日将降至B等级\n" +
                "\n" +
                "[# th:if='${orderLimitITriggered && !#lists.isEmpty(orderLimitItems) || deductPenaltyITriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
                "*****\uD83D\uDD25权益失效预警*****[/]\n" +
                "[# th:if='${orderLimitITriggered && !#lists.isEmpty(orderLimitItems)}']" +
                "**航司收单上限预警**\n" +
                "[# th:each='item ,iterStat: ${orderLimitItems}']" +
                "[# th:if='${iterStat.index} < 2']" +
                "⏳ [[${item.carrier}]]票量余额[[${item.availableTicket}]]张，[[${item.willExhaustedHour}]]小时后将被禁售\n" +
                "[/]" +
                "[/]" +
                "[# th:if='${#lists.size(orderLimitItems)} > 2']" +
                "⏳ 还有[[${#lists.size(orderLimitItems)}-2]]家航司将被禁售，请及时关注\n" +
                "[/]" +
                "\uD83D\uDCA1 建议立即扩容，购买扩容包：\n" +
                "[/]" +
                "[# th:if='${deductPenaltyITriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
                "**质检违约金抵扣倒计时**\n" +
                "[# th:each='item ,iterStat: ${deductPenaltyItems}']" +
                "[# th:if='${iterStat.index} < 2']" +
                "\uD83D\uDD14 质检单[[${item.qcNo}]]，剩余处理时间[[${item.remainProcessTime}]]小时，可用[[${item.recommendUsePoint}]]积分抵扣[[${item.recommendDeductPenalty}]]元违约金\n" +
                "[/]" +
                "[/]" +
                "[# th:if='${#lists.size(deductPenaltyItems)} > 2']" +
                "\uD83D\uDCB0 还有[[${#lists.size(deductPenaltyItems)}-2]]单质检违约金抵扣将到期，请及时关注\n" +
                "[/]" +
                "\uD83D\uDCA1 超时将损失[[${#aggregates.sum(deductPenaltyItems.{recommendDeductPenalty})}]]元，建议立即兑换：" +
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
        context.setVariable("userName", "王五");
        context.setVariable("isVip", true);
        context.setVariable("isTest", false);
        context.setVariable("triggerTime", LocalDateTime.now());
        context.setVariable("fullDomain", "abc.trade.qunar.com");
        context.setVariable("shortDomain", "abc");

        // 质检违约金
        boolean deductPenaltyITriggered = true;
        List<DeductibleQualityCheckTask> deductPenaltyItems = Arrays.asList(
                new DeductibleQualityCheckTask("QC001", 10, new BigDecimal("100.00"), new BigDecimal("10.00")),
                new DeductibleQualityCheckTask("QC002", 5, new BigDecimal("50.00"), new BigDecimal("5.00")),
                new DeductibleQualityCheckTask("QC003", 2, new BigDecimal("20.00"), new BigDecimal("2.00")),
                new DeductibleQualityCheckTask("QC004", 8, new BigDecimal("80.00"), new BigDecimal("8.00"))
        );
        context.setVariable("deductPenaltyITriggered", deductPenaltyITriggered);
        context.setVariable("deductPenaltyItems", deductPenaltyItems);
        // 收单上限
        boolean orderLimitITriggered = true;
        List<AgentNoticeAlertOrderLimitItemBean> orderLimitItems = Arrays.asList(
                new AgentNoticeAlertOrderLimitItemBean(1L, "abc.trade.qunar.com", "R1", "MU", 100, 2),
                new AgentNoticeAlertOrderLimitItemBean(2L, "abc.trade.qunar.com", "R2", "9C", 50, 1),
                new AgentNoticeAlertOrderLimitItemBean(3L, "abc.trade.qunar.com", "R3", "MU", 200, 3),
                new AgentNoticeAlertOrderLimitItemBean(4L, "abc.trade.qunar.com", "R4", "9C", 150, 2)
        );
        context.setVariable("orderLimitITriggered", orderLimitITriggered);
        context.setVariable("orderLimitItems", orderLimitItems);

        System.out.println(Jsons.DEFAULT.toJson(deductPenaltyItems));
        deductPenaltyItems = Jsons.DEFAULT.fromJson("[{\"qcNo\":\"QC001\",\"remainProcessTime\":10,\"recommendUsePoint\":100.00,\"recommendDeductPenalty\":10.00},{\"qcNo\":\"QC002\",\"remainProcessTime\":5,\"recommendUsePoint\":50.00,\"recommendDeductPenalty\":5.00},{\"qcNo\":\"QC003\",\"remainProcessTime\":2,\"recommendUsePoint\":20.00,\"recommendDeductPenalty\":2.00},{\"qcNo\":\"QC004\",\"remainProcessTime\":8,\"recommendUsePoint\":80.00,\"recommendDeductPenalty\":8.00}]",
                new TypeToken<List<DeductibleQualityCheckTask>>(){}.getType());
        return context;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static
    class AgentNoticeAlertOrderLimitItemBean {
        private Long id;
        private String domain;          // 全域名
        private String productRowkey;   // 收单上限产品rowkey
        private String carrier;         // 航司
        private int availableTicket;    // 剩余票量
        private int willExhaustedHour;  // 用尽小时数
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static
    class DeductibleQualityCheckTask {
        private String qcNo;
        private int remainProcessTime;
        private BigDecimal recommendUsePoint;
        private BigDecimal recommendDeductPenalty;

    }
}