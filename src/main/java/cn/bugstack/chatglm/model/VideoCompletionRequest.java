package cn.bugstack.chatglm.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * CogView <a href="https://open.bigmodel.cn/dev/api/videomodel/cogvideox">根据用户的文字描述生成视频,使用同步调用方式请求接口</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoCompletionRequest {

    /**
     * CogVideoX-Flash为免费的视频生成模型
     */
    private Model model = Model.COGVIDEOX_Flash;

    /**
     * 所需视频的文本描述
     */
    private String prompt;

    /**
     * 是否生成 AI 音效。默认值: False（不生成音效）
     */
    @JsonProperty("with_audio")
    private Boolean withAudio = false;

    public String getModel() {
        return model.getCode();
    }

    public Model getModelEnum() {
        return model;
    }

    @Override
    public String toString() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("model", model.getCode());
        paramsMap.put("prompt", prompt);
        paramsMap.put("with_audio", withAudio);
        try {
            return new ObjectMapper().writeValueAsString(paramsMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

