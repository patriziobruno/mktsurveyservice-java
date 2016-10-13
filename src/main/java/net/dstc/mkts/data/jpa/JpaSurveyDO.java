/* 
 * Copyright 2016 eul0860.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @author Patrizio Bruno <desertconsulting@gmail.com>
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
