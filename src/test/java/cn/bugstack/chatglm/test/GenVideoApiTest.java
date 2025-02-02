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
        configuration.setApiSecretKey("");
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

    @Test
    public void test_genVideoAsync() throws Exception {
        VideoCompletionRequest request = new VideoCompletionRequest();
        request.setModel(Model.COGVIDEOX_Flash);
        request.setPrompt("生成一个猫拍手的动画，模仿Happy猫");
        String id = openAiSession.getVideoTaskId(request);
        log.info("生成视频任务id：{}", id);

        Thread.sleep(60 * 1000);
        VideoCompletionResponse response = openAiSession.getVideoByTaskId(id, request.getModelEnum());
        log.info("测试结果：{}", JSON.toJSONString(response));
    }

    @Test
    public void test_genVideoAsync2() throws Exception {
        VideoCompletionRequest request = new VideoCompletionRequest();
        request.setModel(Model.COGVIDEOX_Flash);
        request.setPrompt("生成一个猫拍手的动画，模仿Happy猫");
        String id = openAiSession.getVideoTaskId(request);
        log.info("生成视频任务id：{}", id);

        Thread.sleep(60 * 1000);
        int count = 20; //处理中状态重试次数
        while (count-- > 0) {
            VideoCompletionResponse response = openAiSession.tryGetVideoByTaskId(id, request.getModelEnum());
            // 成功则返回结果
            if(response.getTaskStatus().equals(VideoCompletionResponse.taskStatus.SUCCESS.getStatus())) {
                log.info("测试结果：{}", JSON.toJSONString(response));
                return;
            }
            // 失败则抛出异常
            else if(response.getTaskStatus().equals(VideoCompletionResponse.taskStatus.FAIL.getStatus())) {
                throw new RuntimeException("生成视频请求失败！");
            }
            // 处理中则等待10s再请求
            Thread.sleep( 10 * 1000);
        }
        throw new RuntimeException("生成视频请求超时！");
    }
}
