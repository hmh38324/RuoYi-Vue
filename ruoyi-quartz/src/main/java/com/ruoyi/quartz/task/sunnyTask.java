package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("sunnyTask")
public class sunnyTask
{

    public void test()  {
        HttpUtils.sendGet("https://sctapi.ftqq.com/SCT164098T6WlEfY7ffLoYx4hfnRPkNBI4.send?title=TrainTicket");
    }
    public void ticket() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("购买地铁周票", "GBK");
        String encodeURL = "https://sctapi.ftqq.com/SCT164098T6WlEfY7ffLoYx4hfnRPkNBI4.send?title=" + encode;
        HttpUtils.sendGet(encodeURL);
    }

    public void xxqg() throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("学习强国", "GBK");
        String encodeURL = "https://sctapi.ftqq.com/SCT164098T6WlEfY7ffLoYx4hfnRPkNBI4.send?title=" + encode;
    }

}
