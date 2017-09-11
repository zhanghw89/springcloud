package com.example.springcloud.provider.mybatis;

/**
 * Created by shiwen on 2017/9/11.
 */
public class Demo {

    public static void main(String[] args) {
        String name = " ";
        name ="民事上诉状\n" +
                "\n" +
                "上诉人(原审原告)：北京市达远律师事务所，男，出生年月出生，民族，职务或职业，住住址。联系方式：电话。\n" +
                "法定代理人/指定代理人：代理人姓名，职务或职业\n" +
                "委托诉讼代理人：胡高翔 贾博妍，工作单位。\n" +
                "被上诉人(原审诉讼地位)：当事人，工作单位。\n" +
                "……\n" +
                "(以上写明当事人和其他诉讼参加人的姓名或者名称等基本信息)\n" +
                "客户名称因与 当事人 案由一案，不服法院 判决/裁定日期作出的 案号 号民事判决/裁定，现提起上诉。\n" +
                "上诉请求：\n" +
                "诉讼请求\n" +
                "上诉理由：\n" +
                "事实和理由\n" +
                "此致\n" +
                "     法院\n" +
                "万达\n" +
                "阿里巴巴\n" +
                "\n" +
                "附：本上诉状副本份数份\n" +
                " \n" +
                "上诉人客户名称（签名/盖章）\n" +
                "上诉日期\n" +
                " \n" +
                "【说明】\n" +
                "1．本样式根据《中华人民共和国民事诉讼法》第一百六十四条、第一百六十五条、第一百六十六条、第二百六十九条制定，供不服第一审人民法院民事判决或者裁定的当事人，向上一级人民法院提起上诉用。\n" +
                "2．当事人是法人或者其他组织的，写明名称住所。另起一行写明法定代表人、主要负责人及其姓名、职务、联系方式。\n" +
                "3．当事人不服地方人民法院第一审判决的，有权在判决书送达之日起十五日内向上一级人民法院提起上诉。当事人不服地方人民法院第一审裁定的，有权在裁定书送达之日起十日内向上一级人民法院提起上诉。在中华人民共和国领域内没有住所的当事人，不服第一审人民法院判决、裁定的，有权在判决书、裁定书送达之日起三十日内提起上诉。\n" +
                "4．上诉状的内容，应当包括当事人的姓名，法人的名称及其法定代表人的姓名或者其他组织的名称及其主要负责人的姓名；原审人民法院名称、案件的编号和案由；上诉的请求和理由。\n" +
                "5．上诉状应当通过原审人民法院提出，并按照对方当事人或者代表人的人数提出副本。\n" +
                "6．有新证据的，应当在上诉理由之后写明证据和证据来源，证人姓名和住所。";

        String[] split = name.split("");
        for (String str : split) {
            String encoding = getEncoding(str);
            System.out.println(str + ">>>>:"+encoding);
            if (encoding.equals("GBK")){
                System.out.println(str + ">>>>:"+encoding);
            }
        }
        System.out.println("finished");
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        String s = getEncoding(str, encode);

        encode = "ISO-8859-1";
        String s1 = getEncoding(str, encode);

        encode = "UTF-8";
        String s2 = getEncoding(str, encode);

        encode = "GBK";
        String s3 = getEncoding(str, encode);

        return getFirstNotNullStr(s, s1, s2, s3);
    }


    private static String getFirstNotNullStr(String... args) {
        for (String str : args) {
            if (str != null) {
                return str;
            }
        }
        return null;
    }


    private static String getEncoding(String str, String encode) {
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
            return null;
        }
        return null;
    }


}
