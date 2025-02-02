package cn.bugstack.chatglm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * CogView <a href="https://open.bigmodel.cn/dev/api/videomodel/cogvideox">视频生成任务回复</a>
 */
@Data
public class VideoCompletionResponse {

    /**
     * 本次调用的模型名称
     */
    private String model;

    /**
     * 处理状态，PROCESSING（处理中），SUCCESS（成功），FAIL（失败）。需通过查询获取结果
     */
    @JsonProperty("task_status")
    private String taskStatus;

    /**
     * 用户在客户端请求时提交的任务编号或者平台生成的任务编号
     */
    @JsonProperty("request_id")
    private String requestId;

    /**
     * 智谱 AI 开放平台生成的任务订单号，调用请求结果接口时请使用此订单号
     */
    private String id;

    /**
     * 视频生成结果
     */
    @JsonProperty("video_result")
    private List<video_result> videoResults;

    @Data
    public static class video_result {
        private String cover_image_url;
        private String url;
    }

    @Getter
    @AllArgsConstructor
    public enum taskStatus {
        PROCESSING("PROCESSING","处理中"),
        SUCCESS("SUCCESS", "成功"),
        FAIL("FAIL", "失败"),
        ;

        private final String status;
        private final String info;
    }

}
