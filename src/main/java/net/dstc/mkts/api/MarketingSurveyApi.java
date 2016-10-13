/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.api;

import java.util.Collection;

/**
 *
 * @author eul0860
 */
public interface MarketingSurveyApi {

    Collection<SurveyDTO> getList(SurveyDTO query);
    void insert(SurveyDTO survey);
    void update(SurveyDTO survey);
    void delete(String id);
}
