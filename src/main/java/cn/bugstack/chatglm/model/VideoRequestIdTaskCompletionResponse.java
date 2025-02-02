package cn.bugstack.chatglm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * CogView <a href="https://open.bigmodel.cn/dev/api/videomodel/cogvideox">视频生成任务id</a>
 */
@Data
public class VideoRequestIdTaskCompletionResponse {

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
     * 本次调用的模型名称
     */
    private String model;

    /**
     * 处理状态，PROCESSING（处理中），SUCCESS（成功），FAIL（失败）。需通过查询获取结果
     */
    @JsonProperty("task_status")
    private String taskStatus;

}
