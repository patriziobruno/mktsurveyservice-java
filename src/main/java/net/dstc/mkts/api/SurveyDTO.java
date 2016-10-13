/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.api;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import net.dstc.mkts.data.SurveyStatus;

/**
 *
 * @author eul0860
 */
@XmlRootElement
public class SurveyDTO {

    @XmlElement
    private String id;
    @XmlElement
    private String title;
    @XmlElement
    private SurveyStatus status;
    @XmlElement
    private Date startDate;
    
    @XmlElement
    private SurveyTargetDTO target;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SurveyStatus getStatus() {
        return status;
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public SurveyTargetDTO getTarget() {
        return target;
    }

    public void setTarget(SurveyTargetDTO target) {
        this.target = target;
    }
}
