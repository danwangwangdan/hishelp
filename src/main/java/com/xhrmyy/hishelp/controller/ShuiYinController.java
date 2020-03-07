package com.xhrmyy.hishelp.controller;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.service.CommonService;
import com.xhrmyy.hishelp.service.ShuiYinService;
import com.xhrmyy.hishelp.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@RestController
@RequestMapping("/shuiyin")
public class ShuiYinController {

    @Autowired
    private ShuiYinService shuiYinService;
    @Autowired
    private CommonService commonService;

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping("/decode")
    public BaseResult decode(@RequestParam String url) {

        String decodeUrl = CommonUtils.decodeHttpUrl(url);
        BaseResult baseResult = new BaseResult();
        baseResult.setData(decodeUrl.equals("") ? "解析有误，请稍后重试" : decodeUrl);
        log.info("解析结果：" + baseResult);
        return baseResult;
    }

    @RequestMapping("/login")
    public BaseResult loginByWx(@RequestParam String code, HttpServletRequest request) {

        return shuiYinService.loginByWx(code);
    }

    @RequestMapping("/downloadVideo")
    public void downloadVideo(@RequestParam String url, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //判断文件夹是否存在
        OutputStream outputStream = null;
        HttpURLConnection conn = null;
        InputStream in = null;
        try {
            // 建立链接
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            in = conn.getInputStream();
            response.setHeader("Content-Length", String.valueOf(conn.getContentLengthLong()));
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + new Date().getTime() + ".MP4");

            outputStream = new BufferedOutputStream(response.getOutputStream());
            //创建存放文件内容的数组
            byte[] buff = new byte[1024];
            //所读取的内容使用n来接收
            int n;
            //当没有读取完时,继续读取,循环
            while ((n = in.read(buff)) != -1) {
                //将字节数组的数据全部写入到输出流中
                outputStream.write(buff, 0, n);
            }

            //强制将缓存区的数据进行输出
            outputStream.flush();
            //关流
            outputStream.close();
            in.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }
    }

    @RequestMapping("/loginPro")
    public BaseResult loginByWxPro(@RequestParam String code, HttpServletRequest request) {

        return shuiYinService.loginByWxPro(code);
    }


    @RequestMapping("/find")
    private BaseResult findById(@RequestParam Long id) {

        return shuiYinService.findById(id);
    }

    @RequestMapping("/srequest")
    private BaseResult sRequest(@RequestParam Long id) throws UnsupportedEncodingException {

        return shuiYinService.updateById(id);
    }


    /**
     * 扣除积分
     *
     * @param id
     * @return
     */
    @RequestMapping("/take")
    private BaseResult takeById(@RequestParam Long id) {

        return shuiYinService.takeById(id);
    }

    @RequestMapping("/takebatch")
    private BaseResult takeBatchById(@RequestParam Long id) {

        return shuiYinService.takeBatchById(id);
    }

    @RequestMapping("/add")
    private BaseResult addById(@RequestParam Long id, @RequestParam Integer type) {

        return shuiYinService.addById(id, type);
    }

    @RequestMapping("/addPro")
    private BaseResult addPro(@RequestParam Long id) {

        return shuiYinService.addPro(id);
    }

    @RequestMapping("/addpoint")
    private BaseResult addPoint(@RequestParam Long id, @RequestParam Integer point) {

        return shuiYinService.addPoint(id, point);
    }

}
