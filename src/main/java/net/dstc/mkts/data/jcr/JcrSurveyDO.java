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
package net.dstc.mkts.data.jcr;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import net.dstc.mkts.data.SurveyStatus;
import org.apache.jackrabbit.ocm.manager.enumconverter.EnumTypeConverter;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Bean;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;
import net.dstc.mkts.data.SurveyDO;
import net.dstc.mkts.data.SurveyTargetDO;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Node
public class JcrSurveyDO implements Serializable, SurveyDO {

    @Field(path = true)
    private String id;
    @Field
    private String title;
    @Field
    private Date startDate;
    @Field(converter = EnumTypeConverter.class)
    private SurveyStatus status;
    @Bean
    private SurveyTargetDO target;

    public JcrSurveyDO() {
        id = "/survey/" + UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public SurveyStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    @Override
    public SurveyTargetDO getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(SurveyTargetDO target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object b) {
        if (b == null) {
            return false;
        }

        if (this == b) {
            return true;
        }

        if (b instanceof JcrSurveyDO == false) {
            return false;
        }

        JcrSurveyDO DO = (JcrSurveyDO) b;

        if (target == null && DO.getTarget() != null) {
            return false;
        }

        if (target != null && DO.getTarget() == null) {
            return false;
        }

        if (target != null && target.hashCode() != DO.getTarget().hashCode()) {
            return false;
        }

        return hashCode() == b.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.title);
        hash = 79 * hash + Objects.hashCode(this.startDate);
        hash = 79 * hash + Objects.hashCode(this.status);
        return hash;
    }
}
