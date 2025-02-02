package cn.bugstack.chatglm.session;

import cn.bugstack.chatglm.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @author 小傅哥，微信：fustack
 * @description 会话服务接口
 * @github https://github.com/fuzhengwei/chatglm-sdk-java
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface OpenAiSession {

    EventSource completions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws Exception;

    CompletableFuture<String> completions(ChatCompletionRequest chatCompletionRequest) throws Exception;

    ChatCompletionSyncResponse completionsSync(ChatCompletionRequest chatCompletionRequest) throws Exception;

    ImageCompletionResponse genImages(ImageCompletionRequest imageCompletionRequest) throws Exception;

    Configuration configuration();

    VideoCompletionResponse genVideo(VideoCompletionRequest videoCompletionRequest) throws Exception;

    String getVideoTaskId(VideoCompletionRequest videoCompletionRequest) throws Exception;

    VideoCompletionResponse getVideoByTaskId(String id, Model model) throws Exception;

    VideoCompletionResponse tryGetVideoByTaskId(String id, Model model) throws Exception;

}
