package cn.bugstack.chatglm.test;

import cn.bugstack.chatglm.model.Model;
import cn.bugstack.chatglm.model.VideoCompletionRequest;
import cn.bugstack.chatglm.model.VideoCompletionResponse;
import cn.bugstack.chatglm.session.Configuration;
import cn.bugstack.chatglm.session.OpenAiSession;
import cn.bugstack.chatglm.session.OpenAiSessionFactory;
import cn.bugstack.chatglm.session.defaults.DefaultOpenAiSessionFactory;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Before;
import org.junit.Test;

/**
 * @author 小傅哥，微信：fustack
 * @description 在官网申请 ApiSecretKey <a href="https://open.bigmodel.cn/usercenter/apikeys">ApiSecretKey</a>
 * @github <a href="https://github.com/fuzhengwei/chatglm-sdk-java">chatglm-sdk-java</a>
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Slf4j
public class GenVideoApiTest {

    private OpenAiSession openAiSession;

    @Before
    public void test_OpenAiSessionFactory() {
        // 1. 配置文件
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://open.bigmodel.cn/");
        configuration.setApiSecretKey("f747bf4c0f79466c976bf70d285652c7.xl0vlvP0LMpQouFG");
        configuration.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        // 3. 开启会话
        this.openAiSession = factory.openSession();
    }

    @Test
    public void test_genVideo() throws Exception {
        VideoCompletionRequest request = new VideoCompletionRequest();
        request.setModel(Model.COGVIDEOX_Flash);
        request.setPrompt("生成一个猫拍手的动画，模仿Happy猫");
        VideoCompletionResponse response = openAiSession.genVideo(request);
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

}
