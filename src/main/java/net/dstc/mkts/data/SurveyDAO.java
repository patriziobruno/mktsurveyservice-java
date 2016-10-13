/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dstc.mkts.data;

import java.util.Collection;

/**
 *
 * @author eul0860
 */
public interface SurveyDAO {

    void add(SurveyDO survey);

    void delete(String id);

    SurveyDO get(String id);

    Collection<SurveyDO> list(SurveyDO query);

    void update(SurveyDO s);
    
    SurveyDO createSurvey();
    
    SurveyTargetDO createSurveyTarget();
    
}
