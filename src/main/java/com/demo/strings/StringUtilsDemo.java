package com.demo.strings;

import com.google.common.base.Splitter;
import com.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class StringUtilsDemo {

    public static void main(String[] args) {
        // 1|2|3|4|5|
        System.out.println(StringUtils.join(new Object[]{"1", "2", "3", "4", "5", ""}, "|"));
        // 1|2|3||5|6
        System.out.println(StringUtils.join(new Object[]{"1", "2", "3", "", "5", "6"}, "|"));
        System.out.println();

        // 1-2-3-4-5-  会忽略尾部空串
        String[] split = StringUtils.split("1|2|3|4|5|", "|");
        for (String s : split) {
            System.out.print(s + '-');
        }
        System.out.println();

        // 1-2-3-5-6-  会忽略中间空串
        String[] split1 = StringUtils.split("1|2|3||5|6", "|");
        for (String s : split1) {
            System.out.print(s + '-');
        }
        System.out.println();


        System.out.println("1234".substring(0, 3)); // 123
        // System.out.println("12".substring(0, 3));   //Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 0, end 3, length 2
        // System.out.println("".substring(0, 3));     //Exception in thread "main" java.lang.StringIndexOutOfBoundsException: begin 0, end 3, length 0

        System.out.println(StringUtils.substring("1234", 0, 3));    // 123
        System.out.println(StringUtils.substring("12", 0, 3));      // 12
        System.out.println(StringUtils.substring("", 0, 3));        //""
        System.out.println(StringUtils.substring(null, 0, 3));      // null
        System.out.println(Optional.ofNullable(StringUtils.substring(null, 0, 3)).orElse(""));  //""

        // 1_2___4
        String join = StringUtils.join(new Object[]{"1", "2", "", null, "4"}, '_');
        System.out.println(join);

        String[] splits = StringUtils.split("1_2___4", "_");
        System.out.println(splits.length);
        System.out.println(JsonUtil.toJson(splits));
        // 3
        // ["1","2","4"]

        // 转为数组，保留空串
        String[] split2 = StringUtils.splitByWholeSeparatorPreserveAllTokens("1_2___4", "_");
        System.out.println(split2.length); // 5
        System.out.println(JsonUtil.toJson(split2)); // ["1","2","","","4"]

        // 转为list，保留空串
        List<String> split3 = Splitter.on("_").splitToList("1_2___4");
        // 直接打印list类型，元素不加引号
        System.out.println(split3);   //[1, 2, , , 4]
        // 使用JsonUtil.toJson序列化，元素加引号
        System.out.println(JsonUtil.toJson(split3)); // ["1","2","","","4"]

        Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();
        System.out.println(splitter);
        // Splitter 拆分null时直接抛异常
        // System.out.println(splitter.split(null));


        String template = "[# th:if='${todayHourInfo ||  orderLimitTriggered && !#lists.isEmpty(orderLimitItems) || deductPenaltyTriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
                "\uD83D\uDCE2 重要通知：您有以下权益即将丢失！\n" +
                "尊敬的[[${#strings.substring(domain, 0, 3)}]]代理商:\n截至今日[[${#temporals.format(triggerTime, 'yyyy-MM-dd HH:mm')}]]，您有以下预警，请尽快处理，以防影响业务\n" +
                "\n" +
                "[# th:if='${todayHourInfo}']" +
                "*****\uD83D\uDD25积分余额不足预警*****\n" +
                "**等级降级预警**\n" +
                "❗\uFE0F 截至[[${todayScoreWarnInfo.hour}]]，仅新增[[${todayScoreWarnInfo.score}]]分（今日保级积分[[${todayScoreWarnInfo.targetScore}]]分）\n" +
                "\uD83D\uDCA1 未达到保级积分，明日将降至[[${todayScoreWarnInfo.degradeLevel}]]等级，建议调整活动阈值\n\n" +
                "[/]" +
                "[# th:if='${orderLimitTriggered && !#lists.isEmpty(orderLimitItems) || deductPenaltyTriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
                "*****\uD83D\uDD25权益失效预警*****[/]\n" +
                "[# th:if='${orderLimitTriggered && !#lists.isEmpty(orderLimitItems)}']" +
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
                "[# th:if='${deductPenaltyTriggered && !#lists.isEmpty(deductPenaltyItems)}']" +
                "**质检违约金抵扣倒计时**\n" +
                "[# th:each='item ,iterStat: ${deductPenaltyItems}']" +
                "[# th:if='${iterStat.index} < 2']" +
                "\uD83D\uDCB0  质检单[[${item.qcNo}]]，剩余处理时间[[${item.remainProcessTime}]]小时，可用[[${item.recommendUsePoint}]]积分抵扣[[${item.recommendDeductPenalty}]]元违约金\n" +
                "[/]" +
                "[/]" +
                "[# th:if='${#lists.size(deductPenaltyItems)} > 2']" +
                "\uD83D\uDCB0 还有[[${#lists.size(deductPenaltyItems)}-2]]单质检违约金抵扣将到期，请及时关注\n" +
                "[/]" +
                "\uD83D\uDCA1 超时将损失[[${#aggregates.sum(deductPenaltyItems.{recommendDeductPenalty})}]]元，建议立即兑换" +
                "[/]" +
                "[/]";
        System.out.println(template.replace("\n", "\\n").replace("\t", "\\t"));
    }
}
