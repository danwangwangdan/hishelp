package com.xhrmyy.hishelp.service.impl;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.ShuiYinUser;
import com.xhrmyy.hishelp.model.WeChatInfo;
import com.xhrmyy.hishelp.repository.ShuiYinRepository;
import com.xhrmyy.hishelp.service.ShuiYinService;
import com.xhrmyy.hishelp.util.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author HuangShiming
 * @Date 2019-6-4
 */
@Service("shuiYinService")
public class ShuiYinServiceImpl implements ShuiYinService {

    @Autowired
    private ShuiYinRepository shuiYinRepository;

    @Override
    public BaseResult loginByWx(String code) {

        BaseResult baseResult = new BaseResult();
        try {
            WeChatInfo weChatInfo = WeChatUtil.code2Session(code);
            ShuiYinUser user = shuiYinRepository.findByOpenid(weChatInfo.getOpenid());
            if (null == user) {
                //新用户
                user = new ShuiYinUser();
                user.setPoint(50);
                user.setShareCount(0);
                user.setSignCount(0);
                user.setOpenid(weChatInfo.getOpenid());
                user.setVideoCount(0);
                ShuiYinUser returnUser = shuiYinRepository.saveAndFlush(user);
                baseResult.setData(returnUser);
            } else {
                baseResult.setData(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常，请稍后重试");
        }

        return baseResult;
    }

    @Override
    public BaseResult findById(Long id) {
        BaseResult baseResult = new BaseResult();
        try {
            ShuiYinUser user = shuiYinRepository.findById(id);
            baseResult.setData(user);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常，请稍后重试");
        }
        return baseResult;
    }

    @Override
    public BaseResult takeById(Long id) {
        BaseResult baseResult = new BaseResult();
        try {
            ShuiYinUser user = shuiYinRepository.findById(id);
            if (user.getPoint() < 2) {
                baseResult.setCode(-101);
                baseResult.setMessage("积分不足");
            } else {
                shuiYinRepository.takePoint(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常，请稍后重试");
        }
        return baseResult;
    }

    @Override
    public BaseResult addById(Long id, Integer type) {
        BaseResult baseResult = new BaseResult();
        try {
            ShuiYinUser user = shuiYinRepository.findById(id);
            if (type == 1) {

                shuiYinRepository.videoPoint(id);
                //if (user.getVideoCount() > 7) {
                //    baseResult.setCode(-101);
                //    baseResult.setMessage("今日观看次数上限");
                //} else {
                //    shuiYinRepository.videoPoint(id);
                //}
            } else if (type == 2) {
                if (user.getShareCount() > 0) {
                    baseResult.setCode(-103);
                    baseResult.setMessage("今日分享次数上限");
                } else {
                    shuiYinRepository.sharePoint(id);
                }
                shuiYinRepository.sharePoint(id);
            } else if (type == 3) {
                if (user.getSignCount() > 0) {
                    baseResult.setCode(-102);
                    baseResult.setMessage("今日签到次数上限");
                } else {
                    shuiYinRepository.signPoint(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常，请稍后重试");
        }
        return baseResult;
    }

    @Override
    public BaseResult resetCount() {

        shuiYinRepository.resetCount();
        return null;
    }

    @Override
    public BaseResult resetPoint() {
        shuiYinRepository.resetPoint();
        return null;
    }

    @Override
    public BaseResult takeBatchById(Long id) {
        BaseResult baseResult = new BaseResult();
        try {
            ShuiYinUser user = shuiYinRepository.findById(id);
            if (user.getPoint() < 50) {
                baseResult.setCode(-101);
                baseResult.setMessage("积分不足");
            } else {
                shuiYinRepository.takeBatchPoint(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常，请稍后重试");
        }
        return baseResult;
    }

    @Override
    public BaseResult updateById(Long id) {

        BaseResult baseResult = new BaseResult();
        try {
            shuiYinRepository.updateById(id);

        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(-500);
            baseResult.setMessage("服务器异常，请稍后重试");
        }
        return baseResult;

    }
}
