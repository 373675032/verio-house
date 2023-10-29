package com.verio.controller;

import com.verio.dto.Resp;
import com.verio.dto.RespResult;
import com.verio.entity.User;
import com.verio.utils.Assert;
import com.verio.utils.OssUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件控制器
 * ==========================================================================
 * 郑重说明：本项目免费开源！原创作者为：薛伟同学，严禁私自出售。
 * ==========================================================================
 * B站账号：薛伟同学
 * 微信公众号：薛伟同学
 * 作者博客：http://xuewei.world
 * ==========================================================================
 * 陆陆续续总会收到粉丝的提醒，总会有些人为了赚取利益倒卖我的开源项目。
 * 不乏有粉丝朋友出现钱付过去，那边只把代码发给他就跑路的，最后还是根据线索找到我。。
 * 希望各位朋友擦亮慧眼，谨防上当受骗！
 * ==========================================================================
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController<User> {

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = OssUtil.upload(file, loginUser.getId() + "");
        if (Assert.isEmpty(url)) {
            return Resp.resp(RespResult.fail("上传失败", url));
        }
        return Resp.resp(RespResult.success("上传成功", url));
    }
}
