package com.wyf.guava.utils;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SplitterTest {

    @Test
    public void testSplitOnSplit(){
        List<String> result = Splitter.on("|").splitToList("hello|world");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
    }


    @Test
    public void testSplitOnSplitOmitEmpty(){
        List<String> result = Splitter.on("|").splitToList("hello|world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(5));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
        result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
        assertThat(result.size(),equalTo(2));
    }

    @Test
    public void testSplitOnSplitOmitEmptyTrimResult(){
        List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello | world|||");
        assertThat(result.get(0),equalTo("hello "));
        assertThat(result.get(1),equalTo(" world"));

        result = Splitter.on("|").omitEmptyStrings().trimResults().splitToList("hello | world|||");
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
    }

    /**
     *aaaabbbbccccdddd
     */
    @Test
    public void testSplitFixedLength(){
        List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
        assertThat(result,notNullValue());
        assertThat(result.get(0),equalTo("aaaa"));
        assertThat(result.get(1),equalTo("bbbb"));
        assertThat(result.get(2),equalTo("cccc"));
        assertThat(result.get(3),equalTo("dddd"));
    }

    @Test
    public void testSplitOnSplitLimit(){
        List<String> result = Splitter.on("#").limit(3).splitToList("hello#world#java#google#scala");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(3));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));
        assertThat(result.get(2),equalTo("java#google#scala"));
    }

    //正则字符串
    @Test
    public void testSplitOnPatternString(){
        List<String> result = Splitter.onPattern("\\|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));

    }
    //正则表达式
    @Test
    public void testSplitOnPattern(){
        List<String> result = Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings().splitToList("hello | world|||");
        assertThat(result,notNullValue());
        assertThat(result.size(),equalTo(2));
        assertThat(result.get(0),equalTo("hello"));
        assertThat(result.get(1),equalTo("world"));

    }

    @Test
    public void testSplitToMap(){
        Map<String,String> result = Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings().withKeyValueSeparator("=").split("hello=HELLO | world=WORLD|||");
        assertThat(result,notNullValue());
        assertThat(result.get("hello"),equalTo("HELLO"));
        assertThat(result.get("world"),equalTo("WORLD"));
    }
}
