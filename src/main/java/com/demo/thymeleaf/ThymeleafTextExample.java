package com.demo.thymeleaf;

import com.google.gson.reflect.TypeToken;
import com.util.Jsons;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
* AgentNoticeEquityService
*  */
@Slf4j
public class ThymeleafTextExample {
    public static void main(String[] args) {
        // 1. 初始化引擎并设置为文本模式
        TemplateEngine templateEngine = new TemplateEngine();
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.TEXT); // **关键：设置为文本模式**
        templateEngine.setTemplateResolver(templateResolver);

        // 2. 定义模板 (使用类似XML的语法)
        // String template = "[# th:if='${todayHourInfo ||  orderLimitTriggered && !#lists.isEmpty(orderLimitItems) || deductPenaltyTriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
        //         "\uD83D\uDCE2 重要通知：您有以下权益即将丢失！\n" +
        //         "尊敬的[[${#strings.substring(domain, 0, 3)}]]代理商:\n截至今日[[${#temporals.format(triggerTime, 'yyyy-MM-dd HH:mm')}]]，您有以下预警，请尽快处理，以防影响业务\n" +
        //         "\n" +
        //         "[# th:if='${todayHourInfo}']" +
        //         "*****\uD83D\uDD25积分余额不足预警*****\n" +
        //         "**等级降级预警**\n" +
        //         "❗\uFE0F 截至[[${todayScoreWarnInfo.hour}]]，仅新增[[${todayScoreWarnInfo.score}]]分（今日保级积分[[${todayScoreWarnInfo.targetScore}]]分）\n" +
        //         "\uD83D\uDCA1 未达到保级积分，明日将降至[[${todayScoreWarnInfo.degradeLevel}]]等级，建议调整活动阈值\n\n" +
        //         "[/]" +
        //         "[# th:if='${orderLimitTriggered && !#lists.isEmpty(orderLimitItems) || deductPenaltyTriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
        //         "*****\uD83D\uDD25权益失效预警*****[/]\n" +
        //         "[# th:if='${orderLimitTriggered && !#lists.isEmpty(orderLimitItems)}']" +
        //         "**航司收单上限预警**\n" +
        //         "[# th:each='item ,iterStat: ${orderLimitItems}']" +
        //         "[# th:if='${iterStat.index} < 2']" +
        //         "⏳ [[${item.carrier}]]票量余额[[${item.availableTicket}]]张，[[${item.willExhaustedHour}]]小时后将被禁售\n" +
        //         "[/]" +
        //         "[/]" +
        //         "[# th:if='${#lists.size(orderLimitItems)} > 2']" +
        //         "⏳ 还有[[${#lists.size(orderLimitItems)}-2]]家航司将被禁售，请及时关注\n" +
        //         "[/]" +
        //         "\uD83D\uDCA1 建议立即扩容，购买扩容包：\n" +
        //         "[/]" +
        //         "[# th:if='${deductPenaltyTriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
        //         "**质检违约金抵扣倒计时**\n" +
        //         "[# th:each='item ,iterStat: ${deductPenaltyItems}']" +
        //         "[# th:if='${iterStat.index} < 2']" +
        //         "\uD83D\uDCB0  质检单[[${item.qcNo}]]，剩余处理时间[[${item.remainProcessTime}]]小时，可用[[${item.recommendUsePoint}]]积分抵扣[[${item.recommendDeductPenalty}]]元违约金\n" +
        //         "[/]" +
        //         "[/]" +
        //         "[# th:if='${#lists.size(deductPenaltyItems)} > 2']" +
        //         "\uD83D\uDCB0 还有[[${#lists.size(deductPenaltyItems)}-2]]单质检违约金抵扣将到期，请及时关注\n" +
        //         "[/]" +
        //         "\uD83D\uDCA1 超时将损失[[${#aggregates.sum(deductPenaltyItems.{recommendDeductPenalty})}]]元，建议立即兑换" +
        //         "[/]" +
        //         "[/]";

        String template = "[# th:if='${domainAuthBean.pointEquityLevelDowngradeWarning  || domainAuthBean.pointEquityInsufficientWarning || domainAuthBean.pointEquityOrderLimitWarning  || domainAuthBean.pointEquityQualityPenaltyWarning }']" +
                "\uD83D\uDCE2重要通知：您有以下权益即将丢失！\n" +
                "尊敬的[[${#strings.substring(domain, 0, 3)}]]代理商:\n" +
                "截至今日[[${#temporals.format(triggerTime, 'yyyy-MM-dd HH:mm')}]]，您有以下预警，请尽快处理，以防影响业务\n\n" +

                "[# th:if='${domainAuthBean.pointEquityLevelDowngradeWarning  || domainAuthBean.pointEquityInsufficientWarning  }']" +
                "*****\uD83D\uDD25积分余额不足预警*****\n" +
                "[/]" +

                "[# th:if='${domainAuthBean.pointEquityLevelDowngradeWarning}']" +
                "**等级降级预警**\n" +
                "❗\uFE0F 截至[[${todayScoreWarnInfo.hour}]]，仅新增[[${todayScoreWarnInfo.score}]]分（今日保级积分[[${todayScoreWarnInfo.targetScore}]]分）\n" +
                "\uD83D\uDCA1 未达到保级积分，明日将降至[[${todayScoreWarnInfo.degradeLevel}]]等级，建议调整活动阈值\n\n" +
                "[/]" +


                "[# th:if='${domainAuthBean.pointEquityOrderLimitWarning  || domainAuthBean.pointEquityQualityPenaltyWarning}']" +
                "*****\uD83D\uDD25权益失效预警*****\n" +
                "[/]" +
                //         航司收单上限预警部分
                "[# th:if='${domainAuthBean.pointEquityOrderLimitWarning}']" +
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
                //         质检违约金部分
                "[# th:if='${domainAuthBean.pointEquityQualityPenaltyWarning}']" +
                "**质检违约金抵扣倒计时**\n" +
                "[# th:each='item ,iterStat: ${deductPenaltyItems}']" +
                "[# th:if='${iterStat.index} < 2']" +
                "\uD83D\uDD14 质检单[[${item.qcNo}]]，剩余处理时间[[${item.remainProcessTime}]]小时，可用[[${item.recommendUsePoint}]]积分抵扣[[${item.recommendDeductPenalty}]]元违约金\n" +
                "[/]" +
                "[/]" +
                "[# th:if='${#lists.size(deductPenaltyItems)} > 2']" +
                "\uD83D\uDCB0 还有[[${#lists.size(deductPenaltyItems)}-2]]单质检违约金抵扣将到期，请及时关注\n" +
                "[/]" +
                "\uD83D\uDCA1 超时将损失[[${#aggregates.sum(deductPenaltyItems.{recommendDeductPenalty})}]]元，建议立即兑换" +
                "[/]" +
                "[/]";


        // 3. 准备数据 (Context)
        Context context = getContext();

        // 4. 渲染
        String output = templateEngine.process(template, context);
        System.out.println(output);

        // String escaped = StringEscapeUtils.escapeJava(template);
        // System.out.println("转义后: " + escaped);

        System.out.println("Template content:");
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

        context.setVariable("todayHourInfo", new DomainLevelWarnInfo(DomainWarnType.UPDATE_LEVEL, "✅", "今日实时积分预警，若保持当前活动深度，明日可能降级A等级，丢失等级权益！"));
        context.setVariable("upgradeInfo", new DomainLevelWarnInfo(DomainWarnType.UPDATE_LEVEL, "🎉", "恭喜，只差临门一脚，今日达100积分，可升级至B等级！"));
        context.setVariable("degradeInfo", new DomainLevelWarnInfo(DomainWarnType.UPDATE_LEVEL, "⚠️", "今日积分未达标，明日将降级为C等级，丢失部分权益！"));
        context.setVariable("todayScoreWarnInfo", new TodayScoreWarnInfo("10:00-11:00", "C", 100, "B", 1000));


        // 质检违约金
        boolean deductPenaltyITriggered = true;
        List<DeductibleQualityCheckTask> deductPenaltyItems = new ArrayList<>();
        deductPenaltyItems.add(new DeductibleQualityCheckTask("QC001", 10, new BigDecimal("100.00"), new BigDecimal("10.00")));
        deductPenaltyItems.add(new DeductibleQualityCheckTask("QC002", 5, new BigDecimal("50.00"), new BigDecimal("5.00")));
        deductPenaltyItems.add(new DeductibleQualityCheckTask("QC003", 2, new BigDecimal("20.00"), new BigDecimal("2.00")));
        deductPenaltyItems.add(new DeductibleQualityCheckTask("QC004", 8, new BigDecimal("80.00"), new BigDecimal("8.00")));

        // deductPenaltyItems = Jsons.DEFAULT.fromJson("[{\"qcNo\":\"QC001\",\"remainProcessTime\":10,\"recommendUsePoint\":100.00,\"recommendDeductPenalty\":10.00},{\"qcNo\":\"QC002\",\"remainProcessTime\":5,\"recommendUsePoint\":50.00,\"recommendDeductPenalty\":5.00},{\"qcNo\":\"QC003\",\"remainProcessTime\":2,\"recommendUsePoint\":20.00,\"recommendDeductPenalty\":2.00},{\"qcNo\":\"QC004\",\"remainProcessTime\":8,\"recommendUsePoint\":80.00,\"recommendDeductPenalty\":8.00}]",                 new TypeToken<List<DeductibleQualityCheckTask>>(){}.getType())

        context.setVariable("deductPenaltyITriggered", deductPenaltyITriggered);
        context.setVariable("deductPenaltyItems", deductPenaltyItems);
        // 收单上限
        boolean orderLimitITriggered = true;
        List<AgentNoticeAlertOrderLimitItemBean> orderLimitItems = new ArrayList<>();
        orderLimitItems.add(new AgentNoticeAlertOrderLimitItemBean(1L, "abc.trade.qunar.com", "R1", "MU", 100, 2));
        orderLimitItems.add(new AgentNoticeAlertOrderLimitItemBean(2L, "abc.trade.qunar.com", "R2", "9C", 50, 1));
        orderLimitItems.add(new AgentNoticeAlertOrderLimitItemBean(3L, "abc.trade.qunar.com", "R3", "CA", 200, 3));
        orderLimitItems.add(new AgentNoticeAlertOrderLimitItemBean(4L, "abc.trade.qunar.com", "R4", "HU", 150, 2));

        context.setVariable("orderLimitITriggered", orderLimitITriggered);
        context.setVariable("orderLimitItems", orderLimitItems);

        System.out.println(Jsons.DEFAULT.toJson(deductPenaltyItems));
        deductPenaltyItems = Jsons.DEFAULT.fromJson("[{\"qcNo\":\"QC001\",\"remainProcessTime\":10,\"recommendUsePoint\":100.00,\"recommendDeductPenalty\":10.00},{\"qcNo\":\"QC002\",\"remainProcessTime\":5,\"recommendUsePoint\":50.00,\"recommendDeductPenalty\":5.00},{\"qcNo\":\"QC003\",\"remainProcessTime\":2,\"recommendUsePoint\":20.00,\"recommendDeductPenalty\":2.00},{\"qcNo\":\"QC004\",\"remainProcessTime\":8,\"recommendUsePoint\":80.00,\"recommendDeductPenalty\":8.00}]",
                new TypeToken<List<DeductibleQualityCheckTask>>() {
                }.getType());


        AgentNoticeAuthBean domainAuthBean = AgentNoticeAuthBean.builder()
                .shortDomain("abc")
                .pointEquityNotice(true)
                .pointEquityLevelDowngradeWarning(false)
                .pointEquityInsufficientWarning(false)
                .pointEquityOrderLimitWarning(false)
                .pointEquityQualityPenaltyWarning(false)
                .agentLevelNotice(true)
                .agentLevelUpgradeDowngradeWarning(true)
                .agentLevelLevelWarning(true)
                .agentLevelKaWarning(true)
                .orderLimitBan(true)
                .qualityCheckPenaltyNotice(true)
                .build();
        context.setVariable("domainAuthBean", domainAuthBean);


        return context;

    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class AgentNoticeAlertOrderLimitItemBean {
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
    static class DeductibleQualityCheckTask {
        private String qcNo;
        private int remainProcessTime;
        private BigDecimal recommendUsePoint;
        private BigDecimal recommendDeductPenalty;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class DomainLevelWarnInfo {
        /**
         * 等级
         */
        private DomainWarnType type;

        /**
         * 表情
         */
        private String emoji;
        /**
         * 文案
         */
        private String text;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TodayScoreWarnInfo {

        /**
         * 小时段
         */
        private String hour;


        /**
         * 当前等级
         */
        private String level;

        /**
         * 当日积分数量
         */
        private int score;

        /**
         * 下一目标等级
         */
        private String degradeLevel;

        /**
         * 当日目标积分
         */
        private int targetScore;
    }

    @Getter
    public enum DomainWarnType {

        UPDATE_LEVEL(1, "升级预警"),
        DOWNLOAD_LEVEL(2, "降级预警"),
        TODAY_HOUR_POINT(3, "当日积分预警"),
        ;

        private int type;
        private String text;

        private DomainWarnType(int type, String text) {
            this.type = type;
            this.text = text;
        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class AgentNoticeAuthBean {

        /**
         * 代理商全域名
         */
        private String shortDomain;

        /**
         * 积分权益通知权限
         */
        private boolean pointEquityNotice;

        /**
         * 积分权益通知-等级降级预警权限
         */
        private boolean pointEquityLevelDowngradeWarning;

        /**
         * 积分权益通知-积分不足权益关闭预警权限
         */
        private boolean pointEquityInsufficientWarning;

        /**
         * 积分权益通知-航司收单上限预警权限
         */
        private boolean pointEquityOrderLimitWarning;

        /**
         * 积分权益通知-质检违约金预警权限
         */
        private boolean pointEquityQualityPenaltyWarning;

        /**
         * 代理等级通知权限
         */
        private boolean agentLevelNotice;

        /**
         * 代理等级通知-升降级预警权限
         */
        private boolean agentLevelUpgradeDowngradeWarning;

        /**
         * 代理等级通知-等级预警权限
         */
        private boolean agentLevelLevelWarning;

        /**
         * 代理等级通知-KA预警权限
         */
        private boolean agentLevelKaWarning;

        /**
         * 收单上限禁售通知权限
         */
        private boolean orderLimitBan;

        /**
         * 质检违约金通知权限
         */
        private boolean qualityCheckPenaltyNotice;

    }

}