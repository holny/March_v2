package com.hly.march2.utils;//package com.hly.march.utils;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class GeneArticles {
//
//    public static List<Article> geneArticle(int count) throws Exception {
//        StringBuilder text = abstractTxt("/Users/hly/IdeaProjects/March/src/main/java/com/hly/march/utils/87641432199105.txt");
//        int textLength = text.length();
//        List<Article> articleList = new ArrayList<>();
//        for(int i =0;i<count;i++) {
//            Article article = new Article();
//            article.setUserId(String.valueOf(10));
//            int r1 = (int)(Math.random() * (textLength-30));
//            String title = text.substring(r1,r1+15);
//            article.setArticleTitle(title);
//            article.setArticleContent(text.toString());
//            article.setArticleCreateDate(new Date());
//            article.setArticleUpdateDate(new Date());
//            int r2 = (int)(Math.random() * (textLength-120));
//            String brief = text.substring(r2,r2+100);
//            article.setArticleBriefIntroduction(brief);
//            articleList.add(article);
//        }
//        return articleList;
//    }
//
//
//    public static StringBuilder abstractTxt(String txt) throws Exception{
//
//        BufferedReader br = new BufferedReader(new FileReader(txt));
//        StringBuilder strB = new StringBuilder();
//        String str;
//        while((str=br.readLine())!=null&&str.trim()!="") {
//            strB.append(str);
//        }
//        System.out.println(str);
//        br.close();
//        return strB;
//    }
//
//
//}
