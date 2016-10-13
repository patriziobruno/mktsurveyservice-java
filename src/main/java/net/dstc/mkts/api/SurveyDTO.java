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
package net.dstc.mkts.api;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import net.dstc.mkts.data.SurveyStatus;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
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
