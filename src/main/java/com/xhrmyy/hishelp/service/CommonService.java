package com.xhrmyy.hishelp.service;

import com.xhrmyy.hishelp.common.BaseResult;
import com.xhrmyy.hishelp.entity.FormIdMapper;
import com.xhrmyy.hishelp.entity.Suggestion;
import com.xhrmyy.hishelp.entity.Trouble;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by huangshiming on 2018/10/23 21:37
 */
public interface CommonService {

    BaseResult getFirTroubleTypes();

    BaseResult getSecTroubleTypes(Long firTypeId);

    BaseResult getSolutionTypes();

    BaseResult submitSug(Suggestion suggestion);

    BaseResult uploadImage(List<MultipartFile> multipartFiles, String serverUrl);

    BaseResult collectFormIds(Trouble trouble);

    BaseResult<FormIdMapper> getOneUsefulFormId(String openId);

    BaseResult getNotice();

    BaseResult getContact();

    BaseResult setIsHoliday(String isHoliday);

    BaseResult setIsWeekendWork(String isWeekendWork);

    BaseResult setDutyPerson(String dutyPerson);

    BaseResult getDutyPlan();

}
