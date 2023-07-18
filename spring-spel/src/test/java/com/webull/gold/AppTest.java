package com.webull.gold;

import com.google.common.collect.Maps;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.Map;

public class AppTest extends TestCase {

    @Test
    public void testExpressionNotEq() {
        final SpelExpressionParser parser = new SpelExpressionParser();
        final SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        final Boolean value = parser.parseExpression("1+1!=2").getValue(context, Boolean.class);
        assertEquals(Boolean.FALSE, value);
    }

    @Test
    public void testExpressionGT() {
        final SpelExpressionParser parser = new SpelExpressionParser();
        final SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        final Boolean value = parser.parseExpression("11.00>000012.00").getValue(context, Boolean.class);
        assertEquals(Boolean.FALSE, value);
    }

    @Test
    public void testExpressionEq() {
        final SpelExpressionParser parser = new SpelExpressionParser();
        final SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        final Boolean value = parser.parseExpression("11.00 == 000011.000000").getValue(context, Boolean.class);
        assertEquals(Boolean.TRUE, value);
    }

    @Test
    public void testExpressionEq1() {
        final SpelExpressionParser parser = new SpelExpressionParser();
        final SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        final Boolean value = parser.parseExpression("((11.00 == 11) && (2 >= 1)) || 1>2").getValue(context, Boolean.class);
        assertEquals(Boolean.TRUE, value);
    }

    @Test
    public void testExpress() {
        final String matchExpression = "{#strikeAmount#} > 100.1000 && {#price#} < 50.1111";

        final Map<String, String> valueMap = Maps.newHashMap();
        valueMap.put("strikeAmount", "100.2");
        valueMap.put("price", "50.000000000");

        final String process = StringTemplateUtil.process(matchExpression, valueMap);
        System.out.println(process);

        final SpelExpressionParser parser = new SpelExpressionParser();
        final SimpleEvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        final Boolean result = parser.parseExpression(process).getValue(context, Boolean.class);
        assertEquals(Boolean.TRUE, result);
    }

}
