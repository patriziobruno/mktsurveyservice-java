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

import java.util.Objects;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Field;
import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;
import net.dstc.mkts.data.SurveyTargetDO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@Node
public class JcrSurveyTargetDO implements SurveyTargetDO {
    @Field private String gender;
    @Field private int minAge;
    @Field private int maxAge;
    
    @Field private int minIncome;
    @Field private int maxIncome;
    @Field private String country;

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int getMinAge() {
        return minAge;
    }

    @Override
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    @Override
    public int getMaxAge() {
        return maxAge;
    }

    @Override
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public int getMinIncome() {
        return minIncome;
    }

    @Override
    public void setMinIncome(int minIncome) {
        this.minIncome = minIncome;
    }

    @Override
    public int getMaxIncome() {
        return maxIncome;
    }

    @Override
    public void setMaxIncome(int maxIncome) {
        this.maxIncome = maxIncome;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object b) {
        if (b == null) {
            return false;
        }

        if (this == b) {
            return true;
        }
        
        if(b instanceof JcrSurveyTargetDO == false)
            return false;
        
        return hashCode() == b.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.gender);
        hash = 37 * hash + this.minAge;
        hash = 37 * hash + this.maxAge;
        hash = 37 * hash + this.minIncome;
        hash = 37 * hash + this.maxIncome;
        hash = 37 * hash + Objects.hashCode(this.country);
        return hash;
    }
}
