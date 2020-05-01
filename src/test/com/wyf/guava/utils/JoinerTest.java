package com.wyf.guava.utils;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


import static com.google.common.collect.ImmutableBiMap.of;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


/**
* JoinerTest Tester. 
* 
*/
public class JoinerTest {

    private final List<String> stringList = Arrays.asList("Google","Guava","Java","Scala","Kafka");
    private final List<String> stringWithNullValueList = Arrays.asList("Google","Guava","Java","Scala",null);

    private final String targetFileName = "E:\\wwj\\guaua\\guava-joiner.txt";
    private final Map<String, String> stringMap = of("Hello", "Guava", "Java", "Scala");

    private final String targetFileNameToMap = "E:\\wwj\\guaua\\guava-joiner-map.txt";
    @Test
    public void testJoinOnJoin(){
        String result = Joiner.on("#").join(stringList);
        assertThat(result,equalTo("Google#Guava#Java#Scala#Kafka"));
    }
    @Test(expected = NullPointerException.class)
    public void testJoinOnJoinWithNullValue(){
        String result = Joiner.on("#").join(stringWithNullValueList);
        assertThat(result,equalTo("Google#Guava#Java#Scala"));
    }

    @Test
    public void testJoinOnJoinWithNullValueButSkip(){
        String result = Joiner.on("#").skipNulls().join(stringWithNullValueList);
        assertThat(result,equalTo("Google#Guava#Java#Scala"));
    }
    @Test
    public void testJoin_On_Join_WithNullValue_UseDefaultValue(){
        String result = Joiner.on("#").useForNull("DEFAULT").join(stringWithNullValueList);
        assertThat(result,equalTo("Google#Guava#Java#Scala#DEFAULT"));
    }

    @Test
    public void testJoin_On_Append_To_StringBuilder(){
        final StringBuilder builder = new StringBuilder();
        StringBuilder resultBuilder = Joiner.on("#").useForNull("DEFAULT").appendTo(builder,stringWithNullValueList);
        assertThat(resultBuilder,sameInstance(builder));
        assertThat(resultBuilder.toString(),equalTo("Google#Guava#Java#Scala#DEFAULT"));
        assertThat(builder.toString(),equalTo("Google#Guava#Java#Scala#DEFAULT"));
    }
    @Test
    public void testJoin_On_Append_To_Writer(){
        try(FileWriter writer = new FileWriter(new File(targetFileName))){
            Joiner.on("#").useForNull("DEFAULT").appendTo(writer,stringWithNullValueList);
            assertThat(Files.isFile().test(new File(targetFileName)),equalTo(true));
        } catch (IOException e) {
            fail("append to the writer occur fetal error");
        }
    }
    @Test
    public void testJoiningByStreamSkipNullValues(){
        String result = stringWithNullValueList.stream().filter(item->item!=null && !item.isEmpty()).collect(Collectors.joining("#"));
        assertThat(result,equalTo("Google#Guava#Java#Scala"));
    }
    @Test
    public void testJoiningByStreamWithDefaultValue(){
        //String result = stringWithNullValueList.stream().map(item->item==null || item.isEmpty()?"DEFAULT":item).collect(Collectors.joining("#"));
        String result = stringWithNullValueList.stream().map(this::defaultValue).collect(Collectors.joining("#"));
        assertThat(result,equalTo("Google#Guava#Java#Scala#DEFAULT"));
    }
    private String defaultValue(final String item){
        return item==null || item.isEmpty()?"DEFAULT":item;
    }
    @Test
    public void testJoinOnWithMap(){
        assertThat(Joiner.on("#").withKeyValueSeparator("=").join(stringMap),equalTo("Hello=Guava#Java=Scala"));
    }

    @Test
    public void testJoin_On_With_Map_To_Appendable(){
        try(FileWriter writer = new FileWriter(new File(targetFileNameToMap))){
            Joiner.on("#").withKeyValueSeparator("=").appendTo(writer,stringMap);
            assertThat(Files.isFile().test(new File(targetFileNameToMap)),equalTo(true));
        } catch (IOException e) {
            fail("append to the writer occur fetal error");
        }
    }
}
