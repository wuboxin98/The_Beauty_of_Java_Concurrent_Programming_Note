package com.demo.thymeleaf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

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
        String template = "[# th:if='${upgradeInfo}']" +
                "[[${upgradeInfo.emoji}]]【升级预警】[[${upgradeInfo.text}]]\n\n" +
                "[/]" +
                "[# th:if='${degradeInfo}']" +
                "[[${degradeInfo.emoji}]]【降级预警】[[${degradeInfo.text}]]\n\n" +
                "[/]" +
                "尊敬的 [[${#strings.substring(domain, 0, 3)}]] 代理商：  \n" +
                "\uD83D\uDCC5 您今日（[[${#temporals.format(triggerTime, 'yyyy-MM-dd')}]]）等级数据如下：\n" +
                "   - 当日等级：[[${domainColdStartLevelVo.level}]] 等级  \n" +
                "   - 总积分（7 日日均）：[[${domainColdStartLevelVo.rewardTicket}]]分\n" +
                "\n" +
                "[# th:if='${agentKaTriggered && !#lists.isEmpty(agentKaItems)}']" +
                "\uD83C\uDFC6 您今日各航司 KA 排名如下：\n" +
                "[# th:each='item ,iterStat: ${#lists.filter(agentKaItems,\"ranklevel != 0\")}']" +
                "([[${iterStat.index+1}]])[[${item.carrier}]]目前[[${item.my_paiming}]]名，Ka需[[${item.ranklevel}]]名，差[[${item.ka_cha}]]分\n" +
                "[/][/]";

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
        context.setVariable("domain", "abc.trade.qunar.com");
        context.setVariable("triggerTime", LocalDateTime.now());

        // 质检违约金
        List<AgentKaInfoBean> agentKaItems = Arrays.asList(
                new AgentKaInfoBean("MU", "MU", "abc", "否", 2, 10, 1, 10.0, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                new AgentKaInfoBean("CA", "CA", "abc", "否", 1, 10, 1, 10.0, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                new AgentKaInfoBean("HU", "HU", "abc", "是", 1, 10, 1, 10.0, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        );
        context.setVariable("agentKaItems", agentKaItems);


        // context.setVariable("upgradeInfo", new ThymeleafTextExample.DomainLevelWarnInfo(ThymeleafTextExample.DomainWarnType.UPDATE_LEVEL, "🎉", "恭喜，只差临门一脚，今日达100积分，可升级至B等级！"));
        context.setVariable("upgradeInfo", null);
        context.setVariable("degradeInfo", new ThymeleafTextExample.DomainLevelWarnInfo(ThymeleafTextExample.DomainWarnType.UPDATE_LEVEL, "⚠️", "降级预警，B等级资格即将不保，今日达到100积分可保住B等级"));


        context.setVariable("agentKaTriggered", true);

        // DomainColdStartLevelVo(domain=abc.trade.qunar.com, averageTicket=150, serviceScore=84.0, rewardTicket=144, level=F, levelAverageTicket=0, levelServiceScore=0, levelActivityScore=0, discountCardOpenSwitch=true, discountCardAllAverageTicket=144, discountCardPackageTicketNumTip=（中转包、往返包）, discountCardPackageTicketNum=0, discountCardRewardTicket=144, levelHasDiscountCard=false, levelDiscountScore=0, levelDiscountExpireDate=null, updateLevel=E, updateAverageTicket=200, updateServiceScore=60, updateActivityScore=0, updateHasDiscountCard=false, updateDiscountScore=0, updateDiscountExpireDate=null)
        DomainColdStartLevelVo domainColdStartLevelVo = DomainColdStartLevelVo.builder()
                .domain("abc.trade.qunar.com")
                .averageTicket(150)
                .serviceScore(84.0f)
                .rewardTicket(144)
                .level("F")
                .levelAverageTicket(0)
                .levelServiceScore(0)
                .levelActivityScore(0)
                .discountCardOpenSwitch(true)
                .discountCardAllAverageTicket(144)
                .discountCardPackageTicketNumTip("（中转包、往返包）")
                .discountCardPackageTicketNum(0)
                .discountCardRewardTicket(144)
                .levelHasDiscountCard(false)
                .levelDiscountScore(0)
                .levelDiscountExpireDate(null)
                .updateLevel("E")
                .updateAverageTicket(200)
                .updateServiceScore(60)
                .updateActivityScore(0)
                .updateHasDiscountCard(false)
                .updateDiscountScore(0)
                .updateDiscountExpireDate(null)
                .build();
        context.setVariable("domainColdStartLevelVo", domainColdStartLevelVo);

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


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DomainColdStartLevelVo {

        private String domain;              // 代理
        private int averageTicket;          // 代理日均票量
        private float serviceScore;         // 代理服务分
        private int rewardTicket;           // 代理奖励票量（活动积分）

        private String level;               // 代理当前等级
        private int levelAverageTicket;     // 代理当前等级-日均票量
        private int levelServiceScore;      // 代理当前等级-服务分
        private int levelActivityScore;     // 代理当前等级-活动积分

        private boolean discountCardOpenSwitch;             // 折扣卡逻辑开关，默认false
        private int discountCardAllAverageTicket;           // 折扣卡逻辑，总积分
        private String discountCardPackageTicketNumTip;     // 折扣卡逻辑，包积分提示
        private int discountCardPackageTicketNum;           // 折扣卡逻辑，包积分
        private int discountCardRewardTicket;               // 折扣卡逻辑，活动积分

        private boolean levelHasDiscountCard;               // 折扣卡逻辑，是否含有当前等级的折扣卡
        private int levelDiscountScore;                     // 折扣卡逻辑，当前等级折扣积分
        private String levelDiscountExpireDate;             // 折扣卡逻辑，当前等级抵扣卡失效日期


        private String updateLevel;         // 代理下一等级
        private int updateAverageTicket;    // 代理下一等级-日均票量
        private int updateServiceScore;     // 代理下一等级-服务分
        private int updateActivityScore;    // 代理下一等级-活动积分

        private boolean updateHasDiscountCard;              // 折扣卡逻辑，是否含有下一等级的折扣卡
        private int updateDiscountScore;                    // 折扣卡逻辑，下一等级折扣积分
        private String updateDiscountExpireDate;            // 折扣卡逻辑，下一等级抵扣卡失效日期

    }


}