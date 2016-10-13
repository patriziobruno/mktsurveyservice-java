/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author eul0860
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
}
