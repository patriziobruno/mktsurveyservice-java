/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.data.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import net.dstc.mkts.data.SurveyStatus;
import org.hibernate.annotations.GenericGenerator;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyTargetDO;

/**
 *
 * @author patrizio
 */
@Entity(name = "survey")
public class JpaSurveyDO implements SurveyDO, Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    private SurveyStatus status;
    @Basic
    private String title;
    @Embedded
    private JpaSurveyTargetDO target;
    
    @Version
    private long version;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public SurveyStatus getStatus() {
        return status;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public SurveyTargetDO getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(SurveyTargetDO target) {
        this.target = (JpaSurveyTargetDO) target;
    }
}
