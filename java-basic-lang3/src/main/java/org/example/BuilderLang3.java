package org.example;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author cleanwk
 * @date 2022/7/9
 */
public class BuilderLang3 {
    private Integer id;
    private String name;

    public BuilderLang3(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    //get set方法
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public static void main(String...strings) {
        BuilderLang3 myBean = new BuilderLang3(1,"test");
        System.out.println(myBean.toString());
    }

}
