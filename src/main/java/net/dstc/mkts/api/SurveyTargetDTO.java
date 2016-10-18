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

import java.util.Arrays;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Patrizio Bruno <desertconsulting@gmail.com>
 */
@XmlRootElement
public class SurveyTargetDTO {

    @XmlElement
    private int[] ageRange;
    @XmlElement
    private int[] incomeRange;
    @XmlElement
    private String country;
    @XmlElement
    private String gender;

    public int[] getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int[] ageRange) {
        this.ageRange = ageRange;
    }

    public int[] getIncomeRange() {
        return incomeRange;
    }

    public void setIncomeRange(int[] incomeRange) {
        this.incomeRange = incomeRange;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Arrays.hashCode(this.ageRange);
        hash = 29 * hash + Arrays.hashCode(this.incomeRange);
        hash = 29 * hash + Objects.hashCode(this.country);
        hash = 29 * hash + Objects.hashCode(this.gender);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SurveyTargetDTO other = (SurveyTargetDTO) obj;
        if (!Objects.equals(this.country, other.getCountry())) {
            return false;
        }
        if (!Objects.equals(this.gender, other.getGender())) {
            return false;
        }
        if (!Arrays.equals(this.ageRange, other.getAgeRange())) {
            return false;
        }
        if (!Arrays.equals(this.incomeRange, other.getIncomeRange())) {
            return false;
        }
        return hashCode() == other.hashCode();
    }
}
