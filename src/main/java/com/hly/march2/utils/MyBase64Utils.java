package com.hly.march2.utils;

import com.hly.march2.dto.BlogSearchDTO;
import com.hly.march2.entity.Blog;
import com.hly.march2.entity.Draft;
import com.hly.march2.vo.BlogUserVo;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 与sun.mis c套件和Apache Commons Codec所提供的Base64编解码器来比较的话，Java 8提供的Base64拥有更好的效能。实际测试编码与解码速度的话，Java 8提供的Base64，
 * 要比sun.mis c套件提供的还要快至少11倍，比Apache Commons Codec提供的还要快至少3倍。
 * 注意：Base编码会使编码后的内容比原来增加1/3
 * @Author hly
 */
public class MyBase64Utils {
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    /**
     * Java util 的Base64编码
     * @param source
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String jEncodeString(String source) throws UnsupportedEncodingException {
        byte[] textByte = source.getBytes("UTF-8");
        return encoder.encodeToString(textByte);
    }

    /**
     * Java util 的Base64解码
     * @param source
     * @return
     */
    private static String jDecodeString(String source) throws UnsupportedEncodingException {
        byte[] textByte = decoder.decode(source);
        return new String(textByte, "utf-8");
    }

    /**
     * Springframework提供的Base64编码
     * @param source
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String springEncodeString(String source) throws UnsupportedEncodingException {
        byte[] textByte = source.getBytes("UTF-8");
        return Base64Utils.encodeToString(textByte);
    }

    /**
     * Springframework提供的Base64解码
     * Spring提供的org.springframework.util.Base64Utils类，先会检测JDK里是否自带java.util.Base64，
     * 如果不带，则使用的是apache提供的org.apache.commons.codec.binary.Base64。
     * 我看了，现在貌似用的java.ut
     * @param source
     * @return
     */
    private static String springDecodeString(String source){
        return new String(Base64Utils.decodeFromString(source));
    }

    public static BlogSearchDTO blogSearchDTODecode(BlogSearchDTO blogSearchDTO) {
        if (blogSearchDTO.getBlogTitle() != null) {
            String decodeBlogTitle = MyBase64Utils.springDecodeString(blogSearchDTO.getBlogTitle());
            blogSearchDTO.setBlogTitle(decodeBlogTitle);
        }
        return blogSearchDTO;
    }

    public static BlogSearchDTO blogSearchDTOEncode(BlogSearchDTO blogSearchDTO) throws UnsupportedEncodingException {
        if (blogSearchDTO.getBlogTitle() != null) {
            String encodeBlogTitle = MyBase64Utils.springEncodeString(blogSearchDTO.getBlogTitle());
            blogSearchDTO.setBlogTitle(encodeBlogTitle);
        }
        return blogSearchDTO;
    }

    public static BlogUserVo blogUserVoDecode(BlogUserVo blog) {
        if (blog.getBlogContent() != null) {
            String decodeBlogContent = MyBase64Utils.springDecodeString(blog.getBlogContent());
            blog.setBlogContent(decodeBlogContent);
        }
//        if (blog.getBlogTitle() != null) {
//            String decodeBlogTitle = MyBase64Utils.springDecodeString(blog.getBlogTitle());
//            blog.setBlogTitle(decodeBlogTitle);
//        } else {
//            String decodeBlogTitle = MyBase64Utils.springDecodeString(DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_DEFAULT) + " 更新");
//            blog.setBlogTitle(decodeBlogTitle);
//        }
//        if (blog.getBlogIntro() != null) {
//            String decodeBlogIntroduction = MyBase64Utils.springDecodeString(blog.getBlogIntro());
//            blog.setBlogIntro(decodeBlogIntroduction);
//        }
        return blog;
    }

    public static BlogUserVo blogUserVoEncode(BlogUserVo blog) throws UnsupportedEncodingException {
        if (blog.getBlogContent() != null) {
            String encodeBlogContent = MyBase64Utils.springEncodeString(blog.getBlogContent());
            blog.setBlogContent(encodeBlogContent);
        }
//        if (blog.getBlogTitle() != null) {
//            String encodeBlogTitle = MyBase64Utils.springEncodeString(blog.getBlogTitle());
//            blog.setBlogTitle(encodeBlogTitle);
//        } else {
//            String encodeBlogTitle = MyBase64Utils.springEncodeString(DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_DEFAULT) + " 更新");
//            blog.setBlogTitle(encodeBlogTitle);
//        }
//        if (blog.getBlogIntro() != null) {
//            String encodeBlogIntroduction = MyBase64Utils.springEncodeString(blog.getBlogIntro());
//            blog.setBlogIntro(encodeBlogIntroduction);
//        }
        return blog;
    }

    public static Blog blogDecode(Blog blog) {
        if (blog.getBlogContent() != null) {
            String decodeBlogContent = MyBase64Utils.springDecodeString(blog.getBlogContent());
            blog.setBlogContent(decodeBlogContent);
        }
//        if (blog.getBlogTitle() != null) {
//            String decodeBlogTitle = MyBase64Utils.springDecodeString(blog.getBlogTitle());
//            blog.setBlogTitle(decodeBlogTitle);
//        } else {
//            String decodeBlogTitle = MyBase64Utils.springDecodeString(DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_DEFAULT) + " 更新");
//            blog.setBlogTitle(decodeBlogTitle);
//        }
//        if (blog.getBlogIntro() != null) {
//            String decodeBlogIntroduction = MyBase64Utils.springDecodeString(blog.getBlogIntro());
//            blog.setBlogIntro(decodeBlogIntroduction);
//        }
        return blog;
    }

    public static Blog blogEncode(Blog blog) throws UnsupportedEncodingException {
        if (blog.getBlogContent() != null) {
            String encodeBlogContent = MyBase64Utils.springEncodeString(blog.getBlogContent());
            blog.setBlogContent(encodeBlogContent);
        }
//        if (blog.getBlogTitle() != null) {
//            String encodeBlogTitle = MyBase64Utils.springEncodeString(blog.getBlogTitle());
//            blog.setBlogTitle(encodeBlogTitle);
//        } else {
//            String encodeBlogTitle = MyBase64Utils.springEncodeString(DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_DEFAULT) + " 更新");
//            blog.setBlogTitle(encodeBlogTitle);
//        }
//        if (blog.getBlogIntro() != null) {
//            String encodeBlogIntroduction = MyBase64Utils.springEncodeString(blog.getBlogIntro());
//            blog.setBlogIntro(encodeBlogIntroduction);
//        }
        return blog;
    }

    public static Draft draftDecode(Draft blog) {
        if (blog.getBlogContent() != null) {
            String decodeBlogContent = MyBase64Utils.springDecodeString(blog.getBlogContent());
            blog.setBlogContent(decodeBlogContent);
        }
//        if (blog.getBlogTitle() != null) {
//            String decodeBlogTitle = MyBase64Utils.springDecodeString(blog.getBlogTitle());
//            blog.setBlogTitle(decodeBlogTitle);
//        } else {
//            String decodeBlogTitle = MyBase64Utils.springDecodeString(DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_DEFAULT) + " 更新");
//            blog.setBlogTitle(decodeBlogTitle);
//        }
//        if (blog.getBlogIntro() != null) {
//            String decodeBlogIntroduction = MyBase64Utils.springDecodeString(blog.getBlogIntro());
//            blog.setBlogIntro(decodeBlogIntroduction);
//        }
        return blog;
    }

    public static Draft draftEncode(Draft blog) throws UnsupportedEncodingException {
        if (blog.getBlogContent() != null) {
            String encodeBlogContent = MyBase64Utils.springEncodeString(blog.getBlogContent());
            blog.setBlogContent(encodeBlogContent);
        }
//        if (blog.getBlogTitle() != null) {
//            String encodeBlogTitle = MyBase64Utils.springEncodeString(blog.getBlogTitle());
//            blog.setBlogTitle(encodeBlogTitle);
//        } else {
//            String encodeBlogTitle = MyBase64Utils.springEncodeString(DateUtils.getCurrentTime(DateUtils.DATE_FORMAT_DEFAULT) + " 更新");
//            blog.setBlogTitle(encodeBlogTitle);
//        }
//        if (blog.getBlogIntro() != null) {
//            String encodeBlogIntroduction = MyBase64Utils.springEncodeString(blog.getBlogIntro());
//            blog.setBlogIntro(encodeBlogIntroduction);
//        }
        return blog;
    }


}
