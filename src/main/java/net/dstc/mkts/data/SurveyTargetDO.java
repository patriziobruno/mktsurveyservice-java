/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.data;

import org.apache.jackrabbit.ocm.mapper.impl.annotation.Node;

/**
 *
 * @author eul0860
 */
@Node
public interface SurveyTargetDO {

    String getCountry();

    String getGender();

    int getMaxAge();

    int getMaxIncome();

    int getMinAge();

    int getMinIncome();

    void setCountry(String country);

    void setGender(String gender);

    void setMaxAge(int maxAge);

    void setMaxIncome(int maxIncome);

    void setMinAge(int minAge);

    void setMinIncome(int minIncome);
}
