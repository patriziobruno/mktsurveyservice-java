/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.data.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import net.dstc.mkts.data.SurveyTargetDO;

/**
 *
 * @author eul0860
 */
@Embeddable
public class JpaSurveyTargetDO implements SurveyTargetDO, Serializable {

    private int id;
    private String gender;
    private int minAge;
    private int maxAge;

    private int minIncome;
    private int maxIncome;
    private String country;

    public JpaSurveyTargetDO() {
    }

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
        
        if(b instanceof JpaSurveyTargetDO == false)
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
