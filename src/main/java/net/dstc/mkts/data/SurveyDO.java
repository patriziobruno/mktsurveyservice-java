/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.data;

import java.util.Date;

/**
 *
 * @author eul0860
 */
public interface SurveyDO {

    String getId();

    Date getStartDate();

    SurveyStatus getStatus();

    String getTitle();

    SurveyTargetDO getTarget();

    void setId(String id);

    void setStartDate(Date startDate);

    void setStatus(SurveyStatus status);

    void setTitle(String title);

    void setTarget(SurveyTargetDO target);
}
